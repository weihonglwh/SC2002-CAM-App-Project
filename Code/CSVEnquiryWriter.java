import java.io.*;
import java.util.ArrayList;

public class CSVEnquiryWriter implements DataWriter {
    public void performWrite(String file, Storage s) {
        if (!(s instanceof EnquiryStorage)) {
            System.out.println("Error: Storage is not a EnquiryStorage.");
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
            ArrayList<Enquiry> enquiryList = s.getData();
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
            System.out.println("Error: Enquiry CSV file could not be written.");
            System.exit(2);
        }
    }
}