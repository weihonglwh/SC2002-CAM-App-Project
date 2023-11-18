import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class DateUtility {
    public static Date stringToDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse(dateString);
            return date;
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter a date in the format dd/MM/yyyy");
        }
        return null;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static boolean checkOverlap(Date start1, Date end1, Date start2, Date end2) {
        try {
            if (start1 == null || end1 == null || start2 == null || end2 == null) {
                throw new IllegalArgumentException("Date ranges cannot be null");
            }
            // Check if either range is empty (start date is after end date)
            if (start1.after(end1) || start2.after(end2)) {
                throw new IllegalArgumentException("Invalid date ranges");
            }
            // Check for overlap
            return (start1.before(end2) || start1.equals(end2)) && (end1.after(start2) || end1.equals(start2));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
