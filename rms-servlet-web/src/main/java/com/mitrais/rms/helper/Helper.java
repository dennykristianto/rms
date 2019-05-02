package com.mitrais.rms.helper;

import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Helper {
    public static String getRouteLink(HttpServletRequest request, String href){
        try {
            return new URL(href).toString();
        } catch (MalformedURLException e) {
            try {
                return new URL(request.getScheme(),request.getServerName(),
                        request.getServerPort(),request.getContextPath()+'/'+href).toString();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                return "";
            }
        }
    }


    public static String store(String subFolder, Part file, HttpServletRequest request) {
        String filename= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).toString()+new Random().nextInt(999)+"."+ FilenameUtils.getExtension(getFileName(file));
        String path = request.getServletContext().getRealPath("");
        Path rootLocation= Paths.get(path+File.separator+"uploads");

        File directory = new File(path+File.separator+"uploads"+File.separator+subFolder);

        if (! directory.exists()){
            directory.mkdirs();
        }

        try {
            if (filename.contains("..")) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, rootLocation.resolve((!subFolder.equals("")?subFolder+"/":"")+filename),
                        StandardCopyOption.REPLACE_EXISTING);
                return "uploads"+File.separator+subFolder+"/"+filename;
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    private static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "";
    }
}
