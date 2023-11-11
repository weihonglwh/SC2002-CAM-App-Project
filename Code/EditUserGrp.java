import java.util.Scanner;

public class EditUserGrp implements EditOperationForStaff{
    public void perform(Camp camp){
        Scanner scanner = new Scanner(System.in);
        // Getting user input
        System.out.println("Enter the new user group: ");
        String userGrp = scanner.nextLine();
        if(camp.getAttendees() == null && camp.getCampComms()==null){
            camp.setUserGroup(userGrp);
            System.out.println("User group changed successfully");
        }
        else{
            System.out.println("Cannot change user group as it has attendees or camp committee members");
        }
       
    }
    
}
