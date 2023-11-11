import java.util.Scanner;

public class EditDescription implements EditOperationForStaff{
    public void perform(Camp camp){
        Scanner scanner = new Scanner(System.in);
        // Getting user input
        System.out.println("Enter the new description: ");
        String description = scanner.nextLine();
        camp.setDescription(description);
    }
}
