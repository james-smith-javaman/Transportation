package Samples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is designed for testing different approaches
 * to deal with I/O system.
 * Created by james.smith on 6/17/16.
 */
public class TryFileIO {

    public static String getFileContent(String path) {
        String fileContent = null;

        try {
            fileContent = new String (Files.readAllBytes(Paths.get(path)));
            System.out.println("Read file content into string:\n" + fileContent);
        } catch (IOException e) {
            System.err.println("An I/O exception");
            e.printStackTrace();
        }

        return fileContent;
    }

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

        try (FileReader fr = new FileReader(path)) {
            int c;
            StringBuilder content = new StringBuilder();

            while ((c = fr.read()) != -1) {
                content.append((char)c);
            }

            String str = content.toString();
            System.out.println("Read into string by I/O:\n" + str);

        } catch (FileNotFoundException e) {
            System.err.println("File not found! Path: " + path);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("An I/O exception");
            e.printStackTrace();
        }

        try {
            String fileContent = new String (Files.readAllBytes(Paths.get(path)));

            System.out.println("Read into string by NIO:\n" + fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
