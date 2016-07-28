package Core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * This class contains data and methods that are common for both Client and Server side.
 *
 * Created by james.smith on 7/28/16.
 */
public class CommonClientServer {

    public static String receiveMessage(InputStream inputStream) throws UnsupportedEncodingException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                System.out.println("Length: " + length);
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString("UTF-8");
    }
}
