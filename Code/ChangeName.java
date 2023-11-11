import java.util.Scanner;

public class ChangeName implements EditOperationForStaff {
    public void perform(Camp camp) {
        Scanner scanner = new Scanner(System.in);
        // Getting user input
        System.out.println("Enter the new camp name: ");
        String name = scanner.nextLine();
        camp.setName(name);    
    }
}
