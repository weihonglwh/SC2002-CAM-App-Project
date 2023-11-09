import java.util.ArrayList;

public class EnquiryStorage extends Storage {
    private ArrayList<Enquiry> enquiries;
    
    public EnquiryStorage() {
        super();
        enquiries = new ArrayList<Enquiry>();
    }

    public void printData() {
        for (Enquiry e : enquiries) {
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
    
    public ArrayList getData() {
        return enquiries;
    }
    
    public Object getData(String s) {
        for (Enquiry e : enquiries) {
            if (e.getEnquiryId().equals(s)) {
                return e;
            }
        }
        return null;
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
}
