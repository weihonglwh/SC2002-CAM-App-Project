import java.util.ArrayList;

/*
    Col 0: ID
    Col 1: Sender
    Col 2: Message
    Col 3: Response
    Col 4: Responder
*/

public class CSVEnquiryReader extends CSVReader{
    public void populateStorage(Storage s) {
        if (!(s instanceof EnquiryStorage)) {
            System.out.println("Error: Storage is not a EnquiryStorage.");
            System.exit(4);
        }
        ArrayList<String> enquiryData = performRead("enquiry.csv");
        try {
            for (String enquiry : enquiryData) { // Iterate through each line
                String[] enquiryDetails = enquiry.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // regex to prevent splitting commas in messages
                // Check if id/sender/message is empty
                if (enquiryDetails[0].isBlank() || enquiryDetails[1].isBlank() || enquiryDetails[2].trim().replace("\"", "").isBlank()) {
                    throw new Exception();
                }
                // Trim to remove whitespace
                String id = enquiryDetails[0].trim();
                String sender = enquiryDetails[1].trim();
                String message = enquiryDetails[2].trim().replace("\"", ""); // Remove quotes from message
                String response = enquiryDetails[3].trim().replace("\"", ""); // Response can be null initially
                String responder = enquiryDetails[4].trim(); // Responder can be null initially
                System.out.println("Adding enquiry: " + id + " " + sender + " " + message + " " + response + " " + responder);
                s.addItem(new Enquiry(sender, message, id, response, responder));
            }
        }
        catch (Exception e) {
            System.out.println("Error: Enquiry CSV file may be missing an entry.");
            System.exit(3);
        }
    }

    // public static void main(String[] args) {
    //     CSVEnquiryReader reader = new CSVEnquiryReader();
    //     EnquiryStorage storage = new EnquiryStorage();
    //     reader.populateStorage(storage);
    //     ArrayList<Enquiry> enquiries = storage.getData();
    //     for (Enquiry e : enquiries) {
    //         System.out.println(e.getEnquiryId() + " " + e.getSender() + " " + e.getMessage() + " " + e.getResponse() + " " + e.getResponder());
    //         System.out.println();
    //     }
    // }
}
