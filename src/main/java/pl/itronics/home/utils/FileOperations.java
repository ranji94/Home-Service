package pl.itronics.home.utils;

import pl.itronics.home.domain.DatabaseConnectionInfo;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperations {
    public static void writeDbCredentials(DatabaseConnectionInfo dbConnection)
            throws IOException {
        String config = String.format("<db:pass>%s</db:pass>\n" +
                "<db:user>%s</db:user>\n" +
                "<db:server>%s</db:server>\n" +
                "<db:database>%s</db:database>",
                dbConnection.getPassword(),
                dbConnection.getUser(),
                dbConnection.getServer(),
                dbConnection.getDatabase());
        FileOutputStream outputStream = new FileOutputStream("authdata.key");
        byte[] strToBytes = config.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
    }
}
