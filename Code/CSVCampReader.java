import java.util.ArrayList;
import java.util.Date;

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

public class CSVCampReader extends CSVReader{
    public void populateStorage(Storage s) {
        if (!(s instanceof CampStorage)) {
            System.out.println("Error: Storage is not a CampStorage.");
            System.exit(4);
        }
        ArrayList<String> campData = performRead("camp.csv");
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
                Date startDateObj = DateConverter.stringToDate(startDate);
                String endDate = campDetails[2].trim();
                Date endDateObj = DateConverter.stringToDate(endDate);
                String regDeadline = campDetails[3].trim();
                Date regDeadlineObj = DateConverter.stringToDate(regDeadline);
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
                    attendees.add(attendee.trim());
                }
                // Split campCommsString by ; and add to campComms ArrayList
                String[] campCommsArray = campCommsString.split(";");
                for (String campComm : campCommsArray) {
                    campComms.add(campComm.trim());
                }
                // Split withdrawalListString by ; and add to withdrawalList ArrayList
                String[] withdrawalListArray = withdrawalListString.split(";");
                for (String withdrawal : withdrawalListArray) {
                    withdrawalList.add(withdrawal.trim());
                }
                //System.out.println("Adding camp: " + name + " " + startDate + " " + endDate + " " + regDeadline + " " + userGroup + " " + location + " " + totalSlots + " " + campCommSlots + " " + description + " " + staffIC + " " + attendees + " " + campComms + " " + visibility + " " + withdrawalList);
                s.addItem(new Camp(name, startDateObj, endDateObj, regDeadlineObj, userGroup, 
                                    location, totalSlots, campCommSlots, description, staffIC, attendees, 
                                    campComms, visibility, withdrawalList));
            }
        }
        catch (Exception e) {
            System.out.println("Error: Camp CSV file may be missing an entry.");
            System.exit(3);
        }
    }

    // public static void main(String[] args) {
    //     CSVCampReader reader = new CSVCampReader();
    //     CampStorage storage = new CampStorage();
    //     reader.populateStorage(storage);
    //     ArrayList<Camp> camps = storage.getData();
    //     for (Camp c : camps) {
    //         System.out.println(c.getName() + " " + c.getStartDate() + " " + c.getEndDate() + " " + c.getRegDeadline() + " " + c.getUserGroup() + " " + c.getLocation() + " " + c.getTotalSlots() + " " + c.getCampCommSlots() + " " + c.getDescription() + " " + c.getStaffIC() + " " + c.getAttendees() + " " + c.getCampComms() + " " + c.getVisibility() + " " + c.getWithdrawalList());
    //         System.out.println();
    //     }
    // }
}
