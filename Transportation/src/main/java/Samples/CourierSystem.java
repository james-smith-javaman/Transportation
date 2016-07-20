package Samples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class is designed for handling interaction with user via console.
 *
 * Created by tarasmotyl on 7/20/16.
 */
public class CourierSystem {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.println("Enter path to file or 'stop' to finish entering schedules.");
            String line = reader.nextLine();
            if (line.toLowerCase().equals("stop")) {
                break;
            }

            if (!Files.exists(Paths.get(line))) {
                System.out.println("Wrong path. Please try again.");
                continue;
            } else {
                Gson gsonSchedule = new GsonBuilder().setPrettyPrinting().create();
                CourierSchedule fromJSONFile = gsonSchedule.fromJson(TryFileIO.getFileContent(line),
                        CourierSchedule.class);
                System.out.println("CourierSchedule object from JSON:\n" + fromJSONFile);
            }
            System.out.println();
        }
    }
}
