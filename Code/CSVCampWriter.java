import java.io.*;
import java.util.ArrayList;

/**
 * Class to write all camps in the system to a CSV file.
 * @version 1.0
 * @since 2023-11-24
 * @see DataWriter
 */
public class CSVCampWriter implements DataWriter{
    /**
     * Writes all camps in the system to a CSV file.
     * Overwrites existing data in the CSV file.
     * @param fileName Name of the file to be written to.
     *                 Should be "camp.csv" by default.
     * @param storage Storage that contains the camps data to be written to the CSV file.
     *                Should be a CampStorage object.
     */
    public void performWrite(String fileName, Storage storage) {
        // Check if storage is a CampStorage object
        if (!(storage instanceof CampStorage)) {
            System.out.println("[ Error: Storage is not a CampStorage. ]");
            System.exit(4);
        }
        try {
            String header = CSVReader.getHeader(fileName);
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write the header first
            if (!header.isBlank()) {
                bw.write(header);
                bw.newLine();
            }
            ArrayList<Camp> campList = storage.getData();
            for (Camp camp : campList) {
                // Prepare strings for attendees, campComms, and withdrawalList
                StringBuilder attendeesString = new StringBuilder();
                StringBuilder campCommsString = new StringBuilder();
                StringBuilder withdrawalListString = new StringBuilder();
                for (String attendee : camp.getAttendees()) {
                    attendeesString.append(attendee).append(";");
                }
                for (String campComm : camp.getCampCommitteeMembers()) {
                    campCommsString.append(campComm).append(";");
                }
                for (String withdrawal : camp.getWithdrawalList()) {
                    withdrawalListString.append(withdrawal).append(";");
                }

                // Convert visibility to string
                String visibilityString = camp.getVisibility() ? "1" : "0";

                bw.write(camp.getName() + "," + DateUtility.dateToString(camp.getStartDate()) + "," +
                 DateUtility.dateToString(camp.getEndDate()) + "," + DateUtility.dateToString(camp.getRegDeadline()) + ","
                 + camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getTotalSlots() + "," + camp.getCampCommSlots() + "," +
                 "\"" + camp.getDescription() + "\"" + "," + camp.getStaffIC() + "," + attendeesString + "," 
                 + campCommsString + "," + visibilityString + "," + withdrawalListString);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("[ Error: Camp CSV file could not be written. ]");
            System.exit(2);
        }
    }
}
