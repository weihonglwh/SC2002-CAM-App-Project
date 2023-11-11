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
        CSVCampReader campReader = new CSVCampReader();
        // populate initial storage
        studentReader.populateStorage(student_storage);
        staffReader.populateStorage(staff_storage);
        suggestionReader.populateStorage(suggestion_storage);
        enquiryReader.populateStorage(enquiry_storage);
        campReader.populateStorage(camp_storage);

        Scanner sc = new Scanner(System.in);
        Scanner sentenceSc = new Scanner(System.in).useDelimiter("\\n");
        boolean log_in_successfully = false;
        ArrayList<Camp> listOfCamps;

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
                            while(staff_choice != 14) {
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
                                System.out.println("13) Change password");
                                System.out.println("14) Log out");
                                staff_choice = sc.nextInt();

                                switch(staff_choice){
                                    case 1:
                                        System.out.println("Please enter the name of the camp");
                                        String name = sentenceSc.next();
                                        while(!Objects.isNull(camp_storage.getData(name)))
                                        {
                                            System.out.println("Camp name exists... Please select another name");
                                            System.out.println("Please enter the name of the camp");
                                            name = sentenceSc.next();
                                        }
                                        Date startDate = null;
                                        Date endDate = null;
                                        Date regDeadline = null;
                                        System.out.println("Please enter the start date (dd/MM/yyyy)");
                                        String startDateString = sc.next();
                                        while (startDate == null) {
                                            startDate = DateConverter.stringToDate(startDateString);
                                            if (startDate == null) {
                                                startDateString = sc.next();
                                            }
                                        }
                                        System.out.println("Please enter the end date (dd/MM/yyyy)");
                                        String endDateString = sc.next();
                                        while (endDate == null) {
                                            endDate = DateConverter.stringToDate(endDateString);
                                            if (endDate == null) {
                                                endDateString = sc.next();
                                            }
                                        }
                                        System.out.println("Please enter the registration deadline (dd/MM/yyyy)");
                                        String regDeadlineString = sc.next();
                                        while (regDeadline == null) {
                                            regDeadline = DateConverter.stringToDate(regDeadlineString);
                                            if (regDeadline == null) {
                                                regDeadlineString = sc.next();
                                            }
                                        }
                                        System.out.println("Please enter the target group");
                                        String userGroup = sc.next();
                                        System.out.println("Please enter the location");
                                        String location = sentenceSc.next();
                                        System.out.println("Please enter the number of slots for this camp");
                                        int totalSlots = sc.nextInt();
                                        System.out.println("Please enter the number of Camp Committee slots for this camp");
                                        int campCommSlots = sc.nextInt();
                                        System.out.println("Please enter a description for the camp");
                                        String description = sentenceSc.next();
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
                                        System.out.println("Please enter the name of the camp that you wish to edit");
                                        String editCamp = sc.next();
                                        Camp editcamp_obj = camp_storage.getData(editCamp);
                                        if(editcamp_obj.getStaffIC().equals(staffAccount.getName())){
                                            int staff_editchoice = 0;
                                            while(staff_editchoice != 9) {
                                                System.out.println("Please select what you would like to edit");
                                                System.out.println("1) Edit Name");
                                                System.out.println("2) Edit start date");
                                                System.out.println("3) Edit end date");
                                                System.out.println("4) Edit registration deadline");
                                                System.out.println("5) Edit user group");
                                                System.out.println("6) Edit location");
                                                System.out.println("7) Edit description");
                                                System.out.println("8) Toggle visibility");
                                                System.out.println("9) Exit edit page");
                                                staff_editchoice = sc.nextInt();

                                                switch(staff_editchoice){
                                                    case 1:
                                                        ChangeName changeName = new ChangeName();
                                                        changeName.perform(editcamp_obj);
                                                        System.out.println("Name changed successfully");
                                                        break;

                                                    case 2:
                                                        EditStartDate changeStartDate = new EditStartDate();
                                                        changeStartDate.perform(editcamp_obj);
                                                        break;
                                                    
                                                    case 3:
                                                        EditEndDate changeEndDate = new EditEndDate();
                                                        changeEndDate.perform(editcamp_obj);
                                                        break;
                                                    
                                                    case 4:
                                                        EditRegDeadline changeRegDeadline = new EditRegDeadline();
                                                        changeRegDeadline.perform(editcamp_obj);
                                                        break;

                                                    case 5:
                                                        EditUserGrp changeUserGroup = new EditUserGrp();
                                                        changeUserGroup.perform(editcamp_obj);

                                                        break;
                                                    
                                                    case 6:
                                                        EditLocation changeLocation = new EditLocation();
                                                        changeLocation.perform(editcamp_obj);
                                                        System.out.println("Location changed successfully");
                                                        break;

                                                    case 7:
                                                        EditDescription changeDescription = new EditDescription();
                                                        changeDescription.perform(editcamp_obj);
                                                        System.out.println("Description changed successfully");
                                                        break;
                                                    
                                                    case 8:
                                                        ToggleVisibility toggleVisibility = new ToggleVisibility();
                                                        toggleVisibility.perform(editcamp_obj);
                                                        break;

                                                    case 9:
                                                        System.out.println("Exiting edit page");
                                                    break;

                                                    default:
                                                    System.out.println("Invalid choice. Please enter a valid option.");
                                                    break;
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("Error... you are not the staff in charge of t1his camp.");
                                        }

                                        break;
                                    case 3:
                                        System.out.println("Please enter the name of the camp that you wish to remove");
                                        String tarCamp = sentenceSc.next();
                                        Camp tarcamp_obj = camp_storage.getData(tarCamp);
                                        if (tarCamp == null){
                                            System.out.println("Camp do not exist");
                                        }
                                        else{ 
                                        if(tarcamp_obj.getStaffIC().equals(staffAccount.getName())){
                                            camp_storage.removeItem(tarcamp_obj);
                                            System.out.println("Camp " + tarCamp +" removed successfully");
                                        }
                                        else{
                                            System.out.println("Error removing... you are not the staff in charge of this camp.");
                                        }
                                    }
                                        break;
                                    case 4:
                                        camp_storage.printData();
                                        break;
                                    case 5:
                                        listOfCamps = camp_storage.getData();
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
                                        System.out.println("Please enter the new password: ");
                                        String userNewPassword = sc.next();
                                        staffAccount.setPassword(userNewPassword);
                                        System.out.println("Password Changed Successfully");
                                        break;
                                    case 14:
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
                            int student_choice = 0;
                            System.out.println("Welcome " + studentAccount.getName());
                            while(student_choice != 12)
                            {
                                System.out.println("What would you like to do today");
                                System.out.println("1) View list of camp available");
                                System.out.println("2) View the remaining slots for a camp");
                                System.out.println("3) Register for camp");
                                System.out.println("4) Register for camp committee");
                                System.out.println("5) Submit enquiries about a camp");
                                System.out.println("6) View enquires made");
                                System.out.println("7) Edit enquires made");
                                System.out.println("8) Delete enquires made");
                                System.out.println("9) See camps registered");
                                System.out.println("10) Withdraw from camp");
                                System.out.println("11) Change password");
                                System.out.println("12) Log out");
                                student_choice = sc.nextInt();
                                switch(student_choice){
                                    case 1:
                                        listOfCamps = camp_storage.getData();
                                        for(Camp c : listOfCamps){
                                            if(c.getUserGroup().equals(studentAccount.getFaculty()) && c.getVisibility())
                                            {
                                                camp_storage.printData(c);
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Which camp would you like to enquire?");
                                        String userCamp = sentenceSc.next();
                                        if (camp_storage.getData(userCamp) == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                        System.out.println("Total slots reminding is " + camp_storage.getData(userCamp).getRemindingSlots());
                                    }
                                        break;
                                    case 3:
                                        System.out.println("Which camp would you like to register for?");
                                        String userRegCamp = sentenceSc.next();
                                        if(camp_storage.getData(userRegCamp) == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                            camp_storage.getData(userRegCamp).addAttendees(studentAccount.getName());
                                            studentAccount.addCamps(camp_storage.getData(userRegCamp).getName());
                                            System.out.println("Registration successful!");
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Do case 4");
                                        break;
                                    case 5:
                                        System.out.println("Which camp do you have enquiries about?");
                                        String userEnqCamp = sc.next();
                                        if(camp_storage.getData(userEnqCamp) == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                            Camp enqCamp = camp_storage.getData(userEnqCamp);
                                            if(enqCamp.getVisibility() == false || enqCamp.getUserGroup().equals(studentAccount.getFaculty()) == false){
                                                System.out.println("Camp is not open for your faculty");
                                            }
                                            else{
                                                System.out.println("Please enter your enquiry");
                                                String userEnq = sentenceSc.next();
                                                Enquiry newEnquiry = new Enquiry(studentAccount.getName(), userEnq, userEnqCamp);
                                                newEnquiry.setEnquiryId(Integer.toString(enquiry_storage.generateID()));
                                                enquiry_storage.addItem(newEnquiry);
                                                System.out.println("Enquiry submitted successfully");
                                                System.out.println("Enquiry ID is " + newEnquiry.getEnquiryId());
                                                System.out.println("Use this enquiry ID to edit or delete your enquiry");
                                            }
                                        }
                                        break;
                                    case 6:
                                        enquiry_storage.printSenderData(studentAccount.getName());
                                        break;
                                    case 7:
                                        System.out.println("Which enquiry would you like to edit? Enter your enquiryID");
                                        String userEditEnq = sc.next();
                                        Enquiry editEnq = enquiry_storage.getData(userEditEnq);
                                        if(editEnq.getSender() != studentAccount.getName()){
                                            System.out.println("You are not the sender of this enquiry");
                                        }
                                        else{
                                            EditEnquiries editEnquiries = new EditEnquiries();
                                            editEnquiries.perform(editEnq);
                                        }
                                        break;
                                    case 8:
                                        System.out.println("Do case 8");
                                        break;
                                    case 9:
                                        ArrayList<String> registedCamp = studentAccount.getCamps();
                                        if(registedCamp.isEmpty())
                                        {
                                            System.out.println("You have not registered for any camp");
                                        }
                                        else {
                                            System.out.println("You have registered for the following camp: ");
                                            for (String s : registedCamp) {
                                                System.out.println(s);
                                            }
                                        }
                                        break;
                                    case 10:
                                        System.out.println("Please enter the name of the camp that you wish to withdraw");
                                        String campWithdraw = sentenceSc.next();
                                        boolean result = studentAccount.removeCamps(campWithdraw);
                                        if (result){
                                            System.out.println("Camp Removed Successfully");
                                            camp_storage.getData(campWithdraw).removeAttendees(studentAccount.getName());
                                            camp_storage.getData(campWithdraw).addWithdrawalList(studentAccount.getName());
                                        }
                                        else{
                                            System.out.println("You did not register for this camp");
                                        }
                                        break;
                                    case 11:
                                        System.out.println("Please enter the new password: ");
                                        String userNewPassword = sc.next();
                                        studentAccount.setPassword(userNewPassword);
                                        System.out.println("Password Changed Successfully");
                                        break;
                                    case 12:
                                        System.out.println("Goodbye " + studentAccount.getName() + " !");
                                        break;
                                }
                            }
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
