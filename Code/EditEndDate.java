import java.util.Scanner;
import java.util.Date;

/**
 * This class is an implementation of the EditOperationForStaff interface
 * It is used to change the end date of the camp
 * @version 1.0
 * @since 2023-11-24
 * @see EditCampOperation
 */
public class EditEndDate implements EditCampOperation {

    /**
     * This method is used to change the end date of the camp
     * @param camp
     */
    public void perform(Camp camp) {

        // Getting user input for the new end date
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new end date (dd/mm/yyyy): ");
        String endDate = scanner.nextLine();

        // Converting the user input to a Date object
        Date enddate = DateUtility.stringToDate(endDate);

        // Checking if the new end date is valid
        // 1. End date cannot be before start date
        // 2. End date cannot be before registration deadline
        // 3. Else, set the new end date
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