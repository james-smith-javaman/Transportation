package Samples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Month;
import java.util.HashSet;
import java.util.Random;


/**
 * This class is designed for storing information about courier and his schedule.
 * Created by james.smith on 7/13/16.
 */
public class CourierSchedule {

    String courierName;

    /**
     * Schedule is a set of Departure objects.
     */
    HashSet<Departure> schedule;

    /**
     * Default constructor.
     */
    public CourierSchedule() {
        courierName = null;
        schedule = new HashSet<>();
    }

    /**
     * Creates an object with the specified name and allocates memory for the schedule.
     * @param courierName - Type: String. Name of the courier.
     */
    public CourierSchedule(String courierName) {
        this.courierName = courierName;
        schedule = new HashSet<>();
    }

    /**
     * Creates an object with the specified name and specified schedule.
     * @param courierName - Type: String. Name of the courier.
     * @param schedule - Type: HashSet<Departure>. Schedule of the courier.
     */
    public CourierSchedule(String courierName, HashSet<Departure> schedule) {
        this.courierName = courierName;
        this.schedule = schedule;
    }

    /**
     * Prints name of the courier.
     */
    public void printCourierName() {
        System.out.println(courierName);
    }

    /**
     * Prints schedule of the courier.
     */
    public void printSchedule() {
        for (Departure dep: schedule) {
            System.out.println(dep);
        }
    }

    /**
     * Returns name of the courier.
     * @return - Type: String. Name of the courier.
     */
    public String getCourierName() {
        return courierName;
    }

    /**
     * Returns schedule of the courier.
     * @return - Type: HashSet<Departure>. Schedule of the courier.
     */
    public HashSet<Departure> getSchedule() {
        return schedule;
    }

    /**
     * Sets name of the courier.
     * @param courierName - Type: String.
     */
    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    /**
     * Sets schedule of the courier.
     * @param schedule - Type: HashSet<Departure>.
     */
    public void setSchedule(HashSet<Departure> schedule) {
        this.schedule = schedule;
    }

    /**
     * Overrides Object.toString() method.
     * @return - Type: String. Format:
     *              Name: courierName
     *              Date: mm/dd/yyyy
     *              Time: hh:mm
     */
    @Override
    public String toString() {
        String fullSchedule = "";
        for (Departure dep: schedule) {
            fullSchedule += dep.toString();
        }
        return "Name: " + courierName + "\n" + fullSchedule + "\n";
    }

    /**
     * Adds departure to the courier's schedule.
     * @param departure - Type: Departure.
     */
    public void addDeparture(Departure departure) {
        schedule.add(departure);
    }

    /**
     * Removes departure from the schedule.
     * @param dep - Type: Departure. Element to delete from the schedule.
     * @return - Type: boolean. True if the set contained the specified element
     */
    public boolean removeDeparture(Departure dep) {
        return schedule.remove(dep);
    }

    /**
     * Generates random int number in the range between 'min' and 'max'.
     * @param min - lower range.
     * @param max - higher range.
     * @return - Type: int. Random int value in the range between 'min' and 'max'.
     * @throws IllegalArgumentException if 'min' is equals or bigger then 'max'.
     */
    public int randomInt(int min, int max) throws IllegalArgumentException {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates schedule set of the specified size with random Departure objects in it.
     * @param size - Type: int. Size of the schedule set.
     * @param minYear - Type: int. Minimum range of the year to generate.
     * @param maxYear - Type: int. Maximum range of the year to generate.
     * @param minMonth - Type: int. Minimum range of the month to generate. Accepts values [1;12].
     * @param maxMonth - Type: int. Maximum range of the month to generate. Accepts values [1;12].
     * @param minDay - Type: int. Minimum range of the day to generate. Accepts values [1;31] but depends on the month.
     * @param maxDay - Type: int. Maximum range of the day to generate. Accepts values [1;31] but depends on the month.
     * @param minHour - Type: int. Minimum range of the hour to generate. Accepts values [0;23].
     * @param maxHour - Type: int. Maximum range of the hour to generate. Accepts values [0;23].
     * @param minMinute - Type: int. Minimum range of the minute to generate. Accepts values [0;59].
     * @param maxMinute - Type: int. Maximum range of the minute to generate. Accepts values [0;59].
     */
    public void generateRandomSchedule(int size, int minYear, int maxYear, int minMonth, int maxMonth,
                                       int minDay, int maxDay, int minHour, int maxHour, int minMinute, int maxMinute) {
        HashSet<Departure> randSchedule = new HashSet<>();
        int randYear = 0;
        int randMonthValue;
        Month randMonth = null;
        int randDay = 0;
        int randHour = 0;
        int randMinute = 0;

        for (int i = 0; i != size; ++i) {
            try {
                randYear = randomInt(minYear, maxYear);
                randMonthValue = randomInt(minMonth, maxMonth);
                randMonth = Month.of(randMonthValue);
                randDay = randomInt(minDay, maxDay);
                randHour = randomInt(minHour, maxHour);
                randMinute = randomInt(minMinute, maxMinute);
            } catch (IllegalArgumentException e) {
                System.err.println("Wrong ranges during generating random schedule.");
                e.printStackTrace();
            }

            randSchedule.add(new Departure(randYear, randMonth, randDay, randHour, randMinute));
        }

        setSchedule(randSchedule);
    }

    public HashSet<Departure> findDepartureInSchedule(Departure findDeparture) {
        HashSet<Departure> departures = new HashSet<>();

        for (Departure dep : schedule) {

            if (dep.getYear() < findDeparture.getYear()) {
                continue;
            } else if (dep.getYear() > findDeparture.getYear()) {
                departures.add(dep);
                continue;
            }
            if (dep.getMonthValue() < findDeparture.getMonthValue()) {
                continue;
            } else if (dep.getMonthValue() > findDeparture.getMonthValue()) {
                departures.add(dep);
                continue;
            }
            if (dep.getDayOfMonth() < findDeparture.getDayOfMonth()) {
                continue;
            } else if (dep.getDayOfMonth() > findDeparture.getDayOfMonth()) {
                departures.add(dep);
                continue;
            }
            if (dep.getHour() < findDeparture.getHour()) {
                continue;
            } else if (dep.getHour() > findDeparture.getHour()) {
                departures.add(dep);
                continue;
            }
            if (dep.getMinute() >= findDeparture.getMinute()) {
                departures.add(dep);
            }
        }

        return departures;
    }

    public static void main(String[] args) {
        CourierSchedule courierSchedule0 = new CourierSchedule();
        courierSchedule0.setCourierName("courierSchedule0");

        Departure departure0 = new Departure(2014, Month.NOVEMBER, 22, 12, 30);
        Departure departure1 = new Departure(2014, Month.APRIL, 11, 2, 15);
        Departure departure2 = new Departure(2014, Month.JANUARY, 1, 3, 30);
        Departure departure3 = new Departure(2014, Month.JULY, 5, 5, 45);
        Departure departure4 = new Departure(2015, Month.FEBRUARY, 2, 17, 15);
        Departure departure5 = new Departure(2015, Month.MARCH, 3, 15, 30);
        Departure departure6 = new Departure(2015, Month.APRIL, 4, 21, 45);
        Departure departure7 = new Departure(2016, Month.MAY, 5, 1, 15);
        Departure departure8 = new Departure(2016, Month.JUNE, 6, 16, 30);

        Departure checkDeparture = new Departure(2015, Month.JANUARY, 1, 0, 0);
        Departure checkEmptyDeparture = new Departure(2018, Month.JANUARY, 1, 0, 0);

        courierSchedule0.addDeparture(departure0);
        courierSchedule0.addDeparture(departure1);
        courierSchedule0.addDeparture(departure2);
        courierSchedule0.addDeparture(departure3);
        courierSchedule0.addDeparture(departure4);
        courierSchedule0.addDeparture(departure5);
        courierSchedule0.addDeparture(departure6);
        courierSchedule0.addDeparture(departure7);
        courierSchedule0.addDeparture(departure8);

        //System.out.println("Departures after: \n" + checkDeparture);


        CourierSchedule courierSchedule1 = new CourierSchedule();
        courierSchedule1.setCourierName("courierSchedule1");
        courierSchedule1.generateRandomSchedule(20, 2014, 2016, 1, 12, 1, 27, 0, 23, 0, 59);
        CourierSchedule courierSchedule2 = new CourierSchedule();
        courierSchedule2.setCourierName("courierSchedule2");
        courierSchedule2.generateRandomSchedule(30, 2013, 2017, 1, 12, 1, 27, 0, 23, 0, 59);
        CourierSchedule courierSchedule3 = new CourierSchedule();
        courierSchedule3.setCourierName("courierSchedule3");
        courierSchedule3.generateRandomSchedule(30, 2010, 2014, 1, 12, 1, 27, 0, 23, 0, 59);

        HashSet<Departure> checkSet0 = courierSchedule0.findDepartureInSchedule(checkDeparture);
        HashSet<Departure> checkSet1 = courierSchedule1.findDepartureInSchedule(checkDeparture);
        HashSet<Departure> checkSet2 = courierSchedule2.findDepartureInSchedule(checkDeparture);
        HashSet<Departure> checkSet3 = courierSchedule3.findDepartureInSchedule(checkDeparture);
        /*
        if (!checkSet0.isEmpty()) {
            System.out.println("Found departures for courier: " + courierSchedule0.getCourierName() + "\n" + checkSet0);
        }  else {
            System.out.println("courierSchedule0 empty.");
            courierSchedule0.printSchedule();
        }
        if (!checkSet1.isEmpty()) {
            System.out.println("Found departures for courier: " + courierSchedule1.getCourierName() + "\n" + checkSet1);
        } else {
            System.out.println("courierSchedule1 empty.");
            courierSchedule1.printSchedule();
        }
        if (!checkSet2.isEmpty()) {
            System.out.println("Found departures for courier: " + courierSchedule2.getCourierName() + "\n" + checkSet2);
        } else {
            System.out.println("courierSchedule2 empty.");
            courierSchedule2.printSchedule();
        }
        if (!checkSet3.isEmpty()) {
            System.out.println("Found departures for courier: " + courierSchedule3.getCourierName() + "\n" + checkSet3);
        } else {
            System.out.println("courierSchedule3 empty.");
            courierSchedule3.printSchedule();
        }
        */



        Gson gsonSchedule = new GsonBuilder().setPrettyPrinting().create();
        //Gson gsonDeparture = new Gson();
        System.out.println(gsonSchedule.toJson(courierSchedule3));

        //CourierSchedule fromJSONFile = gsonSchedule.fromJson(TryFileIO.getFileContent(""), CourierSchedule.class);
    }
}
