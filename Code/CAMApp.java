import java.util.Scanner;
import java.util.Objects;
import java.util.Date;
import java.util.ArrayList;

/**
 * CAMApp is the main class of the CAM application.
 * @version 1.0
 * @since 2023-11-24
 */
public class CAMApp {
    /**
     * The main method of the CAM application.
     * @param args The arguments to be passed in.
     */
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
        System.out.println("[ Data loaded successfully ]");
        // init required dependencies
        Scanner sc = new Scanner(System.in);
        boolean logInSuccessfully;

        while (true) {
            logInSuccessfully = false;
            int userChoice=0;
            UiPrinter.printLoginInMenu();
            System.out.print(">> ");
            if(sc.hasNextInt()){
                userChoice = sc.nextInt();
                sc.nextLine();
            }
            while (userChoice != 1 && userChoice != 2 && userChoice != 3) {
                System.out.println("[ Error... Please select again ]");
                UiPrinter.printLoginInMenu();
                if(sc.hasNextInt()){
                    userChoice = sc.nextInt();
                }
                sc.nextLine();
            }
            // this switch case is for user to log in.
            switch (userChoice) {

                case 1: // staff login
                    while (!logInSuccessfully) {
                        System.out.println("> Please enter your Staff Username <");
                        System.out.print(">> ");
                        String staffUser = sc.nextLine();
                        System.out.println("> Please enter your Staff Password <");
                        System.out.print(">> ");
                        String staffPassword = sc.nextLine();
                        StaffAccount staffAccount = staffStorage.getData(staffUser);
                        if (Objects.isNull(staffAccount)|| !staffAccount.getPassword().equals(staffPassword)){
                            int userTryAgain = 0;
                            while(userTryAgain != 1 && userTryAgain != 2) {
                                System.out.println("[ Wrong username or password ]");
                                System.out.println("1) Try again");
                                System.out.println("2) Return to the previous page");
                                System.out.print(">> ");
                                if(sc.hasNextInt()){
                                    userTryAgain = sc.nextInt();
                                }
                                sc.nextLine();
                            }

                            switch(userTryAgain){
                                case 1:
                                    break;
                                case 2:
                                    logInSuccessfully = true;
                                    break;
                            }
                        }
                        else {
                            System.out.println("[ Login Successful! ]");
                            logInSuccessfully = true;
                            // Since the user log in successfully, we will carry on with the staff operation
                            //print the menu for the staff
                            int staffChoice = 0;
                            System.out.println("[ Welcome " + staffAccount.getName() + "! ]");
                            while(staffChoice != 15) {
                                UiPrinter.printStaffMenu();
                                System.out.print(">> ");
                                if(sc.hasNextInt()){
                                    staffChoice = sc.nextInt();
                                }
                                sc.nextLine();
                                switch(staffChoice){
                                    case 1: // Create camps
                                        //Name of camp
                                        System.out.println("> Please enter the name of the camp <");
                                        System.out.print(">> ");
                                        String name = sc.nextLine();
                                        while(!Objects.isNull(campStorage.getData(name.trim())))
                                        {
                                            System.out.println("[ Camp name exists... Please select another name ]");
                                            System.out.println("> Please enter the name of the camp <");
                                            System.out.print(">> ");
                                            name = sc.nextLine();
                                        }
                                        Date startDate = null;
                                        Date endDate = null;
                                        Date regDeadline = null;
                                        //Start date of camp
                                        System.out.println("> Please enter the start date (dd/MM/yyyy) <");
                                        System.out.print(">> ");
                                        String startDateString = sc.next();
                                        while (startDate == null || new Date().after(startDate)) {
                                            startDate = DateUtility.stringToDate(startDateString);
                                            if (startDate == null) {
                                                startDateString = sc.next();
                                            }
                                            //Error handling
                                            else if (new Date().after(startDate)) {
                                                System.out.println("[ Start date cannot be before today's date, please enter again ]");
                                                startDate = null;
                                                System.out.println("> Please enter the start date (dd/MM/yyyy) <");
                                                System.out.print(">> ");
                                                startDateString = sc.next();
                                            }
                                        }
                                        //End date of camp
                                        System.out.println("> Please enter the end date (dd/MM/yyyy) <");
                                        System.out.print(">> ");
                                        String endDateString = sc.next();
                                        while (endDate == null || startDate.after(endDate)) {
                                            endDate = DateUtility.stringToDate(endDateString);
                                            if (endDate == null) {
                                                endDateString = sc.next();
                                            }
                                            //Error handling
                                            else if (startDate.after(endDate)) {
                                                System.out.println("[ End date cannot be before start date, please enter again ]");
                                                endDate = null;
                                                System.out.println("> Please enter the end date (dd/MM/yyyy) <");
                                                System.out.print(">> ");
                                                endDateString = sc.next();
                                            }
                                        }
                                        //Registration deadline
                                        System.out.println("> Please enter the registration deadline (dd/MM/yyyy) <");
                                        System.out.print(">> ");
                                        String regDeadlineString = sc.next();
                                        while (regDeadline == null || new Date().after(regDeadline) || regDeadline.after(startDate)) {
                                            regDeadline = DateUtility.stringToDate(regDeadlineString);
                                            if (regDeadline == null) {
                                                regDeadlineString = sc.next();
                                            }
                                            //Error handling
                                            else if (new Date().after(regDeadline)) {
                                                System.out.println("[ Registration deadline cannot be before today's date, please enter again ]");
                                                regDeadline = null;
                                                System.out.println("> Please enter the registration deadline (dd/MM/yyyy) <");
                                                System.out.print(">> ");
                                                regDeadlineString = sc.next();
                                            }
                                            //Error handling
                                            else if (regDeadline.after(startDate)) {
                                                System.out.println("[ Registration deadline cannot be after start date, please enter again ]");
                                                regDeadline = null;
                                                System.out.println("> Please enter the registration deadline (dd/MM/yyyy) <");
                                                System.out.print(">> ");
                                                regDeadlineString = sc.next();
                                            }
                                        }
                                        sc.nextLine();
                                        //Setting user group
                                        System.out.println("> Would you like to open it up to NTU? (Y/N) <");
                                        System.out.print(">> ");
                                        String userGroupSel = sc.nextLine();
                                        String userGroup;
                                        //Error handling
                                        while(!(userGroupSel.equals("y")) && !(userGroupSel.equals("Y")) && !(userGroupSel.equals("N")) && !(userGroupSel.equals("n")))
                                        {
                                            System.out.println("[ Invalid choice. Please select again ]");
                                            System.out.println("> Would you like to open it up to NTU?(Y/N) <");
                                            System.out.print(">> ");
                                            userGroupSel = sc.nextLine();
                                        }
                                        if(userGroupSel.equals("y") || userGroupSel.equals("Y")){
                                            userGroup = "NTU";
                                        }
                                        else{
                                            userGroup = staffAccount.getFaculty();
                                        }
                                        //Location of camp
                                        System.out.println("> Please enter the location <");
                                        System.out.print(">> ");
                                        String location = sc.nextLine();
                                        int totalSlots = -1; // Set to -1 to enter while loop
                                        while (totalSlots < 2) {
                                            //Setting total slots of camp
                                            System.out.println("> Please enter the number of slots for this camp (Min 2) <");
                                            System.out.print(">> ");
                                            if(sc.hasNextInt()){
                                                totalSlots = sc.nextInt();
                                            }
                                            sc.nextLine();

                                        }
                                        int campCommSlots = -1; // Set to -1 to enter while loop
                                        while (campCommSlots > 10 || campCommSlots < 1 || campCommSlots >= totalSlots) {
                                            //Setting camp committee slots
                                            System.out.println("> Please enter the number of Camp Committee slots for this camp (Min 1, Max 10, Cannot be more than or equal to total slots) <");
                                            System.out.print(">> ");
                                            if(sc.hasNextInt()){
                                                campCommSlots = sc.nextInt();
                                            }
                                        }
                                        sc.nextLine();
                                        //Description of camp
                                        System.out.println("> Please enter a description for the camp <");
                                        System.out.print(">> ");
                                        String description = sc.nextLine();
                                        //Visibility of camp
                                        System.out.println("> Do you want this camp to be visible?(Y/N) <");
                                        System.out.print(">> ");
                                        String visibilitySelection = sc.next();
                                        boolean visibility;
                                        //Error handling
                                        while(!(visibilitySelection.equalsIgnoreCase("y")) && !(visibilitySelection.equalsIgnoreCase("n")))
                                        {
                                            System.out.println("[ Invalid choice. Please select again ]");
                                            System.out.println("> Do you want this camp to be visible?(Y/N) <");
                                            System.out.print(">> ");
                                            visibilitySelection = sc.next();
                                        }
                                        visibility = visibilitySelection.equalsIgnoreCase("y");

                                        staffAccount.createCamp(name, startDate, endDate, regDeadline, userGroup, location, totalSlots, campCommSlots, description, visibility, campStorage);
                                        break;

                                    case 2: // edit camps
                                        System.out.println("> Please enter the name of the camp that you wish to edit <");
                                        System.out.print(">> ");
                                        String editCamp = sc.nextLine();
                                        Camp editCampObj = campStorage.getData(editCamp);
                                        if(editCampObj == null){
                                            System.out.println("[ Camp do not exist ]");
                                            break;
                                        }
                                        else {
                                            //Ensure that staff have permission to edit this camp
                                            if (editCampObj.getStaffIC().equals(staffAccount.getUserId())) {
                                                int staffEditChoice = 0;
                                                while (staffEditChoice != 7) {
                                                    UiPrinter.printStaffEditMenu();
                                                    System.out.print(">> ");
                                                    if(sc.hasNextInt()){
                                                        staffEditChoice = sc.nextInt();
                                                    }
                                                    sc.nextLine();
                                                    switch (staffEditChoice) {
                                                        case 1: // edit start date
                                                            staffAccount.editCamp(editCampObj, new EditStartDate());
                                                            break;

                                                        case 2: // edit end date
                                                            staffAccount.editCamp(editCampObj, new EditEndDate());
                                                            break;

                                                        case 3: // edit registration deadline
                                                            staffAccount.editCamp(editCampObj, new EditRegDeadline());
                                                            break;

                                                        case 4: // edit location
                                                            staffAccount.editCamp(editCampObj, new EditLocation());
                                                            break;

                                                        case 5: // edit description
                                                            staffAccount.editCamp(editCampObj, new EditDescription());
                                                            break;

                                                        case 6: // toggle visibility
                                                            staffAccount.editCamp(editCampObj, new ToggleVisibility());
                                                            break;

                                                        case 7: // exit edit page
                                                            System.out.println("[ Exiting edit page ]");
                                                            break;

                                                        default: // Asks user for an input again
                                                            System.out.println("[ Invalid choice. Please select again. ]");
                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("[ Error... you are not the staff in charge of this camp. ]");
                                            }
                                        }

                                        break;
                                    case 3: // delete camps
                                        System.out.println("> Please enter the name of the camp that you wish to remove <");
                                        System.out.print(">> ");
                                        String tarCamp = sc.nextLine();
                                        Camp targetCampObj = campStorage.getData(tarCamp);
                                        //Error handling
                                        if (targetCampObj == null){
                                            System.out.println("[ Camp do not exist ]");
                                            break;
                                        }
                                        else{
                                            //Ensure that staff have permission to delete this camp
                                            if(targetCampObj.getStaffIC().equals(staffAccount.getUserId()) && targetCampObj.getAttendees().isEmpty() && targetCampObj.getCampCommitteeMembers().isEmpty()){
                                                campStorage.removeItem(targetCampObj);
                                                System.out.println("[ Camp " + tarCamp +" removed successfully ]");
                                        }
                                        else{
                                            System.out.println("[ Error removing... you are not the staff in charge of this camp. ]");
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
                                        System.out.println("> Please enter the name of the camp <");
                                        System.out.print(">> ");
                                        String campName = sc.nextLine();
                                        Camp campNameObj = campStorage.getData(campName);
                                        //Error handling
                                        if(campNameObj == null){
                                            System.out.println("[ Camp do not exist! ]");
                                            break;
                                        }
                                        else{
                                            //Ensure that staff have permission to view enquiries of this camp
                                            if(campNameObj.getStaffIC().equals(staffAccount.getUserId())){
                                                enquiryStorage.printAllData(campName);
                                            }
                                            else
                                            {
                                                System.out.println("[ Error... you are not the staff in charge of this camp. ]");
                                            }
                                        }
                                        break;
                                    case 7: // reply to enquiries
                                        System.out.println("> Please enter the name of the camp <");
                                        System.out.print(">> ");
                                        String campNameEnquires = sc.nextLine();
                                        Camp campNameObjEnquires = campStorage.getData(campNameEnquires);
                                        //Error handling
                                        if(campNameObjEnquires == null){
                                            System.out.println("[ Camp do not exist! ]");
                                            break;
                                        }
                                        else{
                                            //Ensure that staff have permission to reply enquiries to this camp
                                            if(campNameObjEnquires.getStaffIC().equals(staffAccount.getUserId())){
                                                enquiryStorage.printAllData(campNameEnquires);
                                                System.out.println("> Please enter the enquiry ID that you wish to answer <");
                                                System.out.print(">> ");
                                                String enquiryID = sc.nextLine();
                                                Enquiry tarEnquiry = enquiryStorage.getData(enquiryID);
                                                //Error handling
                                                if(tarEnquiry == null){
                                                    System.out.println("[ Enquiry could not be found. ]");
                                                    break;
                                                }
                                                //Check to see whether the enquiry has been responded
                                                else {
                                                    if(tarEnquiry.getResponse()!=null){
                                                        System.out.println("[ There is already a response. ]");
                                                    }
                                                    else {
                                                        //Staff response
                                                        System.out.println("> Please enter your response <");
                                                        System.out.print(">> ");
                                                        String response = sc.nextLine();
                                                        tarEnquiry.setResponse(response);
                                                        tarEnquiry.setResponder(staffAccount.getUserId());
                                                    }
                                                }

                                            }
                                            else
                                            {
                                                System.out.println("[ Error... you are not the staff in charge of this camp. ]");
                                            }
                                        }
                                        break;
                                    case 8: // view suggestions
                                        System.out.println("> Please enter the name of the camp <");
                                        System.out.print(">> ");
                                        String campNameSuggestion = sc.nextLine();
                                        Camp campNameObjSuggestion = campStorage.getData(campNameSuggestion);
                                        //Error handling
                                        if(campNameObjSuggestion == null){
                                            System.out.println("[ Camp do not exist! ]");
                                            break;
                                        }
                                        else {
                                            //Ensure that staff have permission to view suggestions to this camp
                                            if (campNameObjSuggestion.getStaffIC().equals(staffAccount.getUserId())) {
                                                suggestionStorage.printData(campNameSuggestion);
                                            }
                                            else {
                                                System.out.println("[ You are not the staff in charge! ]");
                                            }
                                        }
                                        break;
                                    case 9: // Process suggestions
                                        System.out.println("> Please enter the name of the camp <");
                                        System.out.print(">> ");
                                        String tarCampSuggestion = sc.nextLine();
                                        Camp tarCampNameObjSuggestion = campStorage.getData(tarCampSuggestion);
                                        //Error handling
                                        if(tarCampNameObjSuggestion == null){
                                            System.out.println("[ Camp do not exist! ]");
                                            break;
                                        }
                                        else {
                                            //Ensure that staff have permission to process suggestions to this camp
                                            if (tarCampNameObjSuggestion.getStaffIC().equals(staffAccount.getUserId())) {
                                                System.out.println("> Please enter the Suggestion ID that you wish to approve/reject <");
                                                System.out.print(">> ");
                                                String tarSuggestionID = sc.nextLine();
                                                Suggestion tarSuggestion = suggestionStorage.getData(tarSuggestionID);
                                                //Error handling
                                                if(tarSuggestion == null){
                                                    System.out.println("[ Suggestion do not exist! ]");
                                                    break;
                                                }
                                                else{
                                                    //Check whether suggestions has been processed
                                                    if (tarSuggestion.getProcessed()){
                                                        System.out.println("[ Suggestion has been processed already! ]");
                                                    }
                                                    else {
                                                        //Processing suggestion
                                                        System.out.println("> Would you like to accept or reject this suggestion? <");
                                                        System.out.println("1) Accept");
                                                        System.out.println("2) Reject");
                                                        System.out.print(">> ");
                                                        int suggestionChoice = 0;
                                                        if(sc.hasNextInt()){
                                                            suggestionChoice=sc.nextInt();
                                                        }
                                                        sc.nextLine();
                                                        switch (suggestionChoice) {
                                                            case 1:
                                                                staffAccount.processSuggestion(tarSuggestion, studentStorage, true);
                                                                break;
                                                            case 2:
                                                                staffAccount.processSuggestion(tarSuggestion, studentStorage, false);
                                                                break;
                                                            default: // Asks user for an input again
                                                                System.out.println("[ Invalid choice. Please select again. ]");
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        break;
                                    case 10: // View camp attendees
                                        System.out.println("> Please enter the name of the camp <");
                                        System.out.print(">> ");
                                        String tarCampAttendee = sc.nextLine();
                                        Camp tarCampAttendeeObj = campStorage.getData(tarCampAttendee);
                                        //Error handling
                                        if(tarCampAttendeeObj == null){
                                            System.out.println("[ Camp do not exist! ]");
                                            break;
                                        }
                                        //Ensure that staff have permission to view camp attendees to this camp
                                        else {
                                            if(tarCampAttendeeObj.getStaffIC().equals(staffAccount.getUserId())){
                                                tarCampAttendeeObj.printAttendees();
                                            }
                                        }
                                        break;
                                    case 11: // View camp committee members
                                        System.out.println("> Please enter the name of the camp <");
                                        System.out.print(">> ");
                                        String tarCampComm = sc.nextLine();
                                        Camp tarCampCommObj = campStorage.getData(tarCampComm);
                                        //Error handling
                                        if(tarCampCommObj == null){
                                            System.out.println("[ Camp do not exist! ]");
                                            break;
                                        }
                                        else {
                                            //Ensure that staff have permission to view camp committee members of this camp
                                            if(tarCampCommObj.getStaffIC().equals(staffAccount.getUserId())){
                                                tarCampCommObj.printCampComm();
                                            } else { //Error handling
                                                System.out.println("[ You are not the staff in charge of this camp! ]");
                                            }
                                        }
                                        break;
                                    case 12:
                                        // Generate performance report for all camps the staff is in charge of
                                        // include the points of the camp committee members
                                        System.out.println("> Please enter the name of the file (without file extension) <");
                                        System.out.print(">> ");
                                        String performanceReportFileName = sc.nextLine();
                                        performanceReportFileName = performanceReportFileName + ".txt";
                                        staffAccount.generatePerformanceReport(campStorage, studentStorage, performanceReportFileName, new PerformanceReportTXTWriter());
                                        break;
                                    case 13: //Generate list of attendees or camp committee either in txt or csv format
                                        System.out.println("> Would you like to generate list of attendees or camp committee <");
                                        System.out.println("1) Generate list of attendees only");
                                        System.out.println("2) Generate list of camp committee only");
                                        System.out.println("3) Generate list without filters");
                                        System.out.print(">> ");

                                        int filterChoice = 0;
                                        if(sc.hasNextInt()){
                                            filterChoice=sc.nextInt();
                                        }
                                        sc.nextLine();
                                        while(filterChoice != 1 && filterChoice !=2 && filterChoice !=3) {
                                            System.out.println("[ Invalid choice. Please select again! ]"); //Error handling
                                            System.out.println("> Would you like to generate list of attendees or camp committee <");
                                            System.out.println("1) Generate list of attendees only");
                                            System.out.println("2) Generate list of camp committee only");
                                            System.out.println("3) Generate list without filters");
                                            System.out.print(">> ");
                                            if(sc.hasNextInt()){
                                                filterChoice = sc.nextInt();
                                            }
                                            sc.nextLine();
                                        }

                                        System.out.println("> Which output format will you like? <");
                                        System.out.println("1) CSV file");
                                        System.out.println("2) TXT file");
                                        System.out.print(">> ");
                                        int outputFormatChoice = 0;
                                        if(sc.hasNextInt()){
                                            outputFormatChoice = sc.nextInt();
                                        }
                                        sc.nextLine();
                                        while(outputFormatChoice!=1 && outputFormatChoice!=2){
                                            System.out.println("[ Invalid choice. Please select again! ]"); //Error handling
                                            System.out.print(">> ");
                                            if(sc.hasNextInt()){
                                                outputFormatChoice = sc.nextInt();
                                            }
                                            sc.nextLine();
                                        }
                                        //Input filename
                                        System.out.println("> Please enter the name of the file (without file extension) <");
                                        System.out.print(">> ");
                                        String fileName = sc.nextLine();
                                        switch(filterChoice){
                                            case 1:
                                                switch(outputFormatChoice){
                                                    case 1:
                                                        //output data in csv file
                                                        fileName = fileName + ".csv";
                                                        staffAccount.generateAttendeeList(new AttendeeFilter(), campStorage, studentStorage, fileName, staffAccount, new AttendeeListCSVWriter());
                                                        break;
                                                    case 2:
                                                        // output data in txt file
                                                        fileName = fileName + ".txt";
                                                        staffAccount.generateAttendeeList(new AttendeeFilter(), campStorage, studentStorage, fileName, staffAccount, new AttendeeListTXTWriter());
                                                        break;
                                                    default: // Asks user for an input again
                                                        System.out.println("[ Invalid choice. Please select again. ]");
                                                }
                                            break;
                                            case 2:
                                                switch(outputFormatChoice){
                                                    case 1:
                                                        //output data in csv file
                                                        fileName = fileName + ".csv";
                                                        staffAccount.generateAttendeeList(new CampCommFilter(), campStorage, studentStorage, fileName, staffAccount, new AttendeeListCSVWriter());
                                                        break;
                                                    case 2:
                                                        // output data in txt file
                                                        fileName = fileName + ".txt";
                                                        staffAccount.generateAttendeeList(new CampCommFilter(), campStorage, studentStorage, fileName, staffAccount, new AttendeeListTXTWriter());
                                                        break;
                                                    default: // Asks user for an input again
                                                        System.out.println("[ Invalid choice. Please select again. ]");
                                                }
                                                break;
                                            case 3: // No filter
                                                switch(outputFormatChoice){
                                                    case 1:
                                                        //output data in csv file
                                                        fileName = fileName + ".csv";
                                                        staffAccount.generateAttendeeList(null, campStorage, studentStorage, fileName, staffAccount, new AttendeeListCSVWriter());
                                                        break;
                                                    case 2:
                                                        // output data in txt file
                                                        fileName = fileName + ".txt";
                                                        staffAccount.generateAttendeeList(null, campStorage, studentStorage, fileName, staffAccount, new AttendeeListTXTWriter());
                                                        break;
                                                    default: // Asks user for an input again
                                                        System.out.println("[ Invalid choice. Please select again. ]");
                                                }
                                                break;
                                            default: // Asks user for an input again
                                                System.out.println("[ Invalid choice. Please select again. ]");
                                        }
                                        break;
                                    case 14: // change password
                                        System.out.println("> Please enter the new password <");
                                        System.out.print(">> ");
                                        String userNewPassword = sc.nextLine();
                                        staffAccount.setPassword(userNewPassword);
                                        System.out.println("[ Password changed successfully. ]");
                                        break;
                                    case 15: // logout
                                        System.out.println("[ Goodbye " + staffAccount.getName() + " ! ]");
                                        break;
                                    default: // Asks user for an input again
                                        System.out.println("[ Invalid choice. Please select again. ]");
                                }
                            }

                        }
                    }

                    break;
                case 2: // student login
                    while (!logInSuccessfully) {
                        System.out.println("> Please enter your Student Username <");
                        System.out.print(">> ");
                        String studentUser = sc.nextLine();
                        System.out.println("> Please enter your Student Password <");
                        System.out.print(">> ");
                        String studentPassword = sc.nextLine();
                        StudentAccount studentAccount = studentStorage.getData(studentUser);
                        if(Objects.isNull(studentAccount)|| !studentAccount.getPassword().equals(studentPassword))
                        {
                            int userTryAgain = 0;
                            while(userTryAgain != 1 && userTryAgain != 2) {
                                System.out.println("[ Wrong username or password ]");
                                System.out.println("1) Try again");
                                System.out.println("2) Return to the previous page");
                                System.out.print(">> ");
                                if(sc.hasNextInt()){
                                    userTryAgain = sc.nextInt();
                                }
                                sc.nextLine();
                            }

                            switch ((userTryAgain)){
                                case 1:
                                    break;
                                case 2:
                                    logInSuccessfully = true;
                                    break;
                                default: // Asks user for an input again
                                    System.out.println("[ Invalid choice. Please select again. ]");
                            }
                        }
                        else {
                            System.out.println("[ Login Successful! ]");
                            logInSuccessfully = true;
                            int studentChoice = 0;
                            System.out.println("[ Welcome " + studentAccount.getName() + "! ]");
                            while(studentChoice != 13)
                            {
                                UiPrinter.printStudentMenu();
                                System.out.print(">> ");
                                if(sc.hasNextInt()){
                                    studentChoice = sc.nextInt();
                                }
                                sc.nextLine();
                                switch(studentChoice){
                                    case 1: //Print student profile
                                        studentAccount.printProfile();
                                        break;
                                    case 2: // view list of camp available
                                        campStorage.printData(studentAccount);
                                        break;
                                    case 3: // view the remaining slots for a camp
                                        System.out.println("> Which camp would you like to enquire? <");
                                        System.out.print(">> ");
                                        String userCamp = sc.nextLine();
                                        Camp userCampObj = campStorage.getData(userCamp);
                                        if ( userCampObj == null){
                                            System.out.println("[ Camp do not exist! ]"); //Error handling
                                            break;
                                        }
                                        else{
                                            //Checking whether visibility is on and correct User group
                                            if(userCampObj.getVisibility() && (userCampObj.getUserGroup().equals(studentAccount.getFaculty()) || userCampObj.getUserGroup().equals("NTU"))){
                                                System.out.println("[ Total slots remaining is " + campStorage.getData(userCamp).getRemainingSlots() + " ]");
                                            }
                                            else{
                                                //Error handling
                                                System.out.println("[ This camp is not available to you! ]");

                                            }
                                    }
                                        break;
                                    case 4: // register for camps
                                        System.out.println("> Which camp would you like to register for? <");
                                        System.out.print(">> ");
                                        String userRegCamp = sc.nextLine();
                                        Camp userRegCampObj = campStorage.getData(userRegCamp);
                                        if( userRegCampObj == null || !userRegCampObj.getVisibility()){
                                            System.out.println("[ Camp do not exist! ]"); //Error handling
                                            break;
                                        }
                                        else {
                                            //Checking whether correct user group
                                            if ((userRegCampObj.getUserGroup().equals(studentAccount.getFaculty()) || userRegCampObj.getUserGroup().equals("NTU"))){
                                                //Checking whether registration deadline is over
                                                if (new Date().before(userRegCampObj.getRegDeadline())){
                                                    //Checking remaining slots
                                                    if(userRegCampObj.getRemainingSlots() > 0){
                                                        //Checking whether user has already registered for this camp
                                                        if(!studentAccount.getCampsRegistered().contains(userRegCamp)){
                                                            //Checking whether user has withdrawn from this camp
                                                            if (!userRegCampObj.getWithdrawalList().contains(studentAccount.getUserId())){
                                                                //Checking whether user is a camp committee member
                                                                if(!studentAccount.getCampCommOf().equals(userRegCamp)){
                                                                    // Check if dates overlap for
                                                                    ArrayList<String> campsRegistered = studentAccount.getCampsRegistered();
                                                                    boolean overlaps = false;
                                                                    for (String cString : campsRegistered) {
                                                                        Camp c = campStorage.getData(cString);
                                                                        if (c == null) {
                                                                            System.out.println("[ Camp do not exist! ]"); //Error handling
                                                                            break;
                                                                        }
                                                                        //Checking whether the dates of the camps which user registered as attendees
                                                                        //overlap with new camp dates
                                                                        if (DateUtility.checkOverlap(userRegCampObj.getStartDate(), userRegCampObj.getEndDate(),
                                                                                c.getStartDate(), c.getEndDate())) {
                                                                            System.out.println("[ Camp " + c.getName() + " overlaps with this camp! ]");
                                                                            overlaps = true;
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (!overlaps && !studentAccount.getCampCommOf().isEmpty()) {
                                                                        Camp campCommCamp = campStorage.getData(studentAccount.getCampCommOf());
                                                                        if (campCommCamp == null) {
                                                                            System.out.println("[ Camp do not exist! ]"); //Error handling
                                                                            break;
                                                                        }
                                                                        //Checking whether the dates of the camps which user registered as camp committee
                                                                        //overlap with new camp dates
                                                                        if (DateUtility.checkOverlap(userRegCampObj.getStartDate(), userRegCampObj.getEndDate(),
                                                                                campCommCamp.getStartDate(), campCommCamp.getEndDate())) {
                                                                            System.out.println("[ Camp " + campCommCamp.getName() + " overlaps with this camp! ]");
                                                                            overlaps = true;
                                                                        }
                                                                    }
                                                                    if (!overlaps) {
                                                                        // All checks passed, add student to camp
                                                                        userRegCampObj.addAttendees(studentAccount.getUserId());
                                                                        studentAccount.addCamps(userRegCampObj.getName());
                                                                        System.out.println("[ Registration successful! ]");
                                                                    }
                                                                }
                                                                //Error handling
                                                                else{
                                                                    System.out.println("[ Cannot register for this camp as you are already camp committee for this camp! ]");
                                                                }
                                                            }
                                                            else{
                                                                System.out.println("[ You have withdrawn from this camp before! ]"); //Error handling
                                                            }
                                                        }
                                                        else{
                                                            System.out.println("[ You have already registered for this camp! ]"); //Error handling
                                                            }
                                                    }
                                                    else{
                                                        System.out.println("[ There is no more slots in this camp! ]"); //Error handling
                                                        }
                                                }
                                                else {
                                                    System.out.println("[ It is past the registration dateline! ]"); //Error handling
                                                }
                                            }
                                            else{
                                                System.out.println("[ This camp is not open to your faculty! ]"); //Error handling
                                            }
                                        }
                                        break;
                                    case 5: // register for camp comm
                                        if (studentAccount.getCampCommOf().isEmpty()){
                                            System.out.println("> Please enter the camp that you wish to register for as camp committee <");
                                            System.out.print(">> ");
                                            String campCommCamp = sc.nextLine();
                                            Camp campCommCampObj = campStorage.getData(campCommCamp);
                                            if (campCommCampObj == null) {
                                                System.out.println("[ Camp do not exist! ]"); //Error handling
                                                break;
                                            }
                                            //Check camp committee remaining slots
                                            else if (campCommCampObj.getRemainingSlotsCampComm() > 0) {
                                                // Check if withdraw before
                                                if (campCommCampObj.getWithdrawalList().contains(studentAccount.getUserId())) {
                                                    System.out.println("[ Cannot register as camp committee as you have withdrawn from camp! ]");
                                                } else {
                                                    // Check if already attendee
                                                    if(studentAccount.getCampsRegistered().contains(campCommCamp)){
                                                        System.out.println("[ Cannot register as camp committee. You are already an attendee! ]");
                                                    }
                                                    else {
                                                        // Check if date overlaps with registered camps
                                                        ArrayList<String> campsRegistered = studentAccount.getCampsRegistered();
                                                        if (campsRegistered == null) {
                                                            System.out.println("[ No Camps Registered! ]");
                                                            break;
                                                        }
                                                        boolean overlaps = false;
                                                        for (String cString : campsRegistered) {
                                                            Camp camp = campStorage.getData(cString);
                                                            if (camp == null) {
                                                                System.out.println("[ Camp do not exist! ]"); //Error handling
                                                                break;
                                                            }
                                                            if (DateUtility.checkOverlap(campCommCampObj.getStartDate(), campCommCampObj.getEndDate(),
                                                                    camp.getStartDate(), camp.getEndDate())) {
                                                                System.out.println("[ Camp " + camp.getName() + " overlaps with this camp! ]");
                                                                overlaps = true;
                                                                break;
                                                            }
                                                        }
                                                        // All checks passed
                                                        if (!overlaps) {
                                                            campCommCampObj.addCampCommitteeMember(studentAccount.getUserId());
                                                            studentAccount.setCampCommOf(campCommCampObj.getName());
                                                            System.out.println("[ You have successfully registered as a camp committee for the camp. ]");
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("[ There is no more camp committee slots ]"); //Error handling
                                            }
                                        }
                                        else{
                                            System.out.println("[ You are already a camp committee of "+ studentAccount.getCampCommOf() + " ]"); //Error handling
                                        }
                                        break;
                                    case 6: // submit enquiries
                                        System.out.println("> Which camp do you have enquiries about? <");
                                        System.out.print(">> ");
                                        String userEnquiryCamp = sc.nextLine();
                                        Camp userEnquiryCampObject = campStorage.getData(userEnquiryCamp);
                                        if(userEnquiryCampObject == null){
                                            System.out.println("[ Camp do not exist! ]"); //Error handling
                                            break;
                                        }
                                        else if(userEnquiryCampObject.getName().equals(studentAccount.getCampCommOf())){
                                            System.out.println("[ You are not the camp committee member. ]"); //Error handling
                                            break;
                                        }
                                        else{
                                            //Checking visibility of camp and correct user group
                                            if(!userEnquiryCampObject.getVisibility() || (!userEnquiryCampObject.getUserGroup().equals("NTU") &&
                                                    !userEnquiryCampObject.getUserGroup().equals(studentAccount.getFaculty()))){
                                                System.out.println("[ Camp is not open for your faculty! ]");
                                            }
                                            else{
                                                System.out.println("> Please enter your enquiry (no blanks) <");
                                                System.out.print(">> ");
                                                String userEnquiry = sc.nextLine();
                                                if (userEnquiry.isBlank()) {
                                                    System.out.println("[ No enquiry created. ]"); //Error handling
                                                    break;
                                                }
                                                // Create the new enquiry
                                                Enquiry newEnquiry = studentAccount.createEnquiry(userEnquiry, userEnquiryCamp, IdGenerator.generateID(enquiryStorage));
                                                // Add new enquiry to storage
                                                enquiryStorage.addItem(newEnquiry);
                                                System.out.println("[ Enquiry submitted successfully. ]");
                                                System.out.println("[ Enquiry ID is " + newEnquiry.getEnquiryId() + " ]");
                                                System.out.println("[ Use this enquiry ID to edit or delete your enquiry. ]");
                                            }
                                        }
                                        break;
                                    case 7: // view enquires made
                                        enquiryStorage.printSenderData(studentAccount.getUserId());
                                        break;
                                    case 8: // edit enquires made
                                        System.out.println("> Which enquiry would you like to edit? Enter your enquiryID <");
                                        System.out.print(">> ");
                                        String userEditEnq = sc.nextLine();
                                        Enquiry editEnq = enquiryStorage.getData(userEditEnq);
                                        if (editEnq == null){
                                            System.out.println("[ Enquiry do not exist! ]"); //Error handling
                                            break;
                                        }
                                        //Checking whether enquiry is made by user
                                        if(editEnq.getSender().equals(studentAccount.getUserId())) {
                                            if (editEnq.getResponse()==null && editEnq.getResponder()==null) {
                                                System.out.println("[ What is your new enquiry (no blanks)? ]");
                                                String newEnq = sc.nextLine();
                                                if(newEnq.isBlank()) {
                                                    System.out.println("[ No enquiry edited. (Blanks found) ]"); //Error handling
                                                    break;
                                                }
                                                else {
                                                    studentAccount.editEnquiry(editEnq, newEnq);
                                                }
                                                System.out.println("[ New enquiry has been saved. ]");
                                            }
                                            else {
                                                System.out.println("[ Error. Enquiry has been responded to. ]"); //Error handling
                                            }
                                        }
                                        else {
                                            System.out.println("[ You are not the sender of this enquiry. ]"); //Error handling
                                        }
                                        break;
                                    case 9: // delete enquires made
                                        System.out.println("> Which enquiry would you like to delete? Enter your enquiryID <");
                                        System.out.print(">> ");
                                        String userDeleteEnq = sc.next();
                                        //Checking whether enquiry is made by user
                                        Enquiry deleteEnq = enquiryStorage.getData(userDeleteEnq);
                                        if (deleteEnq == null){
                                            System.out.println("[ Enquiry do not exist! ]"); //Error handling
                                            break;
                                        }
                                        else if (deleteEnq.getResponse()!=null || deleteEnq.getResponder()!=null){
                                            System.out.println("[ Error. Enquiry has been responded to. ]"); //Error handling
                                            break;
                                        }
                                        else if(deleteEnq.getSender().equals(studentAccount.getUserId())){
                                            enquiryStorage.deleteItem(deleteEnq);
                                            System.out.println("[ Enquiry deleted successfully. ]");
                                        }
                                        else{
                                            System.out.println("[ You are not the sender of this enquiry. ]"); //Error handling
                                        }
                                        break;
                                    case 10: // withdraw from camp
                                        System.out.println("> Please enter the name of the camp that you wish to withdraw <");
                                        System.out.print(">> ");
                                        String campWithdraw = sc.nextLine();
                                        Camp campToWithdrawObj = campStorage.getData(campWithdraw);
                                        if (campToWithdrawObj == null) {
                                            System.out.println("[ Camp do not exist! ]"); //Error handling
                                            break;
                                        }
                                        boolean result = studentAccount.removeCamps(campWithdraw);
                                        if (result){
                                            System.out.println("[ Camp Removed Successfully. ]");
                                            campToWithdrawObj.removeAttendees(studentAccount.getUserId());
                                        }
                                        else{
                                            //Checking whether user is part of the camp's camp committee
                                            if(studentAccount.getCampCommOf().equals(campWithdraw)){
                                                System.out.println("[ You are the Camp Committee of this camp, hence you cannot withdraw! ]");
                                            }
                                            else{
                                                //Error handling
                                                System.out.println("[ You did not register for this camp! ]");
                                            }
                                        }
                                        break;
                                    case 11: // change password
                                        System.out.println("> Please enter the new password <");
                                        System.out.print(">> ");
                                        String userNewPassword = sc.nextLine();
                                        studentAccount.setPassword(userNewPassword);
                                        System.out.println("[ Password changed successfully ]");
                                        break;
                                    case 12: // enter camp committee mode
                                        String studentCampComm = studentAccount.getCampCommOf();
                                        if (studentCampComm.isEmpty()){
                                            System.out.println("[ You are not a Camp Committee member! ]"); //Error handling
                                        }
                                        else {
                                            Camp studentCampCommObj = campStorage.getData(studentCampComm);
                                            if (studentCampCommObj == null) {
                                                System.out.println("[ Camp do not exist! ]"); //Error handling
                                                break;
                                            }
                                            int studentCampCommChoice = 0;
                                            while(studentCampCommChoice != 10) {
                                                UiPrinter.printCampCommitteeMenu();
                                                System.out.print(">> ");
                                                if(sc.hasNextInt()){
                                                    studentCampCommChoice = sc.nextInt();
                                                }
                                                sc.nextLine();
                                                switch (studentCampCommChoice) {
                                                    // 1) View camps that you have registered
                                                    case 1:
                                                        studentAccount.printProfile();
                                                        break;
                                                    // 2) Submit suggestions for camps
                                                    case 2:
                                                        System.out.println("> Please enter the suggestion for " + studentCampCommObj.getName() + " (no blanks) <");
                                                        System.out.print(">> ");
                                                        String userSuggestionMsg = sc.nextLine();
                                                        if(userSuggestionMsg.isBlank()) {
                                                            System.out.println("[ No suggestion created (blanks found) ]"); //Error handling
                                                            break;
                                                        }
                                                        // add in the suggestion ID
                                                        Suggestion userSuggestion = studentAccount.createSuggestion(userSuggestionMsg, IdGenerator.generateID(suggestionStorage));
                                                        suggestionStorage.addItem(userSuggestion);
                                                        studentAccount.addPoints();
                                                        System.out.println(("[ Suggestion created successfully. ]"));
                                                        break;
                                                    // 3) View suggestion
                                                    case 3:
                                                        ArrayList<Suggestion> userSuggestions = suggestionStorage.getData();
                                                        if (userSuggestions == null) {
                                                            System.out.println("[ No suggestions in storage. ]"); //Error handling
                                                            break;
                                                        }
                                                        boolean noSuggestion = true;
                                                        for (Suggestion suggestion : userSuggestions){
                                                            if(suggestion.getSuggester().equals(studentAccount.getUserId()))
                                                            {
                                                                noSuggestion = false;
                                                                System.out.println("[ The suggestion ID is " + suggestion.getSuggestionId());
                                                                System.out.println("The suggestion is " + suggestion.getSuggestion());
                                                                System.out.println("The process status is " + suggestion.getProcessed());
                                                                System.out.println("The approval status is " + suggestion.getApproval() + " ]");
                                                            }
                                                        }
                                                        if(noSuggestion){
                                                            System.out.println("[ You do not have any suggestion. ]"); //Error handling
                                                        }
                                                        break;
                                                    // 4) Edit suggestion
                                                    case 4:
                                                        System.out.println("> Which suggestion would you like to edit? Enter your suggestionID (no blanks <)");
                                                        System.out.print(">> ");
                                                        String userEditSuggestion = sc.nextLine();
                                                        if(userEditSuggestion.isBlank()) {
                                                            System.out.println("[ No suggestion edited (blanks found) ]"); //Error handling
                                                            break;
                                                        }
                                                        Suggestion editSuggestion = suggestionStorage.getData(userEditSuggestion);
                                                        if (editSuggestion == null){
                                                            System.out.println("[ Suggestion do not exist! ]"); //Error handling
                                                            break;
                                                        }
                                                        //Check to ensure suggestion is made by user
                                                        if(editSuggestion.getSuggester().equals(studentAccount.getUserId())) {
                                                            //Check to see whether suggestion has been approved
                                                            if (editSuggestion.getProcessed()) {
                                                                System.out.println("[ Suggestion has already been processed ]");
                                                            } else {
                                                                System.out.println("> What is your new suggestion? <");
                                                                System.out.print(">> ");
                                                                String newSuggestion = sc.nextLine();
                                                                if(newSuggestion.isBlank()) {
                                                                    System.out.println("[ No suggestion edited (blanks found) ]"); //Error handling
                                                                    break;
                                                                }
                                                                else {
                                                                    studentAccount.editSuggestion(editSuggestion, newSuggestion);
                                                                }
                                                                System.out.println("[ New suggestion has been saved. ]");
                                                            }
                                                        }
                                                        else{
                                                            System.out.println("[ You are not the suggester of this suggestion! ]"); //Error handling
                                                        }
                                                        break;
                                                    // 5) Delete suggestion
                                                    case 5:
                                                        System.out.println("> Which suggestion would you like to delete? Enter your suggestionID <");
                                                        System.out.print(">> ");
                                                        String userDelSuggest = sc.next();
                                                        Suggestion deleteSuggest = suggestionStorage.getData(userDelSuggest);
                                                        if (deleteSuggest == null){
                                                            System.out.println("[ Suggestion do not exist! ]"); //Error handling
                                                            break;
                                                        }
                                                        // check if suggestion is processed
                                                        if (deleteSuggest.getProcessed()) {
                                                            System.out.println("[ Cannot Delete! Suggestion has already been processed! ]");
                                                            break;
                                                        }
                                                        //Check whether suggestion is made by user
                                                        if(deleteSuggest.getSuggester().equals(studentAccount.getUserId())){
                                                            suggestionStorage.deleteItem(deleteSuggest);
                                                            System.out.println("[ Suggestion deleted successfully. ]");
                                                        }
                                                        else{
                                                            System.out.println("[ You are not the suggester of this suggestion! ]"); //Error handling
                                                        }
                                                        break;
                                                    case 6: // View enquiries
                                                        enquiryStorage.printAllData(studentCampCommObj.getName());
                                                        break;
                                                    case 7: // Reply enquiries
                                                        enquiryStorage.printAllData(studentCampCommObj.getName());
                                                        System.out.println("> Which enquiry would you like to respond? Enter the enquiry ID <");
                                                        System.out.print(">> ");
                                                        String tarEnquiryId = sc.nextLine();
                                                        Enquiry targetEnquiry = enquiryStorage.getData(tarEnquiryId);
                                                        if(targetEnquiry == null){
                                                            System.out.println("[ Enquiry could not be found. ]"); //Error handling
                                                        }
                                                        else {
                                                            // check if there is response
                                                            if (targetEnquiry.getResponse() != null) {
                                                                System.out.println("[ There is already a response to this enquiry. ]");
                                                            }
                                                            // check if user sent enquiry before becoming camp comm member
                                                            else if (targetEnquiry.getSender().equals(studentAccount.getUserId())) {
                                                                System.out.println("[ You are the sender of this enquiry! ]");
                                                            }
                                                            else {
                                                                System.out.println("> Please enter your response <");
                                                                System.out.print(">> ");
                                                                String response = sc.nextLine();
                                                                targetEnquiry.setResponse(response);
                                                                targetEnquiry.setResponder(studentAccount.getUserId());
                                                                studentAccount.addPoints();
                                                            }
                                                        }
                                                        break;

                                                    case 8:  //Generate list of attendees or camp committee either in txt or csv format
                                                        int filterChoice = 0;
                                                        System.out.println("> Would you like to generate list of attendees or camp committee <");
                                                        System.out.println("1) Generate list of attendees only");
                                                        System.out.println("2) Generate list of camp committee only");
                                                        System.out.println("3) Generate list without filters");
                                                        System.out.print(">> ");
                                                        if (sc.hasNextInt()) {
                                                            filterChoice = sc.nextInt();
                                                        }
                                                        sc.nextLine();
                                                        while(filterChoice != 1 && filterChoice !=2 && filterChoice !=3) {
                                                            System.out.println("[ Invalid choice. Please select again! ]"); //Error handling
                                                            if (sc.hasNextInt()) {
                                                                filterChoice = sc.nextInt();
                                                            }
                                                            sc.nextLine();
                                                        }
                                                        System.out.println("> Which output format will you like? <");
                                                        System.out.println("1) CSV file");
                                                        System.out.println("2) TXT file");
                                                        System.out.print(">> ");
                                                        int outputFormatChoice = 0;
                                                        if(sc.hasNextInt()){
                                                            outputFormatChoice = sc.nextInt();
                                                        }
                                                        sc.nextLine();
                                                        while(outputFormatChoice!=1 && outputFormatChoice!=2){
                                                            System.out.println("[ Invalid choice. Please select again ]"); //Error handling
                                                            if (sc.hasNextInt()) {
                                                                outputFormatChoice = sc.nextInt();
                                                            }
                                                            sc.nextLine();
                                                        }
                                                        System.out.println("> Please enter the name of the file (without file extension). <");
                                                        System.out.print(">> ");
                                                        String fileName = sc.nextLine();
                                                        switch(filterChoice){
                                                            case 1:
                                                                switch(outputFormatChoice){
                                                                    case 1:
                                                                        //output data in csv file
                                                                        fileName = fileName + ".csv";
                                                                        studentAccount.generateAttendeeList(new AttendeeFilter(), campStorage, studentStorage, fileName, studentAccount, new AttendeeListCSVWriter());
                                                                        break;
                                                                    case 2:
                                                                        // output data in txt file
                                                                        fileName = fileName + ".txt";
                                                                        studentAccount.generateAttendeeList(new AttendeeFilter(), campStorage, studentStorage, fileName, studentAccount, new AttendeeListTXTWriter());
                                                                        break;
                                                                    default: // Asks user for an input again
                                                                        System.out.println("[ Invalid choice. Please select again. ]");

                                                                }
                                                                break;
                                                            case 2:
                                                                switch(outputFormatChoice){
                                                                    case 1:
                                                                        //output data in csv file
                                                                        fileName = fileName + ".csv";
                                                                        studentAccount.generateAttendeeList(new CampCommFilter(), campStorage, studentStorage, fileName, studentAccount, new AttendeeListCSVWriter());
                                                                        break;
                                                                    case 2:
                                                                        // output data in txt file
                                                                        fileName = fileName + ".txt";
                                                                        studentAccount.generateAttendeeList(new CampCommFilter(), campStorage, studentStorage, fileName, studentAccount, new AttendeeListTXTWriter());
                                                                        break;
                                                                    default: // Asks user for an input again
                                                                        System.out.println("[ Invalid choice. Please select again. ]");
                                                                }
                                                                break;
                                                            case 3: // No filter
                                                                switch(outputFormatChoice){
                                                                    case 1:
                                                                        //output data in csv file
                                                                        fileName = fileName + ".csv";
                                                                        studentAccount.generateAttendeeList(null, campStorage, studentStorage, fileName, studentAccount, new AttendeeListCSVWriter());
                                                                        break;
                                                                    case 2:
                                                                        // output data in txt file
                                                                        fileName = fileName + ".txt";
                                                                        studentAccount.generateAttendeeList(null, campStorage, studentStorage, fileName, studentAccount, new AttendeeListTXTWriter());
                                                                        break;
                                                                    default: // Asks user for an input again
                                                                        System.out.println("[ Invalid choice. Please select again. ]");
                                                                }
                                                                break;
                                                            default: // Asks user for an input again
                                                                System.out.println("[ Invalid choice. Please select again. ]");
                                                        }
                                                        break;

                                                    case 9: // Print camp information
                                                        UiPrinter.printCampInformation(studentCampCommObj);
                                                        break;

                                                    case 10: // Return back to student Mode
                                                        System.out.println("[ Returning to student mode... ]");
                                                        break;

                                                    default: // Asks user for an input again
                                                        System.out.println("[ Invalid choice. Please select again. ]");
                                                }
                                            }
                                        }
                                        break;
                                    case 13: // exit
                                        System.out.println("[ Goodbye " + studentAccount.getName() + "! ]");
                                        break;

                                    default: // Asks user for an input again
                                        System.out.println("[ Invalid choice. Please select again. ]");
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("[ Goodbye! ]");
                    staffWriter.performWrite("staff.csv", staffStorage);
                    studentWriter.performWrite("student.csv", studentStorage);
                    enquiryWriter.performWrite("enquiry.csv", enquiryStorage);
                    suggestionWriter.performWrite("suggestion.csv", suggestionStorage);
                    campWriter.performWrite("camp.csv", campStorage);
                    System.out.println("[ Data written to files. ]");
                    System.exit(0);

                default:
                    System.out.println("[ Invalid input! Please try again. ]");
            }
        }
    }
}
