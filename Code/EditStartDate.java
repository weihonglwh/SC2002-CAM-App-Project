import java.util.Scanner;
import java.util.Date;

public class EditStartDate implements EditOperationForStaff {
    public void perform(Camp camp) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new start date (dd/mm/yyyy): ");
        String startDate = scanner.nextLine();
        Date startdate = DateConverter.stringToDate(startDate);
        if(startdate.after(camp.getEndDate())){
            System.out.println("Start date cannot be after end date");
            return;
        }
        else if(startdate.before(camp.getRegDeadline())){
            System.out.println("Start date cannot be before registration deadline");
            return;
        }
        else camp.setStartDate(startdate);
    }
}

