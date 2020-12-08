package pl.itronics.home.utils;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalCreds {
    public static String getCredentials(String credentialsField) {
        String data = "";

        String pattern = "<" + credentialsField + ">(.*)<" + "/" + credentialsField + ">";
        Pattern r = Pattern.compile(pattern);


        try {
            FileInputStream fis = new FileInputStream("authdata.key");
            try {
                data = IOUtils.toString(fis, "UTF-8");
            } catch (IOException io) {
                System.out.println("Input-output exception");
            }
        } catch(FileNotFoundException fnf) {
            System.out.println("There's file not found exception appears");
        }

        Matcher m = r.matcher(data);


        if(m.find()) {
            return m.group(1);
        }

        return data;
    }
}
