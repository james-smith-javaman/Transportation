package Server;

import Core.CommonClientServer;
import Core.Departure;

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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("Server info:");
            System.out.println("IP Address: " + serverSocket.getInetAddress());
            System.out.println("Port: " + serverSocket.getLocalPort());

            try (Socket socket = serverSocket.accept()) {
                System.out.println("Received a connection from: " + socket.getRemoteSocketAddress());

                try (InputStream in = socket.getInputStream();
                     OutputStream out = socket.getOutputStream();
                     ObjectOutputStream objectOut = new ObjectOutputStream(out);
                     ObjectInputStream objectIn = new ObjectInputStream(in)) {

                    System.out.println(objectIn.readInt());

                    //System.out.println(dataIn.readInt());


                    Departure receivedObject = (Departure) objectIn.readObject();
                    System.out.println("Received object: " + receivedObject);

                    System.out.println(objectIn.readInt());


                    String message = CommonClientServer.receiveMessage(in);
                    System.out.println("Message:\n" + message);
                    System.out.println("Message length: " + message.length());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
