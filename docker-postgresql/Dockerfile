FROM postgres:16-alpine as dumper

COPY init.sql /docker-entrypoint-initdb.d/init.sql

RUN ["sed", "-i", "s/exec \"$@\"/echo \"skipping...\"/", "/usr/local/bin/docker-entrypoint.sh"]

ENV PG_USER=postgres
ENV PGDATA=/data
ENV POSTGRES_PASSWORD StrongAdminP@ssw0rd

RUN ["/usr/local/bin/docker-entrypoint.sh", "postgres"]

# final build stage
FROM postgres:16-alpine

ENV POSTGRES_PASSWORD StrongAdminP@ssw0rd
ENV POSTGRES_USER postgres
ENV POSTGRES_DB postgres

COPY --from=dumper /data $PGDATA

EXPOSE 5432