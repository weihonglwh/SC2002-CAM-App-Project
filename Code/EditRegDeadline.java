import java.util.Scanner;
import java.util.Date;

/**
 * This class is an implementation of the EditOperationForStaff interface
 * It is used to change the registration deadline of the camp
 * @see EditCampOperation
 */
public class EditRegDeadline implements EditCampOperation {

    /**
     * This method is used to change the registration deadline of the camp
     * @param camp The camp in which the registration deadline will be edited
     */
    public void perform(Camp camp) {

        // Check for null exception
        if (camp == null) {
            System.out.println("[ Camp object is null ]");
            return;
        }
        // Getting user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("[ Enter the new registration deadline (dd/mm/yyyy): ]");
        String regDeadlineString = scanner.nextLine();

        // Converting the user input to a Date object
        Date newRegDeadline = DateUtility.stringToDate(regDeadlineString);

        // Checking if the new registration deadline is valid
        // 1. Registration deadline cannot be after start date
        // 2. Registration deadline cannot be after end date
        // 3. Else, set the new registration deadline
        if(newRegDeadline != null && newRegDeadline.after(camp.getStartDate())){
            System.out.println("[ Registration deadline cannot be after start date ]");
        }
        else if(newRegDeadline != null && newRegDeadline.after(camp.getEndDate())){
            System.out.println("[ Registration deadline cannot be after end date ]");
        }
        else camp.setRegDeadline(newRegDeadline);
    }
}

