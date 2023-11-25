import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;

/**
 * Writes attendee list to TXT file.
 * Implements the writeData() method from AttendeeListWriter.
 * @version 1.0
 * @since 2023-11-24
 * @see AttendeeListWriter
 */
public class AttendeeListTXTWriter implements AttendeeListWriter{
    /**
     * Writes the attendee list data to TXT file.
     * @param fileName Name of the TXT file to write to.
     * @param nameAndRoles List of dictionaries containing participant name and role.
     * @param camp Camp object containing camp data.
     * @param filter Filter object containing filter criteria for attendee dictionary.
     */
    public void writeData(String fileName, ArrayList<Dictionary<String, String>> nameAndRoles, Camp camp, Filter filter){
        // Perform filtering if filter is passed in
        if (filter != null){
            nameAndRoles = filter.performFilter(nameAndRoles);
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Write camp data
            bufferedWriter.write("===============================================\n");
            bufferedWriter.write("Camp Name: " + camp.getName() + "\n");
            bufferedWriter.write("===============================================\n");
            bufferedWriter.write("Start Date: " + DateUtility.dateToString(camp.getStartDate()) + "\n");
            bufferedWriter.write("End Date: " + DateUtility.dateToString(camp.getEndDate()) + "\n");
            bufferedWriter.write("Registration Deadline: " + DateUtility.dateToString(camp.getRegDeadline()) + "\n");
            bufferedWriter.write("User Group: " + camp.getUserGroup() + "\n");
            bufferedWriter.write("Location: " + camp.getLocation() + "\n");
            bufferedWriter.write("Total Slots: " + camp.getTotalSlots() + "\n");
            bufferedWriter.write("Camp Comm Slots: " + camp.getCampCommSlots() + "\n");
            bufferedWriter.write("Description: " + camp.getDescription() + "\n");
            bufferedWriter.write("Staff IC: " + camp.getStaffIC() + "\n");
            bufferedWriter.write("Visibility: " + camp.getVisibility() + "\n");
            bufferedWriter.write("Withdrawal List: \n");
            ArrayList<String> withdrawalList = camp.getWithdrawalList();
            // Check and write withdrawal list
            if (withdrawalList.isEmpty()) {
                bufferedWriter.write("\tNo withdrawals\n");
            }
            else {
                for (String withdrawal : withdrawalList) {
                    bufferedWriter.write("\t" + withdrawal + "\n");
                }
            }
            // Write participant list in the filtered array of dictionary
            bufferedWriter.write("Participants: \n");
            for (Dictionary<String, String> nameAndRole : nameAndRoles) {
                String name = nameAndRole.keys().nextElement();
                String role = nameAndRole.get(name);
                bufferedWriter.write("\t" + name + "\t:\t" + role);
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("[ Unable to write to file '" + fileName + "' ]");
        }
    }
}
