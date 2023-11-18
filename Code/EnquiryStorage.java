import java.util.ArrayList;

public class EnquiryStorage extends Storage {
    private ArrayList<Enquiry> enquiries;
    
    public EnquiryStorage() {
        super();
        enquiries = new ArrayList<Enquiry>();
    }

    public void printAllData(String campName) {
        boolean noEquiries = true;
        for (Enquiry e : enquiries) {
            if (e.getCampName().equals(campName)) {
                noEquiries = false;
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

        if (noEquiries){
            System.out.println("There are no enquiries for this camp");
        }
    }

    public void printSenderData(String sender) {
        boolean noEquiries = true;
        for (Enquiry e : enquiries) {
            if(e.getSender().equals(sender)){
                noEquiries = false;
                System.out.println("The sender for this enquiry is " + e.getSender());
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
                System.out.println();
            }
        }

        if (noEquiries) {
            System.out.println("You did not make any enquiry");
        }
    }
    
    public ArrayList getData() {
        return enquiries;
    }

    public Enquiry getData(String id) {
        for (Enquiry e : enquiries) {
            if (e.getEnquiryId() == Integer.parseInt(id)) {
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


