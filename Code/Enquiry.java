public class Enquiry {
    private String sender;
    private String message;
    private String enquiryID;
    private String response;
    private String responder;

    public Enquiry(String sender, String message, String enquiryID) {
        this.sender = sender;
        this.message = message;
        this.enquiryID = enquiryID;
        this.response = null;
        this.responder = null;
    }

    public Enquiry(String sender, String message, String enquiryID, String response, String responder) {
        this.sender = sender;
        this.message = message;
        this.enquiryID = enquiryID;
        this.response = response;
        this.responder = responder;
    }

    public String getEnquiryId(){
        return enquiryID;
    }
    

    public void setEnquiryId(String enquiryID){
        this.enquiryID = enquiryID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse(){
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponder(){
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
    }
}