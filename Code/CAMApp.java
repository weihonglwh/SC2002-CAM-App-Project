import java.text.ParseException;
import java.util.Scanner;
import java.util.Objects;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CAMApp {
    public static void main(String[] args) {
        // init storages
        StudentStorage student_storage = new StudentStorage();
        StaffStorage staff_storage = new StaffStorage();
        SuggestionStorage suggestion_storage = new SuggestionStorage();
        EnquiryStorage enquiry_storage = new EnquiryStorage();
        CampStorage camp_storage = new CampStorage();
        // init readers
        CSVStudentReader studentReader = new CSVStudentReader();
        CSVStaffReader staffReader = new CSVStaffReader();
        CSVSuggestionReader suggestionReader = new CSVSuggestionReader();
        CSVEnquiryReader enquiryReader = new CSVEnquiryReader();
        // CSVCampReader campReader = new CSVCampReader();
        // populate initial storage
        studentReader.populateStorage(student_storage);
        staffReader.populateStorage(staff_storage);
        suggestionReader.populateStorage(suggestion_storage);
        enquiryReader.populateStorage(enquiry_storage);

        Scanner sc = new Scanner(System.in);
        boolean log_in_successfully = false;

        while (true) {
            log_in_successfully = false;
            int user_choice = 0;
            System.out.println("Please select who would you like to log in as: ");
            System.out.println("1) Staff");
            System.out.println("2) Student");
            System.out.println("3) Exit app");
            user_choice = sc.nextInt();
            while (user_choice != 1 && user_choice != 2 && user_choice != 3) {
                System.out.print("Error... Please select again");
                System.out.println("Please select who would you like to log in as: ");
                System.out.println("1) Staff");
                System.out.println("2) Student");
                System.out.println("3) Exit app");
                user_choice = sc.nextInt();
            }
            // this switch case is for user to log in.
            switch (user_choice) {

                case 1:
                    while (!log_in_successfully) {
                        System.out.println("Please enter your Staff Username");
                        String staff_user = sc.next();
                        System.out.println("Please enter your Staff Password");
                        String staff_password = sc.next();
                        StaffAccount staffAccount = staff_storage.getData(staff_user);
                        if (Objects.isNull(staffAccount)){
                            System.out.println("Wrong username or password");
                        }
                        else if (staffAccount.getPassword().equals(staff_password)) {
                            System.out.println("Login Successful!");
                            log_in_successfully = true;
                            // Since the user log in successfully, we will carry on with the staff operation
                            //print the menu for the staff
                            int staff_choice = 0;
                            System.out.println("Welcome " + staffAccount.getName());
                            while(staff_choice != 13) {
                                System.out.println("What would you like to do today?");
                                System.out.println("1) Create Camps");
                                System.out.println("2) Edit Camps");
                                System.out.println("3) Delete Camps");
                                System.out.println("4) View all Camps");
                                System.out.println("5) View camps created by you");
                                System.out.println("6) View Enquires by students");
                                System.out.println("7) Reply to enquires by students");
                                System.out.println("8) View Suggestions to camp");
                                System.out.println("9) Approve/Reject Suggestions");
                                System.out.println("10) View attendees of a camp");
                                System.out.println("11) View camp committee of a camp");
                                System.out.println("12) Generate a performance report of the camp committee members");
                                System.out.println("13) Log out");
                                staff_choice = sc.nextInt();

                                switch(staff_choice){
                                    case 1:
                                        System.out.println("Please enter the name of the camp");
                                        String name = sc.next();
                                        while(!Objects.isNull(camp_storage.getData(name)))
                                        {
                                            System.out.println("Camp name exists... Please select another name");
                                            System.out.println("Please enter the name of the camp");
                                            name = sc.next();
                                        }
                                        Date startDate = null;
                                        Date endDate = null;
                                        Date regDeadline = null;
                                        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                                        System.out.println("Please enter the start date (MM/dd/yyyy)");
                                        String startDateString = sc.next();
                                        try {
                                            startDate = format.parse(startDateString);
                                        } catch (ParseException e){
                                            System.out.print(("Invalid date format. Please enter a date in the format MM/dd/yyyy"));
                                        }
                                        System.out.println("Please enter the end date (MM/dd/yyyy)");
                                        String endDateString = sc.next();
                                        try{
                                            endDate = format.parse(endDateString);
                                        } catch(ParseException e){
                                            System.out.print(("Invalid date format. Please enter a date in the format MM/dd/yyyy"));
                                        }
                                        System.out.println("Please enter the registration deadline (MM/dd/yyyy)");
                                        String regDateString = sc.next();
                                        try{
                                            regDeadline = format.parse(regDateString);
                                        } catch(ParseException e){
                                            System.out.print(("Invalid date format. Please enter a date in the format MM/dd/yyyy"));
                                        }
                                        System.out.println("Please enter the target group");
                                        String userGroup = sc.next();
                                        System.out.println("Please enter the location");
                                        String location = sc.next();
                                        System.out.println("Please enter the number of slots for this camp");
                                        int totalSlots = sc.nextInt();
                                        System.out.println("Please enter the number of Camp Committee slots for this camp");
                                        int campCommSlots = sc.nextInt();
                                        System.out.println("Please enter a description for the camp");
                                        String description = sc.next();
                                        System.out.println("Do you want this camp to be visible?(Y/N)");
                                        String visibility_selection = sc.next();
                                        boolean visibility;
                                        while(!(visibility_selection.equals("y")) && !(visibility_selection.equals("Y")) && !(visibility_selection.equals("N")) && !(visibility_selection.equals("n")))
                                        {
                                            System.out.println("Invalid choice. Please select again");
                                            System.out.println("Do you want this camp to be visible?(Y/N)");
                                            visibility_selection = sc.next();
                                        }
                                        if(visibility_selection.equals("y") || visibility_selection.equals("Y")){
                                            visibility = true;
                                        }
                                        else{
                                            visibility = false;
                                        }

                                        Camp newCamp = new Camp(name, startDate, endDate, regDeadline, userGroup, location, totalSlots, campCommSlots, description, staffAccount.getName(), visibility);
                                        System.out.println("Camp created successfully");
                                        camp_storage.addItem(newCamp);
                                        System.out.println("Camp added to storage");
                                        break;

                                    case 2:
                                        System.out.println("do case 2");
                                        break;
                                    case 3:
                                        System.out.println("Please enter the name of the camp that you wish to remove");
                                        String tarCamp = sc.next();
                                        Camp tarcamp_obj = camp_storage.getData(tarCamp);
                                        if(tarcamp_obj.getStaffIC().equals(staffAccount.getName())){
                                            camp_storage.removeItem(tarcamp_obj);
                                            System.out.println("Camp " + tarCamp +" removed successfully");
                                        }
                                        else{
                                            System.out.println("Error removing... you are not the staff in charge of this camp.");
                                        }
                                        break;
                                    case 4:
                                        camp_storage.printData();
                                        break;
                                    case 5:
                                        ArrayList<Camp> listOfCamps = camp_storage.getData();
                                        for (Camp camp: listOfCamps){
                                            if(camp.getStaffIC().equals(staffAccount.getName())){
                                                camp_storage.printData(camp);
                                            }
                                        }
                                        break;
                                    case 6:
                                        System.out.println("do case 6");
                                        break;
                                    case 7:
                                        System.out.println("do case 7");
                                        break;
                                    case 8:
                                        System.out.println("do case 8");
                                        break;
                                    case 9:
                                        System.out.println("do case 9");
                                        break;
                                    case 10:
                                        System.out.println("do case 10");
                                        break;
                                    case 11:
                                        System.out.println("do case 11");
                                        break;
                                    case 12:
                                        System.out.println("do case 12");
                                        break;
                                    case 13:
                                        System.out.println("Goodbye " + staffAccount.getName() + " !");
                                        break;
                                }
                            }

                        } else {
                            System.out.println("Wrong username or password");
                        }
                    }

                    break;
                case 2:
                    while (!log_in_successfully) {
                        System.out.println("Please enter your Student Username");
                        String studentUser = sc.next();
                        System.out.println("Please enter your Student Password");
                        String studentPassword = sc.next();
                        StudentAccount studentAccount = student_storage.getData(studentUser);
                        if(Objects.isNull(studentAccount))
                        {
                            System.out.println("Wrong username or password");
                        }
                        else if (studentAccount.getPassword().equals(studentPassword)) {
                            System.out.println("Login Successful!");
                            log_in_successfully = true;
                        } else {
                            System.out.println("Wrong username or password");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Goodbye!");
                    System.exit(1);

            }
        }
    }
}
