import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class AttendeeListCSVWriter extends AttendeeListWriter{
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
            System.out.println("Error writing header to file.");
        }
    }

    public void writeData(String fileName, ArrayList<Dictionary<String, String>> nameAndRoles, Camp c, Filter filter){
        try{
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            // Apply filter
            if (filter != null) {
                nameAndRoles = filter.performFilter(nameAndRoles);
            }
            // Prepare withdrawal list
            StringBuilder withdrawalList = new StringBuilder();
            for (String withdrawal : c.getWithdrawalList()) {
                withdrawalList.append(withdrawal).append(";");
            }
            // Write camp data
            try {
                String firstParticipant = nameAndRoles.get(0).keys().nextElement();
                String firstRole = nameAndRoles.get(0).get(firstParticipant);
                bw.write(c.getName() + "," + DateUtility.dateToString(c.getStartDate()) + "," + DateUtility.dateToString(c.getEndDate()) + "," + DateUtility.dateToString(c.getRegDeadline()) + "," +
                    c.getUserGroup() + "," + c.getLocation() + "," + c.getTotalSlots() + "," + c.getCampCommSlots() + "," +
                    "\"" +c.getDescription() + "\"" + "," + c.getStaffIC() + "," + c.getVisibility() + ","  + withdrawalList + "," + firstParticipant + "," + firstRole);
                bw.newLine();
            } 
            catch (IndexOutOfBoundsException e) {
                bw.write(c.getName() + "," + DateUtility.dateToString(c.getStartDate()) + "," + DateUtility.dateToString(c.getEndDate()) + "," + DateUtility.dateToString(c.getRegDeadline()) + "," +
                    c.getUserGroup() + "," + c.getLocation() + "," + c.getTotalSlots() + "," + c.getCampCommSlots() + "," +
                    "\"" + c.getDescription() + "\"" + "," + c.getStaffIC() + "," + c.getVisibility() + ","  + withdrawalList + ",," ); // Write empty participant data
                bw.newLine();
            }
            // Write remaining participant data
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
            System.out.println("Error writing to file.");
        }
    }

}
