import java.util.Date;
import java.text.SimpleDateFormat;


public class DateConverter {
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
}
