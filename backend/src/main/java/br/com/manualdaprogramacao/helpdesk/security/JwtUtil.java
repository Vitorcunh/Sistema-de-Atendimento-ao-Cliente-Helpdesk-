package br.com.manualdaprogramacao.helpdesk.security;

import br.com.manualdaprogramacao.helpdesk.dto.AuthResponseDto;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public AuthResponseDto generateToken(String username) {
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plusSeconds(JWT_EXPIRATION_TIME)))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claims
        );

        try {
            MACSigner signer = new MACSigner(secret);
            signedJWT.sign(signer);

            logger.info("- - - - [GENERATED TOKEN] - - - -");
            return new AuthResponseDto(username, signedJWT.serialize(), claims.getExpirationTime().getTime());
        } catch (Exception e) {
            logger.error("Error generating token", e);
            throw new RuntimeException("Error generating token", e);
        }
    }

    public String extractSubject(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            logger.error("- - - - [MALFORMED TOKEN] - - - -", e);
        }
        return null;
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token) && isSignatureValid(token);
    }

    private boolean isSignatureValid(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            MACVerifier verifier = new MACVerifier(secret);
            return signedJWT.verify(verifier);
        } catch (Exception e) {
            logger.error("- - - - [INVALID TOKEN SIGNATURE] - - - -", e);
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            long expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime().getTime();
            long currentTime = new Date().getTime();
            return expirationTime < currentTime;
        } catch (Exception e) {
            logger.error("- - - - [EXPIRED TOKEN] - - - -", e);
            return true;
        }
    }

    private static final long ONE_HOUR = 60 * 60;
    public static final long JWT_EXPIRATION_TIME = ONE_HOUR;
}
