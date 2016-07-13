package Samples;

import java.lang.reflect.Array;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * This class is designed for storing information about courier and his schedule.
 *
 * Created by tarasmotyl on 7/13/16.
 */
public class CourierSchedule {

    String courierName;
    HashSet<Departure> schedule;

    public CourierSchedule() {
        courierName = null;
        schedule = new HashSet<>();
    }

    public CourierSchedule(String courierName) {
        this.courierName = courierName;
        schedule = new HashSet<>();
    }

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
     *
     * @return - Type: String. Name of the courier.
     */
    public String getCourierName() {
        return courierName;
    }

    /**
     * Returns schedule of the courier.
     *
     * @return - Type: HashSet<Departure>. Schedule of the courier.
     */
    public HashSet<Departure> getSchedule() {
        return schedule;
    }

    /**
     * Sets name of the courier.
     *
     * @param courierName - Type: String.
     */
    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    /**
     * Sets schedule of the courier.
     *
     * @param schedule - Type: HashSet<Departure>.
     */
    public void setSchedule(HashSet<Departure> schedule) {
        this.schedule = schedule;
    }

    /**
     * Overrides Object.toString() method.
     *
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
     *
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

    public static void main(String[] args) {
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
    }
}
