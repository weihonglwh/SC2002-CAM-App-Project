import java.io.*;
import java.util.ArrayList;

public class CSVStaffWriter implements DataWriter{
    public void performWrite(String file, Storage s) {
        if (!(s instanceof StaffStorage)) {
            System.out.println("Error: Storage is not a StaffStorage.");
            System.exit(4);
        }
        try {
            String header = CSVReader.getHeader(file);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write the header first
            bw.write(header);
            bw.newLine();
            // Write the data
            ArrayList<StaffAccount> staffList = s.getData();
            for (StaffAccount staff : staffList) {
                bw.write(staff.getName() + "," + staff.getUserId()+"@NTU.EDU.SG" + "," +
                 staff.getFaculty() + "," + staff.getPassword());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error: Staff CSV file could not be written.");
            System.exit(2);
        }
    }
}
