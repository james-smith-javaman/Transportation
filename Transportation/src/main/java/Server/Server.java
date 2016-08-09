package Server;

import Core.CommonClientServer;
import Core.CourierSchedule;
import Core.CourierSystem;
import Core.Departure;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This class represents server side of the "client-server" application.
 * Connection between client and the server is made by TCP/IP.
 *
 * Created by james.smith on 7/26/16.
 */
public class Server {

    private static Socket clientSocket;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("Server info:");
            System.out.println("IP Address: " + serverSocket.getInetAddress());
            System.out.println("Port: " + serverSocket.getLocalPort());

            while (true) {
                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Received a connection from: " + clientSocket.getRemoteSocketAddress());
                    System.out.println("Before new thread");
                    new Thread(new RunningThread(clientSocket), clientSocket.getRemoteSocketAddress().toString()).start();
                    System.out.println("After new thread");
                } catch (IOException e) {
                    System.out.println("Failed to accept new client.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
