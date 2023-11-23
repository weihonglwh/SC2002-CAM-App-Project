import java.util.Scanner;

public class EditLocation implements EditCampOperation {
    public void perform(Camp camp) {
        Scanner scanner = new Scanner(System.in);
        // Getting user input
        System.out.println("Enter the new location: ");
        String location = scanner.nextLine();
        camp.setLocation(location);
        System.out.println("Location changed successfully");
    }
    
}
