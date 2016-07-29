package Client;

import Core.CommonClientServer;
import Core.CourierSchedule;
import Core.CourierSystem;
import Core.Departure;
import Samples.TryFileIO;

import java.io.*;
import java.net.Socket;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
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

        /*
        List<String> pathsList = CourierSystem.readPaths();
        if (pathsList.isEmpty()) {
            System.out.println("Nothing to process. No path has been entered.\nQuiting...");
            System.exit(-1);
        }
        List<CourierSchedule> courierScheduleList = CourierSystem.getCourierScheduleObjectsFromJSON(pathsList);
        Departure checkDeparture = new Departure(2015, Month.JANUARY, 1, 0, 0);
        //System.out.println("To find feature departures please fill in next information:\n");
        //Departure checkDeparture = CourierSystem.createCheckerDeparture();
        */

        List<String> stringList = new ArrayList<>();
        stringList.add("First string");
        stringList.add("Second string");
        stringList.add("Third string");
        stringList.add("Fourth string");
        stringList.add("Fifth string");
        stringList.add("Sixth string");
        stringList.add("Seventh string");
        stringList.add("Eighth string");
        stringList.add("Ninth string");
        stringList.add("Tenth string");

        String ipAddress = "localhost";
        int port = 7000;

        System.out.println("Connecting to: \n" + ipAddress + "\n" + port);
        try (Socket socket = new Socket(ipAddress, port)) {
            System.out.println("Connected to: " + socket.getRemoteSocketAddress());

            try (ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream())) {

                /*objectOut.writeInt(999);
                objectOut.flush();

                Departure checkDeparture = new Departure(2015, Month.JANUARY, 1, 0, 0);
                objectOut.writeObject(checkDeparture);
                objectOut.flush();

                objectOut.writeInt(999);
                objectOut.flush();

                /*String request = TryFileIO.getFileContent("/Users/tarasmotyl/Desktop/king.txt");
                System.out.println("Message length: " + request.length());

                out.write(request.getBytes());
                out.flush();*/

                List<String> pathsList = CourierSystem.readPaths();
                List<String> filesContent = new ArrayList<>();

                for (String path: pathsList) {
                    filesContent.add(CourierSystem.getFileContent(path));
                }

                objectOut.writeInt(filesContent.size());
                objectOut.flush();

                System.out.println("Sending list of strings");
                for (String message: filesContent) {
                    System.out.println("Length: " + message.length());
                    objectOut.writeObject(message);
                    objectOut.flush();
                }

                Departure checkDeparture = new Departure(2015, Month.JANUARY, 1, 0, 0);
                System.out.println("Sending checker Departure object: " + checkDeparture);
                objectOut.writeObject(checkDeparture);
                objectOut.flush();

                int responseSize = objectIn.readInt();

                for (int i = 0; i != responseSize; ++i) {
                    System.out.println((String)objectIn.readObject());
                }

                CourierSystem.closeScanner();


                /*String response = CommonClientServer.receiveMessage(in);
                System.out.println("Server response:\n" + response);*/
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
