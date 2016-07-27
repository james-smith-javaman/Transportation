package Client;

import Samples.TryFileIO;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class represents client side of the "client-server" application.
 * Connection between client and the server is made by TCP/IP.
 *
 * Created by james.smith on 7/26/16.
 */
public class Client {

    public static void main(String[] args) {

        /*
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter IP Address of the server: ");
        String ipAddress = reader.nextLine();

        System.out.print("Enter port of the server: ");
        int port = reader.nextInt();
        */

        String ipAddress = "localhost";
        int port = 7000;

        System.out.println("Connecting to: \n" + ipAddress + "\n" + port);
        try (Socket socket = new Socket(ipAddress, port)) {
            System.out.println("Connected to: " + socket.getRemoteSocketAddress());

            try (InputStream in = socket.getInputStream();
                 OutputStream out = socket.getOutputStream()){

                String message = TryFileIO.getFileContent("/Users/tarasmotyl/Desktop/king.txt");

                System.out.println("Message length: " + message.length());
                out.write(message.getBytes());
                out.flush();
                System.out.println("Flushed out.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
