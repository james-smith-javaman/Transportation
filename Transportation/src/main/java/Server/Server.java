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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("Server info:");
            System.out.println("IP Address: " + serverSocket.getInetAddress());
            System.out.println("Port: " + serverSocket.getLocalPort());

            try (Socket socket = serverSocket.accept()) {
                System.out.println("Received a connection from: " + socket.getRemoteSocketAddress());

                try (ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream())) {

                    int size = objectIn.readInt();
                    System.out.println("size: " + size);
                    List<String> filesList = new ArrayList<>();
                    List<CourierSchedule> courierSchedulesList = new ArrayList<>();
                    List<String> responseList = new ArrayList<>();

                    for (int i = 0; i != size; ++i) {
                        filesList.add((String)objectIn.readObject());
                    }

                    System.out.println("Received list of strings:");
                    System.out.println("List size: " + filesList.size());
                    for (String str: filesList) {
                        System.out.println("Length: " + str.length());
                    }

                    Departure receivedDepartureObject = (Departure) objectIn.readObject();
                    System.out.println("Received object: " + receivedDepartureObject);

                    for (String str: filesList) {
                        courierSchedulesList.add(CourierSystem.getCourierScheduleObjectFromJSON(str));
                    }

                    for (CourierSchedule courierSchedule: courierSchedulesList) {
                        HashSet<Departure> checkSet0 = courierSchedule.findDepartureInSchedule(receivedDepartureObject);
                        if (!checkSet0.isEmpty()) {
                            responseList.add("Found departures for courier: " + courierSchedule.getCourierName() + "\n"
                                    + checkSet0);
                            //System.out.println("Found departures for courier: " + courierSchedule.getCourierName() + "\n" + checkSet0);
                        } else {
                            responseList.add("No available departures for courier: " + courierSchedule.getCourierName());
                            //System.out.println("No available departures for courier: " + courierSchedule.getCourierName());
                        }
                    }

                    objectOut.writeInt(responseList.size());
                    objectOut.flush();

                    for (String response: responseList) {
                        objectOut.writeObject(response);
                        objectOut.flush();
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
