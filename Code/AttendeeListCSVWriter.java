import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;

/**
 * Writes attendee list to CSV file.
 * Implements the writeData() method from AttendeeListWriter.
 * @version 1.0
 * @since 2023-11-24
 * @see AttendeeListWriter
 */
public class AttendeeListCSVWriter implements AttendeeListWriter{
    /**
     * Writes the column headers to CSV file.
     * @param fileName Name of the CSV file to write to.
     */
    public void writeHeader(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Camp Name,Start Date,End Date,Registration Deadline,User Group,Location,Total Slots,Camp Comm Slots,Description,Staff IC,Visibility,Withdrawal List, Participant Name, Role");
            bw.newLine();
            bw.close();
            fw.close();
        }
        catch(IOException e){
            System.out.println("[ Error writing header to file. ]");
        }
    }

    /**
     * Writes camp data to CSV file.
     * @param fileName Name of the CSV file to write to.
     * @param nameAndRoles List of dictionaries containing participant name and role.
     * @param camp Camp object containing camp data.
     * @param filter Filter object containing filter criteria for attendee dictionary.
     */
    public void writeData(String fileName, ArrayList<Dictionary<String, String>> nameAndRoles, Camp camp, Filter filter){
        try{
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            // Apply filter
            if (filter != null) {
                nameAndRoles = filter.performFilter(nameAndRoles);
            }
            // Prepare withdrawal list
            StringBuilder withdrawalList = new StringBuilder();
            for (String withdrawal : camp.getWithdrawalList()) {
                withdrawalList.append(withdrawal).append(";");
            }
            // Write camp data
            try {
                String firstParticipant = nameAndRoles.get(0).keys().nextElement();
                String firstRole = nameAndRoles.get(0).get(firstParticipant);
                bw.write(camp.getName() + "," + DateUtility.dateToString(camp.getStartDate()) + "," + DateUtility.dateToString(camp.getEndDate()) + "," + DateUtility.dateToString(camp.getRegDeadline()) + "," +
                    camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getTotalSlots() + "," + camp.getCampCommSlots() + "," +
                    "\"" +camp.getDescription() + "\"" + "," + camp.getStaffIC() + "," + camp.getVisibility() + ","  + withdrawalList + "," + firstParticipant + "," + firstRole);
                bw.newLine();
            } 
            catch (IndexOutOfBoundsException e) {
                bw.write(camp.getName() + "," + DateUtility.dateToString(camp.getStartDate()) + "," + DateUtility.dateToString(camp.getEndDate()) + "," + DateUtility.dateToString(camp.getRegDeadline()) + "," +
                    camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getTotalSlots() + "," + camp.getCampCommSlots() + "," +
                    "\"" + camp.getDescription() + "\"" + "," + camp.getStaffIC() + "," + camp.getVisibility() + ","  + withdrawalList + ",," ); // Write empty participant data
                bw.newLine();
            }
            // Write remaining attendee data
            for (int i=1; i<nameAndRoles.size(); i++) {
                Dictionary<String, String> nameAndRole = nameAndRoles.get(i);
                String participantId = nameAndRole.keys().nextElement();
                String role = nameAndRole.get(participantId);
                bw.write(",,,,,,,,,,,," + participantId + "," + role);
                bw.newLine();
            }
            // Separate camps
            bw.newLine();
            bw.close();
            fw.close();
        }
        catch(IOException e){
            System.out.println("[ Error writing to file. ]");
        }
    }

}
