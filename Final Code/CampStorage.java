import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * CampStorage is an implementation of Storage that stores all the camps in the system.
 * It contains methods to print all camps, print camps created by a specific staff,
 * print camps specific to student's faculty, get all camps, get a specific camp,
 * add a camp, remove a camp, sort camps, and populate data from a CSV file.
 * @version 1.0
 * @since 2023-11-24
 * @see Storage
 */
public class CampStorage implements Storage{
    /**
     * An ArrayList of Camp objects.
     */
    private ArrayList<Camp> camps;

    /**
     * Default constructor for CampStorage.
     */
    public CampStorage() {
        super();
        camps = new ArrayList<Camp>();
    }

    /**
     * Prints all camps in the system.
     * @param searchFilter SearchFilter object that filters camps according to user filter input.
     * @param filterObject An object that the user wants to filter by.
     *                     This object is used by the SearchFilter object to filter the camps.
     */
    public void printData(SearchFilter searchFilter, Object filterObject) {
        // Make a copy of the camp array to prevent modifying the original array
        ArrayList<Camp> filteredCamps = new ArrayList<Camp>(camps);
        if (searchFilter != null) {
            filteredCamps = searchFilter.performFilter(filteredCamps, filterObject);
        }
        // No camps found with filter
        if (filteredCamps.isEmpty()) {
            System.out.println("[ No camps found with your filter. ]");
            return;
        }
        // Print all camps found with filter or no filter
        for (Camp c : filteredCamps) {
            UiPrinter.printCampInformation(c);
        }
    }

    /**
     * Prints camps created by a specific staff.
     * @param staff StaffAccount object.
     */
    // Print camps created by a specific staff
    public void printData(StaffAccount staff) {
        for (Camp campStaff: camps){
            if (campStaff.getStaffIC().equals(staff.getUserId())){
                UiPrinter.printCampInformation(campStaff);
            }
        }
    }

    /**
     * Prints camps specific to student's faculty or the whole of NTU.
     * @param student StudentAccount object.
     * @param searchFilter SearchFilter object that filters camps according to user filter input.
     * @param filterObject An object that the user wants to filter by.
     *                     This object is used by the SearchFilter object to filter the camps.
     */
    public void printData(StudentAccount student, SearchFilter searchFilter, Object filterObject) {
        // Make a copy of the camp array to prevent modifying the original array
        ArrayList<Camp> filteredCamps = new ArrayList<Camp>(camps);
        if (searchFilter != null) {
            filteredCamps = searchFilter.performFilter(filteredCamps, filterObject);
        }
        // No camps found with filter
        if (filteredCamps.isEmpty()) {
            System.out.println("[ No camps found with your filter. ]");
            return;
        }
        // Print all camps found with filter or no filter
        for (Camp c : filteredCamps) {
            if((c.getUserGroup().equals(student.getFaculty()) || c.getUserGroup().equals("NTU"))
                    && c.getVisibility()) {
                UiPrinter.printCampInfoForStudents(c);
            }
        }
    }

    /**
     * Gets all camps in the system.
     * @return ArrayList of Camp objects.
     */
    public ArrayList<Camp> getData() {
        return camps;
    }

    /**
     * Gets a specific camp using the camp name.
     * @param s Camp name.
     * @return Camp object.
     */
    public Camp getData(String s) {
        for (Camp c : camps) {
            if (c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Adds a camp to the system.
     * @param o Camp object.
     */
    public void addItem(Object o) {
        if (o instanceof Camp){
            camps.add((Camp)o);
            sortCamps();
        }
        else{
            System.out.println("[ Invalid object type ]");
            System.exit(0);
        }
    }

    /**
     * Removes a camp from the system.
     * @param o Camp object.
     */
    public void removeItem(Object o) {
        if (o instanceof Camp){
            camps.remove((Camp)o);
        }
        else{
            System.out.println("[ Invalid object type ]");
            System.exit(0);
        }
    }

    /**
     * Sorts camps by name.
     */
    public void sortCamps() {
        Collections.sort(camps);
    }

    /**
     * Populates camps from CSV file.
     * @param reader CSVReader object.
     */
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
                this.addItem(new Camp(name, startDateObj, endDateObj, regDeadlineObj, userGroup,
                        location, totalSlots, campCommSlots, description, staffIC, attendees,
                        campComms, visibility, withdrawalList));
            }
        }
        catch (Exception e) {
            System.out.println("[ Error: Camp CSV file may be missing an entry or contains invalid entries. ]");
            System.exit(3);
        }
    }
}
