import java.util.Scanner;
import java.util.Date;

/**
 * This class is an implementation of the EditOperationForStaff interface.
 * It is used to change the start date of the camp.
 * @version 1.0
 * @since 2023-11-24
 * @see EditCampOperation
 */
public class EditStartDate implements EditCampOperation {

    /**
     * This method is used to change the start date of the camp.
     * @param camp The camp in which the start date will be edited.
     */
    public void perform(Camp camp) {

        // Check for null exception
        if (camp == null) {
            System.out.println("[ Camp object is null ]");
            return;
        }
        // Getting user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("[ Enter the new start date (dd/mm/yyyy): ]");
        String startDate = scanner.nextLine();

        // Converting the user input to a Date object
        Date startdate = DateUtility.stringToDate(startDate);

        // Checking if the new start date is valid
        // 1. Start date cannot be after end date
        // 2. Start date cannot be before registration deadline
        // 3. Else, set the new start date
        if(startdate != null && startdate.after(camp.getEndDate())){
            System.out.println("[ Start date cannot be after end date ]");
        }
        else if(startdate != null && startdate.before(camp.getRegDeadline())){
            System.out.println("[ Start date cannot be before registration deadline ]");
        }
        else camp.setStartDate(startdate);
    }
}

