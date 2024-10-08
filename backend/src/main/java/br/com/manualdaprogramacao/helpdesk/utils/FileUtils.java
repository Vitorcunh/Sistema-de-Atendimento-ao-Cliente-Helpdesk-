package br.com.manualdaprogramacao.helpdesk.utils;

import java.io.*;
import java.util.Base64;

public class FileUtils {

    public static byte[] convertBase64ToByteArray(String base64String) throws IOException {
        String[] base64Parts = base64String.split(",");
        byte[] fileBytes = Base64.getDecoder().decode(base64Parts[1]);
        return fileBytes;
    }

    public static void saveByteArrayToFile(byte[] data, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }


}
