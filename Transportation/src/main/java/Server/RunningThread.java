package Server;

import Core.CourierSchedule;
import Core.CourierSystem;
import Core.Departure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This class is designed for processing information of each client in separate thread.
 *
 * Created by james.smith on 8/9/16.
 */
public class RunningThread implements Runnable {

    private Socket socket;

    public RunningThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Thread info:");
        System.out.println("Thread name: " + Thread.currentThread().getName() + "\n" +
                           "Thread ID: " + Thread.currentThread().getId());

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

        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Exception in RunningThread.run()");
            e.printStackTrace();
        }
    }
}
