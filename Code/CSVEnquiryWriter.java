import java.io.*;
import java.util.ArrayList;

/**
 * Class to write all enquiries in the system to a CSV file.
 * @version 1.0
 * @since 2023-11-24
 * @see DataWriter
 */
public class CSVEnquiryWriter implements DataWriter {
    /**
     * Writes all enquiries in the system to a CSV file.
     * Overwrites existing data in the CSV file.
     * @param fileName Name of the file to be written to.
     *                 Should be "enquiry.csv" by default.
     * @param storage Storage that contains the enquiries data to be written to the CSV file.
     *                Should be a EnquiryStorage object.
     */
    public void performWrite(String fileName, Storage storage) {
        // Check if storage is a EnquiryStorage object
        if (!(storage instanceof EnquiryStorage)) {
            System.out.println("[ Error: Storage is not a EnquiryStorage. ]");
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
            // Write the data
            ArrayList<Enquiry> enquiryList = storage.getData();
            for (Enquiry enquiry : enquiryList) {
                if (enquiry.getResponse() == null || enquiry.getResponder() == null) {
                    enquiry.setResponse("");
                    enquiry.setResponder("");
                }
                bw.write(enquiry.getEnquiryId() + "," + enquiry.getSender() + "," +
                 "\""+enquiry.getMessage()+"\"" + "," + "\""+enquiry.getResponse()+"\"" + "," + enquiry.getResponder() 
                 + "," + enquiry.getCampName());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("[ Error: Enquiry CSV file could not be written.]");
            System.exit(2);
        }
    }
}