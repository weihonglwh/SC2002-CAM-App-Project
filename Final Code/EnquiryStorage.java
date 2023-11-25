import java.util.ArrayList;

/**
 * EnquiryStorage is an implementation of Storage that stores Enquiry objects.
 * It contains methods to print all enquiries, print enquiries by sender, and populate data from a CSV file.
 * @version 1.0
 * @since 2023-11-24
 * @see Storage
 */
public class EnquiryStorage implements Storage {
    /**
     * The list of enquiries.
     */
    private ArrayList<Enquiry> enquiries;

    /**
     * Constructor for EnquiryStorage class.
     */
    public EnquiryStorage() {
        super();
        enquiries = new ArrayList<Enquiry>();
    }

    /**
     * Prints all enquiries for a certain camp.
     * @param campName The camp name for the enquiry.
     */
    public void printAllData(String campName) {
        boolean noEnquiries = true;
        for (Enquiry e : enquiries) {
            if (e.getCampName().equals(campName)) {
                noEnquiries = false;
                System.out.println("[ Enquiry ID "+ e.getEnquiryId()+ " ]");
                System.out.println(" Enquiry Sender: " + e.getSender());
                System.out.println(" Enquiry Message: " + e.getMessage());
                if (e.getResponse() == null) {
                    System.out.println(" Enquiry Response: None");
                } else {
                    System.out.println(" Enquiry Response: " + e.getResponse());
                }
                if (e.getResponder() == null) {
                    System.out.println(" Enquiry responder: None");
                } else {
                    System.out.println(" Enquiry Responder: " + e.getResponder());
                }
                System.out.println();
            }
        }

        if (noEnquiries){
            System.out.println("[ There are no enquiries for this camp ]");
        }
    }

    /**
     * Prints all enquiries for a certain sender.
     * @param sender The name of the sender.
     */
    public void printSenderData(String sender) {
        boolean noEnquiries = true;
        for (Enquiry e : enquiries) {
            if(e.getSender().equals(sender)){
                noEnquiries = false;
                System.out.println("[ Enquiry ID " + e.getEnquiryId()+ " ]");
                System.out.println(" Enquiry Sender: " + e.getSender() + " ]");
                System.out.println(" Enquiry Message: " + e.getMessage()+ " ]");
                if (e.getResponse() == null) {
                    System.out.println(" Enquiry Response: None");
                } else {
                    System.out.println(" Enquiry Response: " + e.getResponse());
                }
                if (e.getResponder() == null) {
                    System.out.println(" Enquiry responder: None");
                } else {
                    System.out.println(" Enquiry Responder: " + e.getResponder());
                }
                System.out.println();
            }
        }

        if (noEnquiries) {
            System.out.println("[ You did not make any enquiry ]");
        }
    }

    /**
     * Gets the list of enquiries.
     * @return The list of enquiries.
     */
    public ArrayList<Enquiry> getData() {
        return enquiries;
    }

    /**
     * Gets the enquiry object with the specified enquiry ID.
     * @param id The enquiry ID of the enquiry.
     */
    public Enquiry getData(String id) {
        int idInt;
        try {
            idInt = Integer.parseInt(id);
        }
        catch (NumberFormatException e) {
            System.out.println("[ Error: Enquiry ID must be an integer. ]");
            return null;
        }
        for (Enquiry e : enquiries) {
            if (e.getEnquiryId() == idInt) {
                return e;
            }
        }
        return null;
    }

    /**
     * Adds an enquiry to the list of enquiries.
     * @param o The enquiry to be added.
     */
    public void addItem(Object o) {
        if (o instanceof Enquiry){
            enquiries.add((Enquiry)o);
        }
        else{
            System.out.println("[ Invalid object type ]");
            System.exit(0);
        }
    }

    /**
     * Deletes an enquiry from the list of enquiries.
     * @param o The enquiry to be deleted.
     */
    public void deleteItem(Object o) {
        if (o instanceof Enquiry){
            enquiries.remove((Enquiry)o);
        }
        else{
            System.out.println("[ Invalid object type ]");
            System.exit(0);
        }
    }

    /**
     * Populates the list of enquiries from a CSV file.
     * @param reader The CSVReader object to read the CSV file.
     */
    public void populateData(CSVReader reader) {
        ArrayList<String> enquiryData = reader.performRead("enquiry.csv");
        try {
            for (String enquiry : enquiryData) { // Iterate through each line
                String[] enquiryDetails = enquiry.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // regex to prevent splitting commas in messages
                // Check if id/sender/message/camp name is empty
                if (enquiryDetails[0].isBlank() || enquiryDetails[1].isBlank()
                        || enquiryDetails[2].trim().replace("\"", "").isBlank()
                        || enquiryDetails[5].trim().replace("\"", "").isBlank()) {
                    throw new Exception();
                }
                // Trim to remove whitespace
                String id = enquiryDetails[0].trim();
                int idInt = Integer.parseInt(id);
                String sender = enquiryDetails[1].trim();
                String message = enquiryDetails[2].trim().replace("\"", ""); // Remove quotes from message
                String response = enquiryDetails[3].trim().replace("\"", ""); // Response can be null initially
                String responder = enquiryDetails[4].trim(); // Responder can be null initially
                String campName = enquiryDetails[5].trim();
                this.addItem(new Enquiry(sender, message, idInt, response, responder, campName));
            }
        }
        catch (Exception e) {
            System.out.println("[ Error: Enquiry CSV file may be missing an entry. ]");
            System.exit(3);
        }
    }
}


