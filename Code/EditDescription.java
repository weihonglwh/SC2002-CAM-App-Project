import java.util.Scanner;

/**
 * This class is an implementation of the EditOperationForStaff interface.
 * It is used to change the description of the camp.
 * @version 1.0
 * @since 2023-11-24
 * @see EditCampOperation
 */
public class EditDescription implements EditCampOperation {

    /**
     * This method is used to change the description of the camp.
     * @param camp The camp in which the description will be edited.
     */
    public void perform(Camp camp){

        // Check for null exception
        if (camp == null) {
            System.out.println("[ Camp object is null ]");
            return;
        }
        // Getting user input for the new description
        Scanner scanner = new Scanner(System.in);
        System.out.println("[ Enter the new description: ]");
        String description = scanner.nextLine();

        // Setting the new description
        camp.setDescription(description);
        System.out.println("[ Description changed successfully ]");
    }
}
