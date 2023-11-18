import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;

/*
    Columns:
    1. Camp Name
    2. Start Date
    3. End Date
    4. Registration Deadline
    5. User Group
    6. Location
    7. Total Slots
    8. Camp Comm Slots
    9. Description
    10. Staff IC
    11. Visibility
    12. Withdrawal List
    13. Attendees
    14. Camp Comms
 */

public class AttendeeListCSVWriter {
    public static void performWrite(String file, Camp camp, ArrayList<Dictionary> namesAndRoles){
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write header
            bw.write("Camp Name, Start Date, End Date, Registration Deadline, User Group, Location, Total Slots, Camp Comm Slots, " +
                    "Description, Staff IC, Visibility, Withdrawal List, Attendees, Camp Comms");
            bw.newLine();
            // Prep withdrawal list as string
            StringBuilder withdrawalList = new StringBuilder();
            for (String student : camp.getWithdrawalList()) {
                withdrawalList.append(student).append(";");
            }
            // Write camp details
            bw.write(camp.getName() + ", " + DateUtility.dateToString(camp.getStartDate()) + ", " + DateUtility.dateToString(camp.getEndDate()) + ", " +
                    DateUtility.dateToString(camp.getRegDeadline()) + ", " + camp.getUserGroup() + ", " + camp.getLocation() + ", " +
                    camp.getTotalSlots() + ", " + camp.getCampCommSlots() + ", " + camp.getDescription() + ", " + camp.getStaffIC() + ", " +
                    camp.getVisibility() + ", " + withdrawalList + ", ");
            // Track first camp comm member of the camp
            int index=0;
            // Write attendees
            for (Dictionary nameAndRole : namesAndRoles) {
                index = namesAndRoles.indexOf(nameAndRole);
                // Get the name from the dictionary key
                String name = (String) nameAndRole.keys().nextElement();
                // Check if current student is an attendee
                if (!nameAndRole.get(name).equals("Attendee")) {
                    break;
                }
                else {
                    bw.write(name + ";");
                }
            }
            for (int i=index; i<namesAndRoles.size(); i++) {
                Dictionary nameAndRole = namesAndRoles.get(i);
                // Get the name from the dictionary key
                String name = (String) nameAndRole.keys().nextElement();
                bw.write(name + ";");
            }
            bw.close();
            fw.close();
        }
        catch(IOException e){
            System.out.println("Error writing to file");
        }
    }
}
