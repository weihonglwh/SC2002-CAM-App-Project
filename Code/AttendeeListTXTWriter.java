import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;

//bw.write("Camp Name,Start Date,End Date,Registration Deadline,User Group,Location,Total Slots," +
  //      "Camp Comm Slots,Description,Staff IC,Visibility,Withdrawal List, Participant Name, " +
    //    "Role");

public class AttendeeListTXTWriter implements AttendeeListWriter{
    public boolean fileExists(String fileName){
        File f = new File(fileName);
        return f.exists() && !f.isDirectory();
    }

    public void writeData(String fileName, ArrayList<Dictionary<String, String>> nameAndRoles, Camp c, Filter filter){
        if (filter != null){
            nameAndRoles = filter.performFilter(nameAndRoles);
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Write camp data
            bufferedWriter.write("===============================================\n");
            bufferedWriter.write("Camp Name: " + c.getName() + "\n");
            bufferedWriter.write("===============================================\n");
            bufferedWriter.write("Start Date: " + DateUtility.dateToString(c.getStartDate()) + "\n");
            bufferedWriter.write("End Date: " + DateUtility.dateToString(c.getEndDate()) + "\n");
            bufferedWriter.write("Registration Deadline: " + DateUtility.dateToString(c.getRegDeadline()) + "\n");
            bufferedWriter.write("User Group: " + c.getUserGroup() + "\n");
            bufferedWriter.write("Location: " + c.getLocation() + "\n");
            bufferedWriter.write("Total Slots: " + c.getTotalSlots() + "\n");
            bufferedWriter.write("Camp Comm Slots: " + c.getCampCommSlots() + "\n");
            bufferedWriter.write("Description: " + c.getDescription() + "\n");
            bufferedWriter.write("Staff IC: " + c.getStaffIC() + "\n");
            bufferedWriter.write("Visibility: " + c.getVisibility() + "\n");
            bufferedWriter.write("Withdrawal List: \n");
            for (String withdrawal : c.getWithdrawalList()) {
                bufferedWriter.write("\t" + withdrawal + "\n");
            }
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
            System.out.println("Unable to write to file '" + fileName + "'");
        }
    }
}
