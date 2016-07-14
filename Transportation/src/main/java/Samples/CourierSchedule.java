package Samples;

import java.lang.reflect.Array;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
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
        String fullSchedule = null;
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
     * @param minMonth - Type: int. Minimum range of the month to generate.
     * @param maxMonth - Type: int. Maximum range of the month to generate.
     * @param minDay - Type: int. Minimum range of the day to generate.
     * @param maxDay - Type: int. Maximum range of the day to generate.
     * @param minHour - Type: int. Minimum range of the hour to generate.
     * @param maxHour - Type: int. Maximum range of the hour to generate.
     * @param minMinute - Type: int. Minimum range of the minute to generate.
     * @param maxMinute - Type: int. Maximum range of the minute to generate.
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

        setCourierName("Default_name");
        setSchedule(randSchedule);
    }

    public static void main(String[] args) {
        /*
        Departure departure0 = new Departure(1963, Month.NOVEMBER, 22, 12, 30);
        Departure departure1 = new Departure(1966, Month.APRIL, 11, 2, 15);
        Departure departure2 = new Departure(1969, Month.JANUARY, 1, 3, 30);
        Departure departure3 = new Departure(1972, Month.JULY, 5, 5, 45);
        Departure departure4 = new Departure(1975, Month.FEBRUARY, 22, 17, 15);
        Departure departure5 = new Departure(1978, Month.SEPTEMBER, 11, 15, 30);
        Departure departure6 = new Departure(1981, Month.AUGUST, 23, 21, 45);
        Departure departure7 = new Departure(1984, Month.MARCH, 21, 1, 15);
        Departure departure8 = new Departure(1987, Month.MAY, 28, 16, 30);
        Departure departure9 = new Departure();

        HashSet<Departure> departureHashSet1 = new HashSet<>();
        HashSet<Departure> departureHashSet2 = new HashSet<>();

        departureHashSet1.add(departure0);
        departureHashSet1.add(departure1);
        departureHashSet1.add(departure2);
        departureHashSet1.add(departure3);
        departureHashSet1.add(departure4);

        departureHashSet2.add(departure5);
        departureHashSet2.add(departure6);
        departureHashSet2.add(departure7);
        departureHashSet2.add(departure8);
        departureHashSet2.add(departure9);

        CourierSchedule courier1 = new CourierSchedule("Johnny", departureHashSet1);
        CourierSchedule courier2 = new CourierSchedule("Leo", departureHashSet2);

        courier1.printCourierName();
        courier1.printSchedule();

        courier2.printCourierName();
        courier2.printSchedule();
        */

        CourierSchedule courierSchedule0 = new CourierSchedule();
        courierSchedule0.generateRandomSchedule(10, 2014, 2016, 1, 12, 1, 27, 0, 23, 0, 60);
        courierSchedule0.printCourierName();
        courierSchedule0.printSchedule();
    }
}
