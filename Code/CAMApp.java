import java.util.Scanner;
import java.util.Objects;
import java.util.Date;
import java.util.ArrayList;
import java.io.Console;

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
        // init writers
        CSVStaffWriter staffWriter = new CSVStaffWriter();
        CSVStudentWriter studentWriter = new CSVStudentWriter();
        CSVSuggestionWriter suggestionWriter = new CSVSuggestionWriter();
        CSVEnquiryWriter enquiryWriter = new CSVEnquiryWriter();
        CSVCampWriter campWriter = new CSVCampWriter();
        // populate initial storage
        studentReader.populateStorage(student_storage);
        staffReader.populateStorage(staff_storage);
        suggestionReader.populateStorage(suggestion_storage);
        enquiryReader.populateStorage(enquiry_storage);
        campReader.populateStorage(camp_storage);
        System.out.println("Data loaded successfully");
        // init required dependencies
        Console console = System.console();
        Scanner sc = new Scanner(System.in);
        boolean log_in_successfully;

        while (true) {
            log_in_successfully = false;
            int user_choice = 0;
            UiPrinter.printLoginInMenu();
            user_choice = sc.nextInt();
            sc.nextLine();
            while (user_choice != 1 && user_choice != 2 && user_choice != 3) {
                System.out.print("Error... Please select again");
                UiPrinter.printLoginInMenu();
                user_choice = sc.nextInt();
                sc.nextLine();
            }
            // this switch case is for user to log in.
            switch (user_choice) {

                case 1: // staff login
                    while (!log_in_successfully) {
                        System.out.println("Please enter your Staff Username");
                        String staff_user = sc.next();
                        // mask password input
                        char[] staff_password_arr = console.readPassword("Please enter your Staff Password\n");
                        String staff_password = new String(staff_password_arr);
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
                            while(staff_choice != 15) {
                                UiPrinter.printStaffMenu();
                                staff_choice = sc.nextInt();
                                sc.nextLine();

                                switch(staff_choice){
                                    case 1: // Create camps
                                        System.out.println("Please enter the name of the camp");
                                        String name = sc.nextLine();
                                        while(!Objects.isNull(camp_storage.getData(name.trim())))
                                        {
                                            System.out.println("Camp name exists... Please select another name");
                                            System.out.println("Please enter the name of the camp");
                                            name = sc.nextLine();
                                        }
                                        Date startDate = null;
                                        Date endDate = null;
                                        Date regDeadline = null;
                                        System.out.println("Please enter the start date (dd/MM/yyyy)");
                                        String startDateString = sc.next();
                                        while (startDate == null || new Date().after(startDate)) {
                                            startDate = DateUtility.stringToDate(startDateString);
                                            if (startDate == null) {
                                                startDateString = sc.next();
                                            }
                                            else if (new Date().after(startDate)) {
                                                System.out.println("Start date cannot be before today's date, please enter again");
                                                startDate = null;
                                                startDateString = sc.next();
                                            }
                                        }
                                        System.out.println("Please enter the end date (dd/MM/yyyy)");
                                        String endDateString = sc.next();
                                        while (endDate == null || startDate.after(endDate)) {
                                            endDate = DateUtility.stringToDate(endDateString);
                                            if (endDate == null) {
                                                endDateString = sc.next();
                                            }
                                            else if (startDate.after(endDate)) {
                                                System.out.println("End date cannot be before start date, please enter again");
                                                endDate = null;
                                                endDateString = sc.next();
                                            }
                                        }
                                        System.out.println("Please enter the registration deadline (dd/MM/yyyy)");
                                        String regDeadlineString = sc.next();
                                        while (regDeadline == null || new Date().after(regDeadline) || regDeadline.after(startDate)) {
                                            regDeadline = DateUtility.stringToDate(regDeadlineString);
                                            if (regDeadline == null) {
                                                regDeadlineString = sc.next();
                                            }
                                            else if (new Date().after(regDeadline)) {
                                                System.out.println("Registration deadline cannot be before today's date, please enter again.");
                                                regDeadline = null;
                                                regDeadlineString = sc.next();
                                            }
                                            else if (regDeadline.after(startDate)) {
                                                System.out.println("Registration deadline cannot be after start date, please enter again.");
                                                regDeadline = null;
                                                regDeadlineString = sc.next();
                                            }
                                        }
                                        sc.nextLine();
                                        System.out.println("Would you like to open it up to NTU? (Y/N)");
                                        String userGroup_sel = sc.nextLine();
                                        String userGroup;
                                        while(!(userGroup_sel.equals("y")) && !(userGroup_sel.equals("Y")) && !(userGroup_sel.equals("N")) && !(userGroup_sel.equals("n")))
                                        {
                                            System.out.println("Invalid choice. Please select again");
                                            System.out.println("Would you like to open it up to NTU?(Y/N)");
                                            userGroup_sel = sc.nextLine();
                                        }
                                        if(userGroup_sel.equals("y") || userGroup_sel.equals("Y")){
                                            userGroup = "NTU";
                                        }
                                        else{
                                            userGroup = staffAccount.getFaculty();
                                        }
                                        System.out.println("Please enter the location");
                                        String location = sc.nextLine();
                                        int totalSlots = -1; // Set to -1 to enter while loop
                                        while (totalSlots < 2) {
                                            System.out.println("Please enter the number of slots for this camp (Min 2)");
                                            totalSlots = sc.nextInt();
                                        }
                                        sc.nextLine();
                                        int campCommSlots = -1; // Set to -1 to enter while loop
                                        while (campCommSlots > 10 || campCommSlots < 1 || campCommSlots >= totalSlots) {
                                            System.out.println("Please enter the number of Camp Committee slots for this camp (Min 1, Max 10, Cannot be more than or equal to total slots)");
                                            campCommSlots = sc.nextInt();
                                        }
                                        sc.nextLine();
                                        System.out.println("Please enter a description for the camp");
                                        String description = sc.nextLine();
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

                                        Camp newCamp = new Camp(name, startDate, endDate, regDeadline, userGroup, location, totalSlots, campCommSlots, description, staffAccount.getUserId(), visibility);
                                        System.out.println("Camp created successfully");
                                        camp_storage.addItem(newCamp);
                                        System.out.println("Camp added to storage");
                                        break;

                                    case 2: // edit camps
                                        System.out.println("Please enter the name of the camp that you wish to edit");
                                        String editCamp = sc.nextLine();
                                        Camp editcamp_obj = camp_storage.getData(editCamp);
                                        if(editcamp_obj == null){
                                            System.out.println("Camp do not exist");
                                        }
                                        else {
                                            if (editcamp_obj.getStaffIC().equals(staffAccount.getUserId())) {
                                                int staff_editchoice = 0;
                                                while (staff_editchoice != 7) {
                                                    UiPrinter.printStaffEditMenu();
                                                    staff_editchoice = sc.nextInt();
                                                    sc.nextLine();

                                                    switch (staff_editchoice) {

                                                        case 1: // edit start date
                                                            editcamp_obj.editCamp(new EditStartDate());
                                                            break;

                                                        case 2: // edit end date
                                                            editcamp_obj.editCamp(new EditEndDate());
                                                            break;

                                                        case 3: // edit registration deadline
                                                            editcamp_obj.editCamp(new EditRegDeadline());
                                                            break;

                                                        case 4: // edit location
                                                            editcamp_obj.editCamp(new EditLocation());
                                                            break;

                                                        case 5: // edit description
                                                            editcamp_obj.editCamp(new EditDescription());
                                                            break;

                                                        case 6: // toggle visibility
                                                            editcamp_obj.editCamp(new ToggleVisibility());
                                                            break;

                                                        case 7: // exit edit page
                                                            System.out.println("Exiting edit page");
                                                            break;

                                                        default:
                                                            System.out.println("Invalid choice. Please enter a valid option.");
                                                            break;
                                                    }
                                                }
                                            } else {
                                                System.out.println("Error... you are not the staff in charge of this camp.");
                                            }
                                        }

                                        break;
                                    case 3: // delete camps
                                        System.out.println("Please enter the name of the camp that you wish to remove");
                                        String tarCamp = sc.nextLine();
                                        Camp tarcamp_obj = camp_storage.getData(tarCamp);
                                        if (tarcamp_obj == null){
                                            System.out.println("Camp do not exist");
                                        }
                                        else{ 
                                        if(tarcamp_obj.getStaffIC().equals(staffAccount.getUserId()) && tarcamp_obj.getAttendees().isEmpty() && tarcamp_obj.getCampComms().isEmpty()){
                                            camp_storage.removeItem(tarcamp_obj);
                                            System.out.println("Camp " + tarCamp +" removed successfully");
                                        }
                                        else{
                                            System.out.println("Error removing... you are not the staff in charge of this camp.");
                                        }
                                    }
                                        break;
                                    case 4: // view all camps
                                        camp_storage.printData();
                                        break;
                                    case 5: // view camps created by staff
                                        camp_storage.printData(staffAccount);
                                        break;
                                    case 6: // view enquiries by students
                                        System.out.println("Please enter the name of the camp:");
                                        String campName = sc.nextLine();
                                        Camp campNameObj = camp_storage.getData(campName);
                                        if(campNameObj == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                            if(campNameObj.getStaffIC().equals(staffAccount.getUserId())){
                                                enquiry_storage.printAllData(campName);
                                            }
                                            else
                                            {
                                                System.out.println("Error... you are not the staff in charge of this camp.");
                                            }
                                        }
                                        break;
                                    case 7: // reply to enquiries
                                        System.out.println("Please enter the name of the camp:");
                                        String campNameEnquires = sc.nextLine();
                                        Camp campNameObjEnquires = camp_storage.getData(campNameEnquires);
                                        if(campNameObjEnquires == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                            if(campNameObjEnquires.getStaffIC().equals(staffAccount.getUserId())){
                                                enquiry_storage.printAllData(campNameEnquires);
                                                System.out.println("Please enter the enquiry ID that you wish to answer");
                                                String enquiryID = sc.nextLine();
                                                Enquiry tarEnquiry = enquiry_storage.getData(enquiryID);
                                                if(tarEnquiry == null){
                                                    System.out.println("Enquiry could not be found");
                                                }
                                                else {
                                                    if(tarEnquiry.getResponse()!=null){
                                                        System.out.println("There is already a response");
                                                    }
                                                    else {
                                                        System.out.println("Please enter your response:");
                                                        String response = sc.nextLine();
                                                        tarEnquiry.setResponse(response);
                                                        tarEnquiry.setResponder(staffAccount.getUserId());
                                                    }
                                                }

                                            }
                                            else
                                            {
                                                System.out.println("Error... you are not the staff in charge of this camp.");
                                            }
                                        }
                                        break;
                                    case 8: // view suggestions
                                        System.out.println("Please enter the name of the camp:");
                                        String campNameSuggestion = sc.nextLine();
                                        Camp campNameObjSuggestion = camp_storage.getData(campNameSuggestion);
                                        if(campNameObjSuggestion == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else {
                                            if (campNameObjSuggestion.getStaffIC().equals(staffAccount.getUserId())) {
                                                suggestion_storage.printData(campNameSuggestion);
                                            }
                                            else {
                                                System.out.println("You are not the staff in charge!");
                                            }
                                        }
                                        break;
                                    case 9: // Process suggestions
                                        System.out.println("Please enter the name of the camp:");
                                        String tarCampSuggestion = sc.nextLine();
                                        Camp tarCampNameObjSuggestion = camp_storage.getData(tarCampSuggestion);
                                        if(tarCampNameObjSuggestion == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else {
                                            if (tarCampNameObjSuggestion.getStaffIC().equals(staffAccount.getUserId())) {
                                                System.out.println("Please enter the Suggestion ID that you wish to approve/reject");
                                                String tarSuggestionID = sc.nextLine();
                                                Suggestion tarSuggestion = suggestion_storage.getData(tarSuggestionID);
                                                if(tarSuggestion == null){
                                                    System.out.println("Suggestion do not exist!");
                                                }
                                                else{
                                                    if (tarSuggestion.getProcessed()){
                                                        System.out.println("Suggestion has been processed already");
                                                    }
                                                    else {
                                                        System.out.println("Would you like to accept or reject this suggestion?");
                                                        System.out.println("1) Accept");
                                                        System.out.println("2) Reject");
                                                        int suggestionChoice = sc.nextInt();
                                                        sc.nextLine();
                                                        switch (suggestionChoice) {
                                                            case 1:
                                                                tarSuggestion.setApproval(true);
                                                                tarSuggestion.setProcessed(true);
                                                                System.out.println("Suggestion Accepted");
                                                                String studentName = tarSuggestion.getSuggestor();
                                                                StudentAccount studentNameObj = student_storage.getData(studentName);
                                                                studentNameObj.addPoints();
                                                                break;
                                                            case 2:
                                                                tarSuggestion.setApproval(false);
                                                                tarSuggestion.setProcessed(true);
                                                                System.out.println("Suggestion Rejected");
                                                                break;
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        break;
                                    case 10: // View camp attendees
                                        System.out.println("Please enter the name of the camp:");
                                        String tarCampAttendee = sc.nextLine();
                                        Camp tarCampAttendeeObj = camp_storage.getData(tarCampAttendee);
                                        if(tarCampAttendeeObj == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else {
                                            if(tarCampAttendeeObj.getStaffIC().equals(staffAccount.getUserId())){
                                                tarCampAttendeeObj.printAttendees();
                                            }
                                        }
                                        break;
                                    case 11: // View camp committee members
                                        System.out.println("Please enter the name of the camp:");
                                        String tarCampComm = sc.nextLine();
                                        Camp tarCampCommObj = camp_storage.getData(tarCampComm);
                                        if(tarCampCommObj == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else {
                                            if(tarCampCommObj.getStaffIC().equals(staffAccount.getUserId())){
                                                tarCampCommObj.printCampComm();
                                            }
                                        }
                                        break;
                                    case 12:
                                        // Generate performance report
                                        // include the points of the camp committee members
                                        System.out.println("Please enter the camp that you wish to generate performace report for");
                                        String campReport = sc.nextLine();
                                        // Generate performance report for campReport
                                        break;
                                    case 13: //Generate list of attendees or camp committee either in txt or csv format
                                        System.out.println("Would you like to generate list of attendees or camp committee");
                                        System.out.println("1) Generate list of attendees");
                                        System.out.println("2) Generate list of camp committee");
                                        System.out.println("3) Generate list of attendees and camp committee");
                                        int userChoice = sc.nextInt();
                                        while(userChoice!= 1 && userChoice!=2 && userChoice!=3){
                                            System.out.println("Invalid choice. Please select again!");
                                            userChoice = sc.nextInt();
                                        }
                                        sc.nextLine();
                                        String campToEnquire;
                                        Camp campNameToEnquireObj;
                                        switch(userChoice){
                                            case 1:
                                                System.out.println("Please enter the camp name that you would like to enquire?");
                                                campToEnquire = sc.nextLine();
                                                campNameToEnquireObj = camp_storage.getData(campToEnquire);
                                                if(campNameToEnquireObj == null){
                                                    System.out.println("Camp do not exist");
                                                }
                                                else{
                                                    //get attendees details
                                                    System.out.println("Which output format will you like?");
                                                    System.out.println(" 1) CSV file");
                                                    System.out.println(" 2) TXT file");
                                                    int outputFormatChoice = sc.nextInt();
                                                    while(outputFormatChoice!=1 && outputFormatChoice!=2){
                                                        System.out.println("Invalid choice. Please select again");
                                                        outputFormatChoice = sc.nextInt();
                                                    }
                                                    sc.nextLine();
                                                    switch(outputFormatChoice){
                                                        case 1:
                                                            //output data in csv file
                                                            break;
                                                        case 2:
                                                            // output data in txt file
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Please enter the camp name that you would like to enquire?");
                                                campToEnquire = sc.nextLine();
                                                campNameToEnquireObj = camp_storage.getData(campToEnquire);
                                                if(campNameToEnquireObj == null){
                                                    System.out.println("Camp do not exist");
                                                }
                                                else{
                                                    //get camp comm details
                                                    System.out.println("Which output format will you like?");
                                                    System.out.println(" 1) CSV file");
                                                    System.out.println(" 2) TXT file");
                                                    int outputFormatChoice = sc.nextInt();
                                                    while(outputFormatChoice!=1 && outputFormatChoice!=2){
                                                        System.out.println("Invalid choice. Please select again");
                                                        outputFormatChoice = sc.nextInt();
                                                    }
                                                    sc.nextLine();
                                                    switch(outputFormatChoice){
                                                        case 1:
                                                            //output data in csv file
                                                            break;
                                                        case 2:
                                                            // output data in txt file
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 3:
                                                // No filter
                                                String fileName = staffAccount.getName()+"_AttendeeList";
                                                staffAccount.generateAttendeeListCSV(null, camp_storage, student_storage, fileName);
                                                break;
                                        }
                                        break;
                                    case 14: // change password
                                        char[] userNewPasswordArr = console.readPassword("Please enter the new password:\n");
                                        String userNewPassword = new String(userNewPasswordArr);
                                        staffAccount.setPassword(userNewPassword);
                                        System.out.println("Password Changed Successfully");
                                        break;
                                    case 15: // logout
                                        System.out.println("Goodbye " + staffAccount.getName() + " !");
                                        break;
                                }
                            }

                        } else {
                            System.out.println("Wrong username or password");
                        }
                    }

                    break;
                case 2: // student login
                    while (!log_in_successfully) {
                        System.out.println("Please enter your Student Username");
                        String studentUser = sc.nextLine();
                        char[] student_password_arr = console.readPassword("Please enter your Student Password\n");
                        String studentPassword = new String(student_password_arr);
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
                            while(student_choice != 13)
                            {
                                UiPrinter.printStudentMenu();
                                student_choice = sc.nextInt();
                                sc.nextLine();
                                switch(student_choice){
                                    case 1: //Print student profile
                                        studentAccount.printProfile();
                                        break;
                                    case 2: // view list of camp available
                                        camp_storage.printData(studentAccount);
                                        break;
                                    case 3: // view the remaining slots for a camp
                                        System.out.println("Which camp would you like to enquire?");
                                        String userCamp = sc.nextLine();
                                        Camp userCampObj = camp_storage.getData(userCamp);
                                        if ( userCampObj == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                            if(userCampObj.getVisibility() && (userCampObj.getUserGroup().equals(studentAccount.getFaculty()) || userCampObj.getUserGroup().equals("NTU"))){
                                                System.out.println("Total slots remaining is " + camp_storage.getData(userCamp).getRemaindingSlots());
                                            }
                                            else{
                                                System.out.println("This camp is not available to you!");

                                            }
                                    }
                                        break;
                                    case 4: // register for camps
                                        System.out.println("Which camp would you like to register for?");
                                        String userRegCamp = sc.nextLine();
                                        Camp userRegCampObj = camp_storage.getData(userRegCamp);
                                        if( userRegCampObj == null || !userRegCampObj.getVisibility()){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else {
                                            if ((userRegCampObj.getUserGroup().equals(studentAccount.getFaculty()) || userRegCampObj.getUserGroup().equals("NTU"))){
                                                if (new Date().before(userRegCampObj.getRegDeadline())){
                                                    if(userRegCampObj.getRemaindingSlots() > 0){
                                                        if(!studentAccount.getCamps().contains(userRegCamp)){
                                                            if (!userRegCampObj.getWithdrawalList().contains(studentAccount.getUserId())){
                                                                if(!studentAccount.getCampCommOf().equals(userRegCamp)){
                                                                    // Check if dates overlap for
                                                                    ArrayList<String> campsRegistered = studentAccount.getCampsRegistered();
                                                                    boolean overlaps = false;
                                                                    for (String cString : campsRegistered) {
                                                                        Camp c = camp_storage.getData(cString);
                                                                        if (DateUtility.checkOverlap(userRegCampObj.getStartDate(), userRegCampObj.getEndDate(),
                                                                                c.getStartDate(), c.getEndDate())) {
                                                                            System.out.println("Camp " + c.getName() + " overlaps with this camp!");
                                                                            overlaps = true;
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (!overlaps && !studentAccount.getCampCommOf().equals("")) {
                                                                        Camp campCommCamp = camp_storage.getData(studentAccount.getCampCommOf());
                                                                        if (DateUtility.checkOverlap(userRegCampObj.getStartDate(), userRegCampObj.getEndDate(),
                                                                                campCommCamp.getStartDate(), campCommCamp.getEndDate())) {
                                                                            System.out.println("Camp " + campCommCamp.getName() + " overlaps with this camp!");
                                                                            overlaps = true;
                                                                        }
                                                                    }
                                                                    //
                                                                    if (!overlaps) {
                                                                        // All checks passed, add student to camp
                                                                        camp_storage.getData(userRegCamp).addAttendees(studentAccount.getUserId());
                                                                        studentAccount.addCamps(camp_storage.getData(userRegCamp).getName());
                                                                        System.out.println("Registration successful!");
                                                                    }
                                                                }
                                                                else{
                                                                    System.out.println("Cannot register for this camp as you are already camp committee for this camp!");
                                                                }
                                                            }
                                                            else{
                                                                System.out.println("You have withdrawn from this camp before!");
                                                            }
                                                        }
                                                        else{
                                                            System.out.println("You have already registered for this camp!");
                                                            }
                                                    }
                                                    else{
                                                        System.out.println("There is no more slots in this camp!");
                                                        }
                                                }
                                                else {
                                                    System.out.println("It is past the registration dateline!");
                                                }
                                            }
                                            else{
                                                System.out.println("This camp is not open to your faculty");
                                            }
                                        }
                                        break;
                                    case 5: // register for camp comm
                                        if (studentAccount.getCampCommOf().isEmpty()){
                                            System.out.println("Please enter the camp that you wish to register for camp comm");
                                            String campCommCamp = sc.nextLine();
                                            Camp campCommCampObj = camp_storage.getData(campCommCamp);
                                            if (campCommCampObj == null) {
                                                System.out.println("Camp do not exist");
                                            }
                                            else if (campCommCampObj.getRemaindingSlotsCampComm() > 0) {
                                                // Check if withdraw before
                                                if (campCommCampObj.getWithdrawalList().contains(studentAccount.getUserId())) {
                                                    System.out.println("Cannot register as camp committee as you have withdrawn from camp");
                                                } else {
                                                    // Check if already attendee
                                                    if(studentAccount.getCampsRegistered().contains(campCommCamp)){
                                                        System.out.println("Cannot register as camp committee! Already Attendee");
                                                    }
                                                    else {
                                                        // Check if date overlaps with registered camps
                                                        ArrayList<String> campsRegistered = studentAccount.getCampsRegistered();
                                                        boolean overlaps = false;
                                                        for (String cString : campsRegistered) {
                                                            Camp c = camp_storage.getData(cString);
                                                            if (DateUtility.checkOverlap(campCommCampObj.getStartDate(), campCommCampObj.getEndDate(),
                                                                    c.getStartDate(), c.getEndDate())) {
                                                                System.out.println("Camp " + c.getName() + " overlaps with this camp!");
                                                                overlaps = true;
                                                                break;
                                                            }
                                                        }
                                                        // All checks passed
                                                        if (!overlaps) {
                                                            campCommCampObj.addCampComms(studentAccount.getUserId());
                                                            studentAccount.setCampCommOf(campCommCampObj.getName());
                                                            System.out.println("You have successfully registered as a camp committee for the camp");
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("There is no more camp committee slots");
                                            }
                                        }
                                        else{
                                            System.out.println("You are already a camp committee of "+ studentAccount.getCampCommOf());
                                        }
                                        break;
                                    case 6: // submit enquiries
                                        System.out.println("Which camp do you have enquiries about?");
                                        String userEnqCamp = sc.nextLine();
                                        if(camp_storage.getData(userEnqCamp) == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else if(camp_storage.getData(userEnqCamp).getName().equals(studentAccount.getCampCommOf())){
                                            System.out.println("You are the camp committee member.");
                                        }
                                        else{
                                            Camp enqCamp = camp_storage.getData(userEnqCamp);
                                            if(!enqCamp.getVisibility() || (!enqCamp.getUserGroup().equals("NTU") &&
                                                    !enqCamp.getUserGroup().equals(studentAccount.getFaculty()))){
                                                System.out.println("Camp is not open for your faculty");
                                            }
                                            else{
                                                System.out.println("Please enter your enquiry (no blanks)");
                                                String userEnq = sc.nextLine();
                                                if (userEnq.isBlank()) {
                                                    System.out.println("No enquiry created (no blanks)");
                                                    break;
                                                }
                                                Enquiry newEnquiry = new Enquiry(studentAccount.getUserId(), userEnq, userEnqCamp, IdGenerator.generateID(enquiry_storage));
                                                enquiry_storage.addItem(newEnquiry);
                                                System.out.println("Enquiry submitted successfully");
                                                System.out.println("Enquiry ID is " + newEnquiry.getEnquiryId());
                                                System.out.println("Use this enquiry ID to edit or delete your enquiry");
                                            }
                                        }
                                        break;
                                    case 7: // view enquires made
                                        enquiry_storage.printSenderData(studentAccount.getUserId());
                                        break;
                                    case 8: // edit enquires made
                                        System.out.println("Which enquiry would you like to edit? Enter your enquiryID");
                                        String userEditEnq = sc.nextLine();
                                        Enquiry editEnq = enquiry_storage.getData(userEditEnq);
                                        if(editEnq.getSender().equals(studentAccount.getUserId())) {
                                            if (editEnq.getResponse()==null && editEnq.getResponder()==null) {
                                                System.out.println("What is your new enquiry (no blanks)?");
                                                String newEnq = sc.nextLine();
                                                if(newEnq.isBlank()) {
                                                    System.out.println("No enquiry edited (no blanks)");
                                                    break;
                                                }
                                                editEnq.setMessage(newEnq);
                                                System.out.println("New enquiry has been saved");
                                            }
                                            else {
                                                System.out.println("Error. Enquiry has been responded to.");
                                            }
                                        }
                                        else {
                                            System.out.println("You are not the sender of this enquiry");
                                        }
                                        break;
                                    case 9: // delete enquires made
                                        System.out.println("Which enquiry would you like to delete? Enter your enquiryID");
                                        String userdeleteEnq = sc.next();
                                        Enquiry deleteEnq = enquiry_storage.getData(userdeleteEnq);
                                        if(deleteEnq.getSender().equals(studentAccount.getUserId())){
                                            enquiry_storage.deleteItem(deleteEnq);
                                            System.out.println("Enquiry deleted successfully");
                                        }
                                        else{

                                            System.out.println("You are not the sender of this enquiry");
                                        }
                                        break;
                                    case 10: // withdraw from camp
                                        System.out.println("Please enter the name of the camp that you wish to withdraw");
                                        String campWithdraw = sc.nextLine();
                                        Camp campToWithdrawObj = camp_storage.getData(campWithdraw);
                                        boolean result = studentAccount.removeCamps(campWithdraw);
                                        if (result){
                                            System.out.println("Camp Removed Successfully");
                                            campToWithdrawObj.removeAttendees(studentAccount.getUserId());
                                        }
                                        else{
                                            if(studentAccount.getCampCommOf().equals(campWithdraw)){
                                                System.out.println("You are the Camp Committee of this camp!");
                                            }
                                            else{
                                                System.out.println("You did not register for this camp");
                                            }
                                        }
                                        break;
                                    case 11: // change password
                                        char[] userNewPasswordArr = console.readPassword("Please enter the new password:\n");
                                        String userNewPassword = new String(userNewPasswordArr);
                                        studentAccount.setPassword(userNewPassword);
                                        System.out.println("Password Changed Successfully");
                                        break;
                                    case 12: // enter camp committee mode
                                        String studentCampComm = studentAccount.getCampCommOf();
                                        if (studentCampComm.equals("")){
                                            System.out.println("You are not a Camp Committee member!");
                                        }
                                        else {
                                            Camp studentCampCommObj = camp_storage.getData(studentCampComm);
                                            int studentCampCommChoice = 0;
                                            while(studentCampCommChoice != 10) {
                                                UiPrinter.printCampCommitteeMenu();
                                                studentCampCommChoice = sc.nextInt();
                                                sc.nextLine();
                                                switch (studentCampCommChoice) {
                                                    // 1) View camps that you have registered
                                                    case 1:
                                                        studentAccount.printProfile();
                                                        break;
                                                    // 2) Submit suggestions for camps
                                                    case 2:
                                                        System.out.println("Please enter the suggestion for " + studentCampCommObj.getName() + " (no blanks)");
                                                        String userSuggestion = sc.nextLine();
                                                        if(userSuggestion.isBlank()) {
                                                            System.out.println("No suggestion created (no blanks)");
                                                            break;
                                                        }
                                                        // add in the suggestion ID
                                                        suggestion_storage.addItem(new Suggestion(studentAccount.getUserId(), userSuggestion, IdGenerator.generateID(suggestion_storage), studentAccount.getCampCommOf()));
                                                        studentAccount.addPoints();
                                                        System.out.println(("Suggestion created successfully"));
                                                        break;
                                                    // 3) View suggestion
                                                    case 3:
                                                        ArrayList<Suggestion> userSuggestions = suggestion_storage.getData();
                                                        boolean no_suggestion = true;
                                                        for (Suggestion suggestion : userSuggestions){
                                                            if(suggestion.getSuggestor().equals(studentAccount.getUserId()))
                                                            {
                                                                no_suggestion = false;
                                                                System.out.println("The suggestion ID is " + suggestion.getSuggestionId());
                                                                System.out.println("The suggestion is " + suggestion.getSuggestion());
                                                                System.out.println("The process status is " + suggestion.getProcessed());
                                                                System.out.println("The approval status is " + suggestion.getApproval() + "\n");
                                                            }
                                                        }
                                                        if(no_suggestion){
                                                            System.out.println("You do not have any suggestion");
                                                        }
                                                        break;
                                                    // 4) Edit suggestion
                                                    case 4:
                                                        System.out.println("Which suggestion would you like to edit? Enter your suggestionID (no blanks)");
                                                        String userEditSuggestion = sc.nextLine();
                                                        if(userEditSuggestion.isBlank()) {
                                                            System.out.println("No suggestion edited (no blanks)");
                                                            break;
                                                        }
                                                        Suggestion editSuggestion = suggestion_storage.getData(userEditSuggestion);
                                                        if(editSuggestion.getSuggestor().equals(studentAccount.getUserId())) {
                                                            if (editSuggestion.getProcessed()) {
                                                                System.out.println("Suggestion has been processed already");
                                                            } else {
                                                                System.out.println("What is your new suggestion?");
                                                                String new_suggest = sc.nextLine();
                                                                editSuggestion.setSuggestion(new_suggest);
                                                                System.out.println("New suggestion has been saved");
                                                            }
                                                        }
                                                        else{
                                                            System.out.println("You are not the suggester of this suggestion");
                                                        }
                                                        break;
                                                    // 5) Delete suggestion
                                                    case 5:
                                                        System.out.println("Which suggestion would you like to delete? Enter your suggestionID");
                                                        String userDelSuggest = sc.next();
                                                        Suggestion deleteSuggest = suggestion_storage.getData(userDelSuggest);
                                                        if(deleteSuggest.getSuggestor().equals(studentAccount.getUserId())){
                                                            suggestion_storage.deleteItem(deleteSuggest);
                                                            System.out.println("Suggestion deleted successfully");
                                                        }
                                                        else{
                                                            System.out.println("You are not the suggester of this suggestion");
                                                        }
                                                        break;
                                                    case 6: // View enquiries
                                                        enquiry_storage.printAllData(studentCampCommObj.getName());
                                                        break;
                                                    case 7: // Reply enquiries
                                                        enquiry_storage.printAllData(studentCampCommObj.getName());
                                                        System.out.println("Which enquiry would you like to respond? Enter the enquiry ID");
                                                        String tarEnquiryId = sc.nextLine();
                                                        Enquiry tarEnquiry = enquiry_storage.getData(tarEnquiryId);
                                                        if(tarEnquiry == null){
                                                            System.out.println("Enquiry could not be found");
                                                        }
                                                        else {
                                                            // check if there is response
                                                            if (tarEnquiry.getResponse() != null) {
                                                                System.out.println("There is already a response");
                                                            }
                                                            // check if user sent enquiry before becoming camp comm member
                                                            else if (tarEnquiry.getSender().equals(studentAccount.getUserId())) {
                                                                System.out.println("You are the sender of this enquiry");
                                                            }
                                                            else {
                                                                System.out.println("Please enter your response:");
                                                                String response = sc.nextLine();
                                                                tarEnquiry.setResponse(response);
                                                                tarEnquiry.setResponder(studentAccount.getUserId());
                                                                studentAccount.addPoints();
                                                            }
                                                        }
                                                        break;

                                                    case 8: // Generate a report of the list of students attending
                                                        System.out.println("Would you like to generate a report on list of attendees or camp committee");
                                                        System.out.println(" 1) Generate report on attendees");
                                                        System.out.println(" 2) Generate report on camp committee");
                                                        int campCommReportSelection = sc.nextInt();
                                                        int campCommReportOutput;
                                                        while(campCommReportSelection != 1 && campCommReportSelection != 2){
                                                            System.out.println("Invalid choice. Please select again");
                                                            campCommReportSelection = sc.nextInt();
                                                        }
                                                        sc.nextLine();
                                                        switch(campCommReportSelection) {
                                                            case 1:
                                                                //get details on attendees
                                                                System.out.println("How would you like to output");
                                                                System.out.println(" 1) CSV");
                                                                System.out.println(" 2) TXT");
                                                                campCommReportOutput = sc.nextInt();
                                                                //reference to the camp object by studentCampCommObj
                                                                while (campCommReportOutput != 1 && campCommReportOutput != 2) {
                                                                    System.out.println("Invalid choice. Please select again");
                                                                    campCommReportOutput = sc.nextInt();
                                                                }
                                                                sc.nextLine();
                                                                switch (campCommReportOutput) {
                                                                    case 1:
                                                                        // output in csv format
                                                                        break;
                                                                    case 2:
                                                                        // output in txt format
                                                                        break;
                                                                }
                                                                break;
                                                            case 2:
                                                                // get details on camp committee
                                                                System.out.println("How would you like to output");
                                                                System.out.println(" 1) CSV");
                                                                System.out.println(" 2) TXT");
                                                                campCommReportOutput = sc.nextInt();
                                                                //reference to the camp object by studentCampCommObj
                                                                while (campCommReportOutput != 1 && campCommReportOutput != 2) {
                                                                    System.out.println("Invalid choice. Please select again");
                                                                    campCommReportOutput = sc.nextInt();
                                                                }
                                                                sc.nextLine();
                                                                switch (campCommReportOutput) {
                                                                    case 1:
                                                                        // output in csv format
                                                                        break;
                                                                    case 2:
                                                                        // output in txt format
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;

                                                    case 9: // Print camp information
                                                        UiPrinter.printCampInformation(studentCampCommObj);
                                                        break;
                                                    case 10: // Return back to student Mode
                                                        System.out.println("Returning to student mode...");
                                                        break;
                                                }
                                            }
                                        }
                                        break;
                                    case 13: // exit
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
                    System.out.println("Goodbye!");
                    staffWriter.performWrite("staff.csv", staff_storage);
                    studentWriter.performWrite("student.csv", student_storage);
                    enquiryWriter.performWrite("enquiry.csv", enquiry_storage);
                    suggestionWriter.performWrite("suggestion.csv", suggestion_storage);
                    campWriter.performWrite("camp.csv", camp_storage);
                    System.out.println("Data written to files.");
                    System.exit(0);
            }
        }
    }
}
