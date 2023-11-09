import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditRegDeadline implements EditOperation {
    public void perform(Camp camp) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Getting user input
            System.out.println("Enter the new registration deadline (yyyy-MM-dd): ");
            String regDeadlineString = scanner.nextLine();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date newRegDeadline = dateFormat.parse(regDeadlineString);
            camp.setRegDeadline(newRegDeadline);
        } catch (ParseException e) {
            System.out.println("Error parsing the date: " + e.getMessage());
        } finally {
                scanner.close();
        }
    }
}
