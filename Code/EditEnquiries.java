import java.util.Scanner;

public class EditEnquiries implements EditOperationForStudent{
    public void perform(Enquiry e){
        Scanner sc = new Scanner(System.in);
        // Getting user input
        System.out.println("What is your new enquiry?");
        String newenq = sc.nextLine();
        e.setMessage(newenq);
        System.out.println("New enquiry has been saved");
    }
}
