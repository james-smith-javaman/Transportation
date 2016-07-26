package Samples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.codehaus.groovy.antlr.treewalker.SourceCodeTraversal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.*;

/**
 * This class is designed for handling interaction with user via console.
 *
 * Created by james.smith on 7/20/16.
 */
public class CourierSystem {

    /**
     * Scanner instance for reading data from System.in.
     */
    private static final Scanner reader = new Scanner(System.in);

    /**
     * Closes the Scanner and its underlying stream: System.in.
     */
    public static void closeScanner() {
        reader.close();
    }

    /**
     * Gets content of a file.
     * @param path - Type: String. String that contains path to a file.
     * @return - Type: String. File content.
     */
    public static String getFileContent(String path) {
        String fileContent = null;

        try {
            fileContent = new String (Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.err.println("An I/O exception");
            e.printStackTrace();
        }

        return fileContent;
    }

    /**
     * Reads, verifies and returns paths for files.
     * @return - Type: List<String>. Paths list.
     */
    public static List<String> readPaths() {
        List<String> pathsList = new ArrayList<>();

        System.out.println("Enter path to file or 'stop' to finish entering paths.\n" +
                "You can enter several paths separated by a comma (,).");
        while (true) {
            boolean flagStop = false;

            String[] lines = reader.nextLine().split(",");
            for (String str: lines) {
                String path = str.startsWith(" ") ? str.substring(1) : str;
                if (path.toLowerCase().equals("stop")) {
                    flagStop = true;
                    break;
                }
                else {
                    if (Files.exists(Paths.get(path))) {
                        pathsList.add(path);
                    }
                    else {
                        System.out.println("Wrong path: " + path + "\nPlease try again.");
                        break;
                    }
                }
            }
            if (flagStop) {
                break;
            }

        }

        return pathsList;
    }

    /**
     * Creates Departure object based on user's input.
     * @return - Type: Departure. Departure object created based on user's input.
     */
    public static Departure createCheckerDeparture() {
        final int MIN_YEAR = 1970;
        final int MIN_MONTH = 1;
        final int MAX_MONTH = 12;
        final int MIN_DAY = 1;
        final int MIN_HOUR = 0;
        final int MAX_HOUR = 23;
        final int MIN_MINUTE = 0;
        final int MAX_MINUTE = 59;

        int year;
        Month month;
        int day;
        int hour;
        int minute;

        while (true) {

            System.out.println("Enter year. Accepted values: [1970; ...]");
            year = reader.nextInt();
            boolean leapYear = false;
            if (year < MIN_YEAR) {
                System.out.println("Wrong value for year. Please try again.");
                System.out.println("Year: " + year);
                continue;
            } else {
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    leapYear = true;
                }
            }

            System.out.println("Enter month. Accepted values: [1;12]");
            int monthValue = reader.nextInt();
            if (monthValue < MIN_MONTH || monthValue > MAX_MONTH) {
                System.out.println("Wrong value for month. Please try again.");
                continue;
            }
            else {
                month = Month.of(monthValue);
            }

            System.out.println("Enter day. Accepted values: [1;31] but depends on the month.");
            day = reader.nextInt();
            int daysInMonth;
            if (monthValue == 4 || monthValue == 6 || monthValue == 9 || monthValue == 11) {
                daysInMonth = 30;
            }
            else {
                if (monthValue == 2) {
                    daysInMonth = (leapYear) ? 29 : 28;
                } else {
                    daysInMonth = 31;
                }
            }
            if (day < MIN_DAY || day > daysInMonth) {
                System.out.println("Wrong value for day. Please try again.");
                continue;
            }

            System.out.println("Enter hour. Accepted values: [0;23]");
            hour = reader.nextInt();
            if (hour < MIN_HOUR || hour > MAX_HOUR) {
                System.out.println("Wrong value for hour. Please try again.");
                continue;
            }

            System.out.println("Enter minute. Accepted values: [0;59]");
            minute = reader.nextInt();
            if (minute < MIN_MINUTE || minute > MAX_MINUTE) {
                System.out.println("Wrong value for minute. Please try again.");
                continue;
            }
            break;
        }

        return new Departure(year, month, day, hour, minute);
    }

    /**
     * Creates list of CourierSchedule objects from JSON files.
     * @param pathsList - Type: List<String>. List of strings with paths to files.
     * @return - Type: List<CourierSchedule>. List of created CourierSchedule objects.
     */
    public static List<CourierSchedule> getCourierScheduleObjectsFromJSON(List<String> pathsList) {
        List<CourierSchedule> objectsFromJSON = new ArrayList<>();
        Gson gsonSchedule = new GsonBuilder().setPrettyPrinting().create();

        for (String path: pathsList) {
            CourierSchedule obj = gsonSchedule.fromJson(getFileContent(path), CourierSchedule.class);
            objectsFromJSON.add(obj);
        }

        return objectsFromJSON;
    }

    public static void main(String[] args) {
        List<String> pathsList = readPaths();
        if (pathsList.isEmpty()) {
            System.out.println("Nothing to process. No path has been entered.\nQuiting...");
            System.exit(-1);
        }
        List<CourierSchedule> courierScheduleList = getCourierScheduleObjectsFromJSON(pathsList);
        //Departure checkDeparture = new Departure(2015, Month.JANUARY, 1, 0, 0);
        System.out.println("To find feature departures please fill in next information:\n");
        Departure checkDeparture = createCheckerDeparture();
        System.out.println("Searching for departures after:\n" + checkDeparture);

        for (CourierSchedule courierSchedule: courierScheduleList) {
            HashSet<Departure> checkSet0 = courierSchedule.findDepartureInSchedule(checkDeparture);
            if (!checkSet0.isEmpty()) {
                System.out.println("Found departures for courier: " + courierSchedule.getCourierName() + "\n" + checkSet0);
            } else {
                System.out.println("No available departures for courier: " + courierSchedule.getCourierName());
            }
        }

        closeScanner();
    }
}
