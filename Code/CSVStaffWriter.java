import java.io.*;
import java.util.ArrayList;

/**
 * Class to write all staffs in the system to a CSV file
 * @version 1.0
 * @since 2023-11-24
 * @see DataWriter
 */
public class CSVStaffWriter implements DataWriter{
    /**
     * Writes all staffs in the system to a CSV file.
     * Overwrites existing data in the CSV file.
     * @param filename Name of the file to be written to.
     *                 Should be "staff.csv" by default.
     * @param storage Storage that contains the staff data to be written to the CSV file.
     *                Should be a StaffStorage object.
     */
    public void performWrite(String filename, Storage storage) {
        // Check if storage is a StaffStorage object
        if (!(storage instanceof StaffStorage)) {
            System.out.println("[ Error: Storage is not a StaffStorage. ]");
            System.exit(4);
        }
        try {
            String header = CSVReader.getHeader(filename);
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write the header first
            if (!header.isBlank()) {
                bw.write(header);
                bw.newLine();
            }
            // Write the data
            ArrayList<StaffAccount> staffList = storage.getData();
            for (StaffAccount staff : staffList) {
                bw.write(staff.getName() + "," + staff.getUserId()+"@NTU.EDU.SG" + "," +
                 staff.getFaculty() + "," + staff.getPassword());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("[ Error: Staff CSV file could not be written. ]");
            System.exit(2);
        }
    }
}
