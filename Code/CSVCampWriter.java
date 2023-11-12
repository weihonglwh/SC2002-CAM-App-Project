import java.io.*;
import java.util.ArrayList;

public class CSVCampWriter implements DataWriter{
    public void performWrite(String file, Storage s) {
        if (!(s instanceof CampStorage)) {
            System.out.println("Error: Storage is not a CampStorage.");
            System.exit(4);
        }
        try {
            String header = CSVReader.getHeader(file);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write the header first
            bw.write(header);
            bw.newLine();
            ArrayList<Camp> campList = s.getData();
            for (Camp camp : campList) {
                // Prepare strings for attendees, campComms, and withdrawalList
                String attendeesString = "";
                String campCommsString = "";
                String withdrawalListString = "";
                for (String attendee : camp.getAttendees()) {
                    attendeesString += attendee + ";";
                }
                for (String campComm : camp.getCampComms()) {
                    campCommsString += campComm + ";";
                }
                for (String withdrawal : camp.getWithdrawalList()) {
                    withdrawalListString += withdrawal + ";";
                }

                // Convert visibility to string
                String visibilityString = camp.getVisibility() ? "1" : "0";

                bw.write(camp.getName() + "," + DateConverter.dateToString(camp.getStartDate()) + "," +
                 DateConverter.dateToString(camp.getEndDate()) + "," + DateConverter.dateToString(camp.getRegDeadline()) + "," 
                 + camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getTotalSlots() + "," + camp.getCampCommSlots() + "," +
                 "\"" + camp.getDescription() + "\"" + "," + camp.getStaffIC() + "," + attendeesString + "," 
                 + campCommsString + "," + visibilityString + "," + withdrawalListString);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error: Camp CSV file could not be written.");
            System.exit(2);
        }
    }
}
