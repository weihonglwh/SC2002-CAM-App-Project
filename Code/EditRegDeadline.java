import java.util.Scanner;
import java.util.Date;

public class EditRegDeadline implements EditCampOperation {
    public void perform(Camp camp) {
        Scanner scanner = new Scanner(System.in);
        // Getting user input
        System.out.println("Enter the new registration deadline (dd/mm/yyyy): ");
        String regDeadlineString = scanner.nextLine();
        Date newRegDeadline = DateUtility.stringToDate(regDeadlineString);
        if(newRegDeadline.after(camp.getStartDate())){
            System.out.println("Regisration deadline cannot be after start date");
            return;
        }
        else if(newRegDeadline.after(camp.getEndDate())){
            System.out.println("Registration deadline cannot be after end date");
            return;
        }
        else camp.setRegDeadline(newRegDeadline);
        }
}
