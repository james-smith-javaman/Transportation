package Samples;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;

/**
 * This class is designed for holding departure's
 * date and time values for a specific currier.
 * Created by james.smith on 7/12/16.
 */
public class Departure {

    LocalDateTime dateTime;

    /**
     * Creates a new instance of the LocalDateTime class with current date and time.
     */
    public Departure() {
        dateTime = LocalDateTime.now();
    }

    /**
     * Creates a new instance of the LocalDateTime class with specified parameters.
     * @param year - year;
     * @param month - month (Month enum);
     * @param dayOfMonth - day of month;
     * @param hour - hour;
     * @param minute - minute.
     */
    public Departure (int year, Month month, int dayOfMonth, int hour, int minute) {
        dateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    /**
     * Overrides Object.toString() method.
     * @return - Type: String. Format:
     *              Date: mm/dd/yyyy
     *              Time: hh:mm
     */
    @Override
    public String toString() {
        return "\nDate: " + dateTime.getMonthValue() + "/" + dateTime.getDayOfMonth() + "/" +
                dateTime.getYear() + "\nTime: " + dateTime.getHour() + ":" + dateTime.getMinute();
    }

    /**
     * Returns name of the day.
     * @return - Type: String. Representation of the day name.
     */
    public String getDay() {
        return dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    /**
     * Returns day of month.
     * @return - Type: int. Value of day in month.
     */
    public int getDayOfMonth() {
        return dateTime.getDayOfMonth();
    }

    /**
     * Returns month.
     * @return - Type: Month. Month.
     */
    public Month getMonth() {
        return dateTime.getMonth();
    }

    /**
     * Returns numeric representation of the month.
     * @return - Type: int. Value of the month.
     */
    public int getMonthValue() {
        return dateTime.getMonthValue();
    }

    /**
     * Returns year.
     * @return - Type: int. Year.
     */
    public int getYear() {
        return dateTime.getYear();
    }

    /**
     * Returns hour.
     * @return - Type: int. Hour.
     */
    public int getHour() {
        return dateTime.getHour();
    }

    /**
     * Returns minute.
     * @return - Type: int. Minute.
     */
    public int getMinute() {
        return dateTime.getMinute();
    }


    /**
     * Returns LocalDateTime.
     * @return - Type: LocalDateTime.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets LocalDateTime.
     * @param dateTime - Type: LocalDateTime.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static void main(String[] args) {

        Departure departure1 = new Departure(1963, Month.NOVEMBER, 22, 12, 30);
        System.out.println(departure1);

        Departure departure2 = new Departure();
        System.out.println(departure2);

        System.out.println("departure1 info:");
        System.out.println("Day: " + departure1.getDay() + ", " + departure1.getDayOfMonth());
        System.out.println("Month: " + departure1.getMonth());
        System.out.println("Year: " + departure1.getYear());
        System.out.println("Hour: " + departure1.getHour());
        System.out.println("Minute: " + departure1.getMinute());

        System.out.println("departure2 info:");
        System.out.println("Day: " + departure2.getDay() + ", " + departure2.getDayOfMonth());
        System.out.println("Month: " + departure2.getMonth());
        System.out.println("Year: " + departure2.getYear());
        System.out.println("Hour: " + departure2.getHour());
        System.out.println("Minute: " + departure2.getMinute());

        HashSet<Departure> departureHashSet = new HashSet<>();

        departureHashSet.add(departure1);
        departureHashSet.add(departure2);

        System.out.println("Printing from HashSet and via enhanced for loop:");
        for (Departure dep: departureHashSet) {
            System.out.println(dep);
        }
    }

}
