/**
 * Enquiry class.
 * This class is used to create an enquiry object.
 * It contains the sender, message, enquiryID, response, responder and campName.
 * It contains getters and setters for all the attributes.
 * It contains a constructor to create an enquiry object.
 * @version 1.0
 * @since 2023-11-24
 */
public class Enquiry {
    /**
     * The userID of the sender.
     */
    private String sender;

    /**
     * The message of the enquiry.
     */
    private String message;

    /**
     * The enquiry ID of the enquiry.
     */
    private int enquiryID;

    /**
     * The response of the enquiry.
     */
    private String response;

    /**
     * The userID of the responder.
     */
    private String responder;

    /**
     * The camp name of the enquiry.
     */
    private String campName;

    /**
     * Constructor for Enquiry class.
     * @param sender The userID of the sender.
     * @param message The message of the enquiry.
     * @param campName The camp name of the enquiry.
     * @param enquiryID The enquiry ID of the enquiry.
     */
    public Enquiry(String sender, String message, String campName, int enquiryID) {
        this.sender = sender;
        this.message = message;
        this.enquiryID = enquiryID;
        this.response = null;
        this.responder = null;
        this.campName = campName;
    }

    /**
     * Constructor for Enquiry class.
     * @param sender The userID of the sender.
     * @param message The message of the enquiry.
     * @param enquiryID The enquiry ID of the enquiry.
     * @param response The response of the enquiry.
     * @param responder The userID of the responder.
     * @param campName The camp name of the enquiry
     */
    public Enquiry(String sender, String message, int enquiryID, String response, String responder, String campName) {
        this.sender = sender;
        this.message = message;
        this.enquiryID = enquiryID;
        this.response = response;
        this.responder = responder;
        this.campName = campName;
    }

    /**
     * Getter for enquiryID.
     * @return The ID of the enquiry.
     */
    public int getEnquiryId(){
        return enquiryID;
    }

    /**
     * Getter for sender.
     * @return The userID of the sender.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Getter for message.
     * @return The enquiry text content.
     */
    public String getMessage(){
        return message;
    }

    /**
     * Setter for message.
     * @param message The enquiry text content.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter for response.
     * @return The response to the enquiry made.
     */
    public String getResponse(){
        return response;
    }

    /**
     * Setter for response.
     * @param response the response to the enquiry made.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Getter for responder.
     * @return The userID of the person replying to the enquiry.
     */
    public String getResponder(){
        return responder;
    }

    /**
     * Setter for responder.
     * @param responder The userID of the person replying to the enquiry.
     */
    public void setResponder(String responder) {
        this.responder = responder;
    }

    /**
     * Getter for campName.
     * @return The name of the camp.
     */
    public String getCampName() { return campName; }

}
