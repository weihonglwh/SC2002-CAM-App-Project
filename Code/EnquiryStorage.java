import java.util.ArrayList;

public class EnquiryStorage extends Storage {
    private ArrayList<Enquiry> enquiries;
    
    public EnquiryStorage() {
        super();
        enquiries = new ArrayList<Enquiry>();
    }

    public void printAllData(String campName) {
        for (Enquiry e : enquiries) {
            System.out.println("the sender for this enquiry is " + "\n" + e.getSender());
            System.out.println("The enquiry id for this enquiry is " + "\n" + e.getEnquiryId());
            System.out.println("The enquiry message for this enquiry is " + "\n" + e.getMessage());
            if (e.getResponse() == null) {
                System.out.println("There is no response for this enquiry");
            } else {
                System.out.println("The response for this enquiry is " + "\n" + e.getResponse());
            }
            if (e.getResponder() == null) {
                System.out.println("There is no responder for this enquiry");
            } else {
                System.out.println("The responder for this enquiry is " + "\n" + e.getResponder());
            }
            System.out.println("");
        }
    }

    public void printSenderData(String sender) {
        for (Enquiry e : enquiries) {
            if(e.getSender().equals(sender)){
                System.out.println("the sender for this enquiry is " + e.getSender());
                System.out.println("The enquiry id for this enquiry is " + e.getEnquiryId());
                System.out.println("The enquiry message for this enquiry is " + e.getMessage());
                if (e.getResponse() == null) {
                    System.out.println("There is no response for this enquiry");
                } else {
                    System.out.println("The response for this enquiry is " + e.getResponse());
                }
                if (e.getResponder() == null) {
                    System.out.println("There is no responder for this enquiry");
                } else {
                    System.out.println("The responder for this enquiry is " + e.getResponder());
                }
                System.out.println("");
            }
        }
    }
    
    public ArrayList getData() {
        return enquiries;
    }

    public Enquiry getData( String Id) {
        for (Enquiry e : enquiries) {
            if (e.getEnquiryId().equals(Id)) {
                return e;
            }
        }
        return null;
    }


    public int generateID() {
        int i=0;
        for (Enquiry e : enquiries) {
            i++;
            }
        i++;
        return i;
    }
    
    public void addItem(Object o) {
        if (o instanceof Enquiry){
            enquiries.add((Enquiry)o);
        }
        else{
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }

    public void deleteItem(Object o) {
        if (o instanceof Enquiry){
            enquiries.remove((Enquiry)o);
        }
        else{
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }
}


