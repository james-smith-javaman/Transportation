package Samples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by tarasmotyl on 6/17/16.
 */
public class TryFileIO {
    public static void main(String[] args) {
        int i;

        String path = "/Users/tarasmotyl/Documents/JavaProjects/MyJavaProjects/TransportationProject" +
                "/Transportation/files/firstFile.txt";

        System.out.println("TryFileIO main method.\n");

        try (FileInputStream fin = new FileInputStream(path)) {

            do {
                i = fin.read();
                if (i != -1) {
                    System.out.print((char)i);
                }
            } while (i != -1);
            System.out.println();

        } catch (FileNotFoundException e) {
            System.err.println("File not found! Path: " + path);
        } catch (IOException e) {
            System.err.println("An I/O exception");
            e.printStackTrace();
        }

        try {
            String fileContent = new String (Files.readAllBytes(Paths.get(path)));

            System.out.println("Read into string:\n" + fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
