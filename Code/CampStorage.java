import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class CampStorage extends Storage{
    private ArrayList<Camp> camps;

    public CampStorage() {
        super();
        camps = new ArrayList<Camp>();
    }

    // Print all camps
    public void printData() {
        for (Camp c : camps) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Camp Name: " + c.getName());
            System.out.println("Camp Start Date: " + c.getStartDate());
            System.out.println("Camp End Date: " + c.getEndDate());
            System.out.println("Camp Registration Deadline: " + c.getRegDeadline());
            System.out.println("Camp User Group: " + c.getUserGroup());
            System.out.println("Camp Location: " + c.getLocation());
            System.out.println("Remaining Slots: " + c.getRemaindingSlots());
            System.out.println("Remaining Camp Committee Slots: " + c.getRemaindingSlotsCampComm());
            System.out.println("Camp Description: " + c.getDescription());
            System.out.println("Camp Staff In-Charge: " + c.getStaffIC());
            System.out.println("Camp Visibility: " + c.getVisibility());
            System.out.println("Camp Withdrawal List: " + c.getWithdrawalList());
            System.out.println("Camp Attendees: " + c.getAttendees());
            System.out.println("Camp Committee Members: " + c.getCampComms());
        }
    }

    // Print camps created by a specific staff
    public void printData(StaffAccount staff) {
        for (Camp campStaff: camps){
            if (campStaff.getStaffIC().equals(staff.getUserId())){
                System.out.println("-------------------------------------------------------------");
                System.out.println("Camp Name: " + campStaff.getName());
                System.out.println("Camp Start Date: " + campStaff.getStartDate());
                System.out.println("Camp End Date: " + campStaff.getEndDate());
                System.out.println("Camp Registration Deadline: "  + campStaff.getRegDeadline());
                System.out.println("Camp User group: " + campStaff.getUserGroup());
                System.out.println("Camp Location: " + campStaff.getLocation());
                System.out.println("Remaining slots: " + campStaff.getRemaindingSlots());
                System.out.println("Remaining Camp Committee Slots: " + campStaff.getRemaindingSlotsCampComm());
                System.out.println("Camp Description: " + campStaff.getDescription());
                System.out.println("Camp Staff In-Charge: " + campStaff.getStaffIC());
                System.out.println("Camp Visibility: " + campStaff.getVisibility());
                System.out.println("Camp Withdrawal List: "  + campStaff.getWithdrawalList());
                System.out.println("Camp Attendees: " + campStaff.getAttendees());
                System.out.println("Camp Committee Members: " + campStaff.getCampComms());
            }
        }
    }

    // Print camps specific to student's faculty
    public void printData(StudentAccount student) {

        for (Camp campStudent: camps){
            if((campStudent.getUserGroup().equals(student.getFaculty()) || campStudent.getUserGroup().equals("NTU"))
                    && campStudent.getVisibility()){
                System.out.println("-------------------------------------------------------------");
                System.out.println("Camp Name: "  + campStudent.getName());
                System.out.println("Camp Start Date: " + campStudent.getStartDate());
                System.out.println("Camp End Date: " + campStudent.getEndDate());
                System.out.println("Camp Registration Deadline: " + campStudent.getRegDeadline());
                System.out.println("Camp User Group: " + campStudent.getUserGroup());
                System.out.println("Camp Location: " + campStudent.getLocation());
                System.out.println("Remaining Slots: " + campStudent.getRemaindingSlots());
                System.out.println("Remaining Camp Committee Slots " + campStudent.getRemaindingSlotsCampComm());
                System.out.println("Camp Description: " + campStudent.getDescription());
            }
        }
    }

    public ArrayList<Camp> getData() {
        return camps;
    }

    public Camp getData(String s) {
        for (Camp c : camps) {
            if (c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }

    public void addItem(Object o) {
        if (o instanceof Camp){
            camps.add((Camp)o);
            sortCamps();
        }
        else{
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }

    public void removeItem(Object o) {
        if (o instanceof Camp){
            camps.remove((Camp)o);
        }
        else{
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }

    public void sortCamps() {
        Collections.sort(camps);
    }

    public void populateData(CSVReader reader) {
        /*
            Col 0: Name
            Col 1: startDate
            Col 2: endDate
            Col 3: regDeadline
            Col 4: userGroup
            Col 5: location
            Col 6: totalSlots
            Col 7: campCommSlots
            Col 8: description
            Col 9: staffIC
            Col 10: Attendees
            Col 11: campComms
            Col 12: visibility
            Col 13: withdrawallist
        */
        ArrayList<String> campData = reader.performRead("camp.csv");
        try {
            for (String camp : campData) { // Iterate through each line
                String[] campDetails = camp.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // regex to prevent splitting commas in messages
                // Check if required fields are empty
                for (int i=0; i<campDetails.length; i++) {
                    // Attendeess, campComms and withdrawallist can be empty
                    if (i == 10 || i == 11 || i == 13) {
                        continue;
                    }
                    if (campDetails[i].trim().replace("\"", "").isBlank()) {
                        throw new Exception();
                    }
                }
                // Trim to remove whitespace
                String name = campDetails[0].trim();
                String startDate = campDetails[1].trim();
                Date startDateObj = DateUtility.stringToDate(startDate);
                String endDate = campDetails[2].trim();
                Date endDateObj = DateUtility.stringToDate(endDate);
                String regDeadline = campDetails[3].trim();
                Date regDeadlineObj = DateUtility.stringToDate(regDeadline);
                String userGroup = campDetails[4].trim();
                String location = campDetails[5].trim();
                int totalSlots = Integer.parseInt(campDetails[6].trim());
                int campCommSlots = Integer.parseInt(campDetails[7].trim());
                String description = campDetails[8].trim().replace("\"", "");
                String staffIC = campDetails[9].trim();
                String attendeesString = campDetails[10].trim();
                String campCommsString = campDetails[11].trim();

                // Replace 1/0 with true/false for boolean parsing of visibility
                campDetails[12] = campDetails[12].replace("1", "true");
                campDetails[12] = campDetails[12].replace("0", "false");
                boolean visibility = Boolean.parseBoolean(campDetails[12].trim());

                String withdrawalListString = campDetails[13].trim();

                // Make ArrayLists for attendees, campComms and withdrawalList
                ArrayList<String> attendees = new ArrayList<String>();
                ArrayList<String> campComms = new ArrayList<String>();
                ArrayList<String> withdrawalList = new ArrayList<String>();

                // Split attendeesString by ; and add to attendees ArrayList
                String[] attendeesArray = attendeesString.split(";");
                for (String attendee : attendeesArray) {
                    if (!attendee.isBlank()) {
                        attendees.add(attendee.trim());
                    }
                }
                // Split campCommsString by ; and add to campComms ArrayList
                String[] campCommsArray = campCommsString.split(";");
                for (String campComm : campCommsArray) {
                    if (!campComm.isBlank()) {
                        campComms.add(campComm.trim());
                    }
                }
                // Split withdrawalListString by ; and add to withdrawalList ArrayList
                String[] withdrawalListArray = withdrawalListString.split(";");
                for (String withdrawal : withdrawalListArray) {
                    if (!withdrawal.isBlank()) {
                        withdrawalList.add(withdrawal.trim());
                    }
                }
                //System.out.println("Adding camp: " + name + " " + startDate + " " + endDate + " " + regDeadline + " " + userGroup + " " + location + " " + totalSlots + " " + campCommSlots + " " + description + " " + staffIC + " " + attendees + " " + campComms + " " + visibility + " " + withdrawalList);
                this.addItem(new Camp(name, startDateObj, endDateObj, regDeadlineObj, userGroup,
                        location, totalSlots, campCommSlots, description, staffIC, attendees,
                        campComms, visibility, withdrawalList));
            }
        }
        catch (Exception e) {
            System.out.println("Error: Camp CSV file may be missing an entry.");
            System.exit(3);
        }
    }
}
