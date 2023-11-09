import java.util.Scanner;

public class EditUserGrp implements EditOperation{
    public void perform(Camp camp){
        Scanner scanner = new Scanner(System.in);
        // Getting user input
        System.out.println("Enter the new user group: ");
        String userGrp = scanner.nextLine();
        camp.setUserGroup(userGrp);
        scanner.close();
    }

}
