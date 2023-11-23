import java.util.Scanner;
import java.util.Objects;
import java.util.Date;
import java.util.ArrayList;

public class CAMApp {
    public static void main(String[] args) {
        // init storages
        StudentStorage studentStorage = new StudentStorage();
        StaffStorage staffStorage = new StaffStorage();
        SuggestionStorage suggestionStorage = new SuggestionStorage();
        EnquiryStorage enquiryStorage = new EnquiryStorage();
        CampStorage campStorage = new CampStorage();
        // init reader
        CSVReader csvReader = new CSVReader();
        // init writers
        CSVStaffWriter staffWriter = new CSVStaffWriter();
        CSVStudentWriter studentWriter = new CSVStudentWriter();
        CSVSuggestionWriter suggestionWriter = new CSVSuggestionWriter();
        CSVEnquiryWriter enquiryWriter = new CSVEnquiryWriter();
        CSVCampWriter campWriter = new CSVCampWriter();
        // populate initial storage
        studentStorage.populateData(csvReader);
        staffStorage.populateData(csvReader);
        suggestionStorage.populateData(csvReader);
        enquiryStorage.populateData(csvReader);
        campStorage.populateData(csvReader);
        System.out.println("Data loaded successfully");
        // init required dependencies
        Scanner sc = new Scanner(System.in);
        boolean logInSuccessfully;

        while (true) {
            logInSuccessfully = false;
            int userChoice = 0;
            UiPrinter.printLoginInMenu();
            userChoice = sc.nextInt();
            sc.nextLine();
            while (userChoice != 1 && userChoice != 2 && userChoice != 3) {
                System.out.print("Error... Please select again");
                UiPrinter.printLoginInMenu();
                userChoice = sc.nextInt();
                sc.nextLine();
            }
            // this switch case is for user to log in.
            switch (userChoice) {

                case 1: // staff login
                    while (!logInSuccessfully) {
                        System.out.println("Please enter your Staff Username");
                        String staffUser = sc.nextLine();
                        System.out.println("Please enter your Staff Password");
                        String staffPassword = sc.nextLine();
                        StaffAccount staffAccount = staffStorage.getData(staffUser);
                        if (Objects.isNull(staffAccount)){
                            System.out.println("Wrong username or password");
                        }
                        else if (staffAccount.getPassword().equals(staffPassword)) {
                            System.out.println("Login Successful!");
                            logInSuccessfully = true;
                            // Since the user log in successfully, we will carry on with the staff operation
                            //print the menu for the staff
                            int staffChoice = 0;
                            System.out.println("Welcome " + staffAccount.getName());
                            while(staffChoice != 15) {
                                UiPrinter.printStaffMenu();
                                staffChoice = sc.nextInt();
                                sc.nextLine();

                                switch(staffChoice){
                                    case 1: // Create camps
                                        System.out.println("Please enter the name of the camp");
                                        String name = sc.nextLine();
                                        while(!Objects.isNull(campStorage.getData(name.trim())))
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
                                        String userGroupSel = sc.nextLine();
                                        String userGroup;
                                        while(!(userGroupSel.equals("y")) && !(userGroupSel.equals("Y")) && !(userGroupSel.equals("N")) && !(userGroupSel.equals("n")))
                                        {
                                            System.out.println("Invalid choice. Please select again");
                                            System.out.println("Would you like to open it up to NTU?(Y/N)");
                                            userGroupSel = sc.nextLine();
                                        }
                                        if(userGroupSel.equals("y") || userGroupSel.equals("Y")){
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
                                        String visibilitySelection = sc.next();
                                        boolean visibility;
                                        while(!(visibilitySelection.equals("y")) && !(visibilitySelection.equals("Y")) && !(visibilitySelection.equals("N")) && !(visibilitySelection.equals("n")))
                                        {
                                            System.out.println("Invalid choice. Please select again");
                                            System.out.println("Do you want this camp to be visible?(Y/N)");
                                            visibilitySelection = sc.next();
                                        }
                                        if(visibilitySelection.equals("y") || visibilitySelection.equals("Y")){
                                            visibility = true;
                                        }
                                        else{
                                            visibility = false;
                                        }
                                        staffAccount.createCamp(name, startDate, endDate, regDeadline, userGroup, location, totalSlots, campCommSlots, description, visibility, campStorage);
                                        break;

                                    case 2: // edit camps
                                        System.out.println("Please enter the name of the camp that you wish to edit");
                                        String editCamp = sc.nextLine();
                                        Camp editcampObj = campStorage.getData(editCamp);
                                        if(editcampObj == null){
                                            System.out.println("Camp do not exist");
                                        }
                                        else {
                                            if (editcampObj.getStaffIC().equals(staffAccount.getUserId())) {
                                                int staffEditchoice = 0;
                                                while (staffEditchoice != 7) {
                                                    UiPrinter.printStaffEditMenu();
                                                    staffEditchoice = sc.nextInt();
                                                    sc.nextLine();
                                                    switch (staffEditchoice) {
                                                        case 1: // edit start date
                                                            staffAccount.editCamp(editcampObj, new EditStartDate());
                                                            break;

                                                        case 2: // edit end date
                                                            staffAccount.editCamp(editcampObj, new EditEndDate());
                                                            break;

                                                        case 3: // edit registration deadline
                                                            staffAccount.editCamp(editcampObj, new EditRegDeadline());
                                                            break;

                                                        case 4: // edit location
                                                            staffAccount.editCamp(editcampObj, new EditLocation());
                                                            break;

                                                        case 5: // edit description
                                                            staffAccount.editCamp(editcampObj, new EditDescription());
                                                            break;

                                                        case 6: // toggle visibility
                                                            staffAccount.editCamp(editcampObj, new ToggleVisibility());
                                                            break;

                                                        case 7: // exit edit page
                                                            System.out.println("Exiting edit page");
                                                            break;

                                                        default:
                                                            System.out.println("Invalid choice. Please enter a valid option.");
                                                            break;
                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("Error... you are not the staff in charge of this camp.");
                                            }
                                        }

                                        break;
                                    case 3: // delete camps
                                        System.out.println("Please enter the name of the camp that you wish to remove");
                                        String tarCamp = sc.nextLine();
                                        Camp targetCampObj = campStorage.getData(tarCamp);
                                        if (targetCampObj == null){
                                            System.out.println("Camp do not exist");
                                        }
                                        else{ 
                                            if(targetCampObj.getStaffIC().equals(staffAccount.getUserId()) && targetCampObj.getAttendees().isEmpty() && targetCampObj.getCampComms().isEmpty()){
                                                campStorage.removeItem(targetCampObj);
                                                System.out.println("Camp " + tarCamp +" removed successfully");
                                        }
                                        else{
                                            System.out.println("Error removing... you are not the staff in charge of this camp.");
                                        }
                                    }
                                        break;
                                    case 4: // view all camps
                                        campStorage.printData();
                                        break;
                                    case 5: // view camps created by staff
                                        campStorage.printData(staffAccount);
                                        break;
                                    case 6: // view enquiries by students
                                        System.out.println("Please enter the name of the camp:");
                                        String campName = sc.nextLine();
                                        Camp campNameObj = campStorage.getData(campName);
                                        if(campNameObj == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                            if(campNameObj.getStaffIC().equals(staffAccount.getUserId())){
                                                enquiryStorage.printAllData(campName);
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
                                        Camp campNameObjEnquires = campStorage.getData(campNameEnquires);
                                        if(campNameObjEnquires == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                            if(campNameObjEnquires.getStaffIC().equals(staffAccount.getUserId())){
                                                enquiryStorage.printAllData(campNameEnquires);
                                                System.out.println("Please enter the enquiry ID that you wish to answer");
                                                String enquiryID = sc.nextLine();
                                                Enquiry tarEnquiry = enquiryStorage.getData(enquiryID);
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
                                        Camp campNameObjSuggestion = campStorage.getData(campNameSuggestion);
                                        if(campNameObjSuggestion == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else {
                                            if (campNameObjSuggestion.getStaffIC().equals(staffAccount.getUserId())) {
                                                suggestionStorage.printData(campNameSuggestion);
                                            }
                                            else {
                                                System.out.println("You are not the staff in charge!");
                                            }
                                        }
                                        break;
                                    case 9: // Process suggestions
                                        System.out.println("Please enter the name of the camp:");
                                        String tarCampSuggestion = sc.nextLine();
                                        Camp tarCampNameObjSuggestion = campStorage.getData(tarCampSuggestion);
                                        if(tarCampNameObjSuggestion == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else {
                                            if (tarCampNameObjSuggestion.getStaffIC().equals(staffAccount.getUserId())) {
                                                System.out.println("Please enter the Suggestion ID that you wish to approve/reject");
                                                String tarSuggestionID = sc.nextLine();
                                                Suggestion tarSuggestion = suggestionStorage.getData(tarSuggestionID);
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
                                                                staffAccount.processSuggestion(tarSuggestion, studentStorage, true);
                                                                break;
                                                            case 2:
                                                                staffAccount.processSuggestion(tarSuggestion, studentStorage, false);
                                                                break;
                                                            default:
                                                                System.out.println("Invalid choice.");
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        break;
                                    case 10: // View camp attendees
                                        System.out.println("Please enter the name of the camp:");
                                        String tarCampAttendee = sc.nextLine();
                                        Camp tarCampAttendeeObj = campStorage.getData(tarCampAttendee);
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
                                        Camp tarCampCommObj = campStorage.getData(tarCampComm);
                                        if(tarCampCommObj == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else {
                                            if(tarCampCommObj.getStaffIC().equals(staffAccount.getUserId())){
                                                tarCampCommObj.printCampComm();
                                            } else {
                                                System.out.println("You are not the staff in charge of this camp!");
                                            }
                                        }
                                        break;
                                    case 12:
                                        // Generate performance report for all camps the staff is in charge of
                                        // include the points of the camp committee members
                                        System.out.println("Please enter the name of the file (without file extension).");
                                        String performanceReportFileName = sc.nextLine();
                                        performanceReportFileName = performanceReportFileName + ".txt";
                                        staffAccount.generatePerformanceReport(campStorage, studentStorage, performanceReportFileName);
                                        break;
                                    case 13: //Generate list of attendees or camp committee either in txt or csv format
                                        System.out.println("Would you like to generate list of attendees or camp committee");
                                        System.out.println("1) Generate list of participants only");
                                        System.out.println("2) Generate list of camp committee only");
                                        System.out.println("3) Generate list without filters");

                                        int filterChoice = sc.nextInt();
                                        while(filterChoice != 1 && filterChoice !=2 && filterChoice !=3) {
                                            System.out.println("Invalid choice. Please select again!");
                                            filterChoice = sc.nextInt();
                                        }
                                        sc.nextLine();
                                        System.out.println("Which output format will you like?");
                                        System.out.println("1) CSV file");
                                        System.out.println("2) TXT file");
                                        int outputFormatChoice = sc.nextInt();
                                        while(outputFormatChoice!=1 && outputFormatChoice!=2){
                                            System.out.println("Invalid choice. Please select again");
                                            outputFormatChoice = sc.nextInt();
                                        }
                                        sc.nextLine();
                                        System.out.println("Please enter the name of the file (without file extension).");
                                        String fileName = sc.nextLine();
                                        switch(filterChoice){
                                            case 1:
                                                switch(outputFormatChoice){
                                                    case 1:
                                                        //output data in csv file
                                                        fileName = fileName + ".csv";
                                                        staffAccount.generateAttendeeListCSV(new ParticipantFilter(), campStorage, studentStorage, fileName, staffAccount);
                                                        break;
                                                    case 2:
                                                        // output data in txt file
                                                        fileName = fileName + ".txt";
                                                        staffAccount.generateAttendeeListTXT(new ParticipantFilter(), campStorage, studentStorage, fileName, staffAccount);
                                                        break;
                                                }
                                            break;
                                            case 2:
                                                switch(outputFormatChoice){
                                                    case 1:
                                                        //output data in csv file
                                                        fileName = fileName + ".csv";
                                                        staffAccount.generateAttendeeListCSV(new CampCommFilter(), campStorage, studentStorage, fileName, staffAccount);
                                                        break;
                                                    case 2:
                                                        // output data in txt file
                                                        fileName = fileName + ".txt";
                                                        staffAccount.generateAttendeeListTXT(new CampCommFilter(), campStorage, studentStorage, fileName, staffAccount);
                                                        break;
                                                }
                                                break;
                                            case 3: // No filter
                                                switch(outputFormatChoice){
                                                    case 1:
                                                        //output data in csv file
                                                        fileName = fileName + ".csv";
                                                        staffAccount.generateAttendeeListCSV(null, campStorage, studentStorage, fileName, staffAccount);
                                                        break;
                                                    case 2:
                                                        // output data in txt file
                                                        fileName = fileName + ".txt";
                                                        staffAccount.generateAttendeeListTXT(null, campStorage, studentStorage, fileName, staffAccount);
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                    case 14: // change password
                                        System.out.println("Please enter the new password:");
                                        String userNewPassword = sc.nextLine();
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
                    while (!logInSuccessfully) {
                        System.out.println("Please enter your Student Username");
                        String studentUser = sc.nextLine();
                        System.out.println("Please enter your Student Password");
                        String studentPassword = sc.nextLine();
                        StudentAccount studentAccount = studentStorage.getData(studentUser);
                        if(Objects.isNull(studentAccount))
                        {
                            System.out.println("Wrong username or password");
                        }
                        else if (studentAccount.getPassword().equals(studentPassword)) {
                            System.out.println("Login Successful!");
                            logInSuccessfully = true;
                            int studentChoice = 0;
                            System.out.println("Welcome " + studentAccount.getName());
                            while(studentChoice != 13)
                            {
                                UiPrinter.printStudentMenu();
                                studentChoice = sc.nextInt();
                                sc.nextLine();
                                switch(studentChoice){
                                    case 1: //Print student profile
                                        studentAccount.printProfile();
                                        break;
                                    case 2: // view list of camp available
                                        campStorage.printData(studentAccount);
                                        break;
                                    case 3: // view the remaining slots for a camp
                                        System.out.println("Which camp would you like to enquire?");
                                        String userCamp = sc.nextLine();
                                        Camp userCampObj = campStorage.getData(userCamp);
                                        if ( userCampObj == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else{
                                            if(userCampObj.getVisibility() && (userCampObj.getUserGroup().equals(studentAccount.getFaculty()) || userCampObj.getUserGroup().equals("NTU"))){
                                                System.out.println("Total slots remaining is " + campStorage.getData(userCamp).getRemaindingSlots());
                                            }
                                            else{
                                                System.out.println("This camp is not available to you!");

                                            }
                                    }
                                        break;
                                    case 4: // register for camps
                                        System.out.println("Which camp would you like to register for?");
                                        String userRegCamp = sc.nextLine();
                                        Camp userRegCampObj = campStorage.getData(userRegCamp);
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
                                                                        Camp c = campStorage.getData(cString);
                                                                        if (DateUtility.checkOverlap(userRegCampObj.getStartDate(), userRegCampObj.getEndDate(),
                                                                                c.getStartDate(), c.getEndDate())) {
                                                                            System.out.println("Camp " + c.getName() + " overlaps with this camp!");
                                                                            overlaps = true;
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (!overlaps && !studentAccount.getCampCommOf().equals("")) {
                                                                        Camp campCommCamp = campStorage.getData(studentAccount.getCampCommOf());
                                                                        if (DateUtility.checkOverlap(userRegCampObj.getStartDate(), userRegCampObj.getEndDate(),
                                                                                campCommCamp.getStartDate(), campCommCamp.getEndDate())) {
                                                                            System.out.println("Camp " + campCommCamp.getName() + " overlaps with this camp!");
                                                                            overlaps = true;
                                                                        }
                                                                    }
                                                                    //
                                                                    if (!overlaps) {
                                                                        // All checks passed, add student to camp
                                                                        campStorage.getData(userRegCamp).addAttendees(studentAccount.getUserId());
                                                                        studentAccount.addCamps(campStorage.getData(userRegCamp).getName());
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
                                            Camp campCommCampObj = campStorage.getData(campCommCamp);
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
                                                            Camp c = campStorage.getData(cString);
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
                                        if(campStorage.getData(userEnqCamp) == null){
                                            System.out.println("Camp do not exist!");
                                        }
                                        else if(campStorage.getData(userEnqCamp).getName().equals(studentAccount.getCampCommOf())){
                                            System.out.println("You are the camp committee member.");
                                        }
                                        else{
                                            Camp enqCamp = campStorage.getData(userEnqCamp);
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
                                                Enquiry newEnquiry = new Enquiry(studentAccount.getUserId(), userEnq, userEnqCamp, IdGenerator.generateID(enquiryStorage));
                                                enquiryStorage.addItem(newEnquiry);
                                                System.out.println("Enquiry submitted successfully");
                                                System.out.println("Enquiry ID is " + newEnquiry.getEnquiryId());
                                                System.out.println("Use this enquiry ID to edit or delete your enquiry");
                                            }
                                        }
                                        break;
                                    case 7: // view enquires made
                                        enquiryStorage.printSenderData(studentAccount.getUserId());
                                        break;
                                    case 8: // edit enquires made
                                        System.out.println("Which enquiry would you like to edit? Enter your enquiryID");
                                        String userEditEnq = sc.nextLine();
                                        Enquiry editEnq = enquiryStorage.getData(userEditEnq);
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
                                        Enquiry deleteEnq = enquiryStorage.getData(userdeleteEnq);
                                        if(deleteEnq.getSender().equals(studentAccount.getUserId())){
                                            enquiryStorage.deleteItem(deleteEnq);
                                            System.out.println("Enquiry deleted successfully");
                                        }
                                        else{

                                            System.out.println("You are not the sender of this enquiry");
                                        }
                                        break;
                                    case 10: // withdraw from camp
                                        System.out.println("Please enter the name of the camp that you wish to withdraw");
                                        String campWithdraw = sc.nextLine();
                                        Camp campToWithdrawObj = campStorage.getData(campWithdraw);
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
                                        System.out.println("Please enter the new password:");
                                        String userNewPassword = sc.nextLine();
                                        studentAccount.setPassword(userNewPassword);
                                        System.out.println("Password Changed Successfully");
                                        break;
                                    case 12: // enter camp committee mode
                                        String studentCampComm = studentAccount.getCampCommOf();
                                        if (studentCampComm.equals("")){
                                            System.out.println("You are not a Camp Committee member!");
                                        }
                                        else {
                                            Camp studentCampCommObj = campStorage.getData(studentCampComm);
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
                                                        suggestionStorage.addItem(new Suggestion(studentAccount.getUserId(), userSuggestion, IdGenerator.generateID(suggestionStorage), studentAccount.getCampCommOf()));
                                                        studentAccount.addPoints();
                                                        System.out.println(("Suggestion created successfully"));
                                                        break;
                                                    // 3) View suggestion
                                                    case 3:
                                                        ArrayList<Suggestion> userSuggestions = suggestionStorage.getData();
                                                        boolean noSuggestion = true;
                                                        for (Suggestion suggestion : userSuggestions){
                                                            if(suggestion.getSuggestor().equals(studentAccount.getUserId()))
                                                            {
                                                                noSuggestion = false;
                                                                System.out.println("The suggestion ID is " + suggestion.getSuggestionId());
                                                                System.out.println("The suggestion is " + suggestion.getSuggestion());
                                                                System.out.println("The process status is " + suggestion.getProcessed());
                                                                System.out.println("The approval status is " + suggestion.getApproval() + "\n");
                                                            }
                                                        }
                                                        if(noSuggestion){
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
                                                        Suggestion editSuggestion = suggestionStorage.getData(userEditSuggestion);
                                                        if(editSuggestion.getSuggestor().equals(studentAccount.getUserId())) {
                                                            if (editSuggestion.getProcessed()) {
                                                                System.out.println("Suggestion has been processed already");
                                                            } else {
                                                                System.out.println("What is your new suggestion?");
                                                                String newSuggestion = sc.nextLine();
                                                                editSuggestion.setSuggestion(newSuggestion);
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
                                                        Suggestion deleteSuggest = suggestionStorage.getData(userDelSuggest);
                                                        if(deleteSuggest.getSuggestor().equals(studentAccount.getUserId())){
                                                            suggestionStorage.deleteItem(deleteSuggest);
                                                            System.out.println("Suggestion deleted successfully");
                                                        }
                                                        else{
                                                            System.out.println("You are not the suggester of this suggestion");
                                                        }
                                                        break;
                                                    case 6: // View enquiries
                                                        enquiryStorage.printAllData(studentCampCommObj.getName());
                                                        break;
                                                    case 7: // Reply enquiries
                                                        enquiryStorage.printAllData(studentCampCommObj.getName());
                                                        System.out.println("Which enquiry would you like to respond? Enter the enquiry ID");
                                                        String tarEnquiryId = sc.nextLine();
                                                        Enquiry tarEnquiry = enquiryStorage.getData(tarEnquiryId);
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

                                                    case 8:  //Generate list of attendees or camp committee either in txt or csv format
                                                        System.out.println("Would you like to generate list of attendees or camp committee");
                                                        System.out.println("1) Generate list of participants only");
                                                        System.out.println("2) Generate list of camp committee only");
                                                        System.out.println("3) Generate list without filters");
                                                        int filterChoice = sc.nextInt();
                                                        while(filterChoice != 1 && filterChoice !=2 && filterChoice !=3) {
                                                            System.out.println("Invalid choice. Please select again!");
                                                            filterChoice = sc.nextInt();
                                                        }
                                                        sc.nextLine();
                                                        System.out.println("Which output format will you like?");
                                                        System.out.println("1) CSV file");
                                                        System.out.println("2) TXT file");
                                                        int outputFormatChoice = sc.nextInt();
                                                        while(outputFormatChoice!=1 && outputFormatChoice!=2){
                                                            System.out.println("Invalid choice. Please select again");
                                                            outputFormatChoice = sc.nextInt();
                                                        }
                                                        sc.nextLine();
                                                        System.out.println("Please enter the name of the file (without file extension).");
                                                        String fileName = sc.nextLine();
                                                        switch(filterChoice){
                                                            case 1:
                                                                switch(outputFormatChoice){
                                                                    case 1:
                                                                        //output data in csv file
                                                                        fileName = fileName + ".csv";
                                                                        studentAccount.generateAttendeeListCSV(new ParticipantFilter(), campStorage, studentStorage, fileName, studentAccount);
                                                                        break;
                                                                    case 2:
                                                                        // output data in txt file
                                                                        fileName = fileName + ".txt";
                                                                        studentAccount.generateAttendeeListTXT(new ParticipantFilter(), campStorage, studentStorage, fileName, studentAccount);
                                                                        break;
                                                                }
                                                                break;
                                                            case 2:
                                                                switch(outputFormatChoice){
                                                                    case 1:
                                                                        //output data in csv file
                                                                        fileName = fileName + ".csv";
                                                                        studentAccount.generateAttendeeListCSV(new CampCommFilter(), campStorage, studentStorage, fileName, studentAccount);
                                                                        break;
                                                                    case 2:
                                                                        // output data in txt file
                                                                        fileName = fileName + ".txt";
                                                                        studentAccount.generateAttendeeListTXT(new CampCommFilter(), campStorage, studentStorage, fileName, studentAccount);
                                                                        break;
                                                                }
                                                                break;
                                                            case 3: // No filter
                                                                switch(outputFormatChoice){
                                                                    case 1:
                                                                        //output data in csv file
                                                                        fileName = fileName + ".csv";
                                                                        studentAccount.generateAttendeeListCSV(null, campStorage, studentStorage, fileName, studentAccount);
                                                                        break;
                                                                    case 2:
                                                                        // output data in txt file
                                                                        fileName = fileName + ".txt";
                                                                        studentAccount.generateAttendeeListTXT(null, campStorage, studentStorage, fileName, studentAccount);
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
                    staffWriter.performWrite("staff.csv", staffStorage);
                    studentWriter.performWrite("student.csv", studentStorage);
                    enquiryWriter.performWrite("enquiry.csv", enquiryStorage);
                    suggestionWriter.performWrite("suggestion.csv", suggestionStorage);
                    campWriter.performWrite("camp.csv", campStorage);
                    System.out.println("Data written to files.");
                    System.exit(0);
            }
        }
    }
}
