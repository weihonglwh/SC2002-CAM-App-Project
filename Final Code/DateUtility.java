import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * DateUtility is a utility class that contains static methods regarding date objects.
 * @version 1.0
 * @since 2023-11-24
 */
public class DateUtility {
    /**
     * Converts a string to a date object.
     * @param dateString The string to be converted.
     * @return A date object if the string is in the correct format, null otherwise.
     */
    public static Date stringToDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(dateString);
        } catch (Exception e) {
            System.out.println("[ Invalid date format. Please enter a date in the format dd/MM/yyyy ]");
            System.out.print(">> ");
        }
        return null;
    }

    /**
     * Converts a date object to a string.
     * @param date The date object to be converted.
     * @return A string if the date object is not null, null otherwise.
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if (date == null) {
            System.out.println("[ Date object is null ]");
            return null;
        }
        else {
            return formatter.format(date);
        }
    }

    /**
     * Checks if two date ranges overlap.
     * @param firstStart Start date of first date range.
     * @param firstEnd End date of first date range
     * @param secondStart Start date of second date range.
     * @param secondEnd End date of second date range.
     * @return True if the date ranges overlap, false otherwise.
     */
    public static boolean checkOverlap(Date firstStart, Date firstEnd, Date secondStart, Date secondEnd) {
        try {
            if (firstStart == null || firstEnd == null || secondStart == null || secondEnd == null) {
                throw new IllegalArgumentException("Date ranges cannot be null");
            }
            // Check if either range is empty (start date is after end date)
            if (firstStart.after(firstEnd) || secondStart.after(secondEnd)) {
                throw new IllegalArgumentException("Invalid date ranges");
            }
            // Check for overlap
            return (firstStart.before(secondEnd) || firstStart.equals(secondEnd)) && (firstEnd.after(secondStart) || firstEnd.equals(secondStart));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
