import java.util.Scanner;
import java.util.Date;

public class EditEndDate implements EditOperationForStaff {
    public void perform(Camp camp) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new end date (dd/mm/yyyy): ");
        String endDate = scanner.nextLine();
        Date enddate = DateConverter.stringToDate(endDate);
        if(enddate.before(camp.getStartDate())){
            System.out.println("End date cannot be before start date");
            return;
        }
        else if(enddate.before(camp.getRegDeadline())){
            System.out.println("End date cannot be before registration deadline");
            return;
        }
        else camp.setEndDate(enddate);
    }
}