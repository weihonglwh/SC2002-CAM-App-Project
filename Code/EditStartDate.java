import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditStartDate implements EditOperation {
    public void perform(Camp camp) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Getting user input
            System.out.println("Enter the new start date (yyyy-MM-dd): ");
            String startDate = scanner.nextLine();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDateParsed = dateFormat.parse(startDate);
            camp.setStartDate(startDateParsed);
        } catch (ParseException e) {
            System.out.println("Error parsing the date: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

