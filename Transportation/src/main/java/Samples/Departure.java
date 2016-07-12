package Samples;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * This class is designed for holding departure's
 * date and time values of a specific currier.
 *
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
     *
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
     *
     * @return - string with next format:
     *              Date: mm/dd/yyyy
     *              Time: hh:mm
     */
    @Override
    public String toString() {
        return "Date: " + dateTime.getMonthValue() + "/" + dateTime.getDayOfMonth() + "/" +
                dateTime.getYear() + "\nTime: " + dateTime.getHour() + ":" + dateTime.getMinute();
    }

    public static void main(String[] args) {

        /*
        LocalDateTime date = LocalDateTime.now();
        DayOfWeek dow = date.getDayOfWeek();

        System.out.println("Day: " + dow);

        String dayName = dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        System.out.println(dayName);
        System.out.println(date.getHour() + ":" + date.getMinute());
        */

        Departure departure1 = new Departure(2016, Month.JULY, 12, 13, 44);
        System.out.println(departure1);

        Departure departure2 = new Departure();
        System.out.println(departure2);
    }

}
