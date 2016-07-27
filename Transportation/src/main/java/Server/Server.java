package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents server side of the "client-server" application.
 * Connection between client and the server is made by TCP/IP.
 *
 * Created by james.smith on 7/26/16.
 */
public class Server {

    private static String receiveMessage(InputStream inputStream) throws UnsupportedEncodingException {
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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("Server info:");
            System.out.println("IP Address: " + serverSocket.getInetAddress());
            System.out.println("Port: " + serverSocket.getLocalPort());

            try (Socket socket = serverSocket.accept()) {
                System.out.println("Received a connection from: " + socket.getRemoteSocketAddress());
                try (InputStream in = socket.getInputStream();
                     OutputStream out = socket.getOutputStream()) {
                    String message = receiveMessage(in);
                    System.out.println("Message:\n" + message);
                    System.out.println("Message length: " + message.length());
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
