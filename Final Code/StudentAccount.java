import java.util.ArrayList;

/**
 * StudentAccount is a subclass of UserAccount that stores StudentAccount objects.
 * It contains methods to get points, add points, get camp committee, set camp committee, get camps registered, add camps, get camps, remove camps, and print profile.
 * @version 1.0
 * @since 2023-11-24
 * @see UserAccount
 */

public class StudentAccount extends UserAccount {
    /**
     * The list of camps that student registered.
     */
    private ArrayList<String> campsRegistered;

    /**
     * The name of the camp that student is the camp committee of.
     */
    private String campCommOf;

    /**
     * The number of points the student has.
     */
    private int points;

    /**
     * Constructor for StudentAccount class
     * @param name The name of the student.
     * @param userId The user ID of the student.
     * @param password The password of the student.
     * @param faculty The faculty of the student.
     * @param points The number of points the student has.
     * @param campCommOf The name of the camp that student is the camp committee of.
     * @param registered Camp registered by the student.
     */
    public StudentAccount(String name, String userId, String password, String faculty, int points, String campCommOf, ArrayList<String> registered) {
        super(name, userId, password, faculty);
        this.campsRegistered = registered;
        this.points = points;
        this.campCommOf = campCommOf;
    }

    /**
     * To get the points of the student.
     * @return The points of the student.
     */
    public int getPoints(){
        return points;
    }

    /**
     * To add points to the student.
     */
    public void addPoints(){
        this.points++;
    }

    /**
     * To get the camp committee of the student.
     * @return The camp committee of the student.
     */
    public String getCampCommOf() {
        return campCommOf;
    }

    /**
     * To set the camp committee of the student.
     * @param camp The name of the camp that student is the camp committee of.
     */
    public void setCampCommOf(String camp) {
        campCommOf = camp;
    }

    /**
     * To get the camps registered by the student.
     * @return The list of camps registered by the student.
     */
    public ArrayList<String> getCampsRegistered() {
        return campsRegistered;
    }

    /**
     * To add camps to the student.
     * @param campName The name of the camp that student registered.
     */
    public void addCamps(String campName){
        campsRegistered.add(campName);
    }

    /**
     * To remove camps from the student
     * @param campName The name of the camp that student registered.
     * @return True if the camp is removed successfully, false if the camp is not removed successfully.
     */
    public boolean removeCamps(String campName)
    {
            int index = campsRegistered.indexOf(campName);
            if (index >= 0){
                campsRegistered.remove(index);
                return true;
            }
            return false;
    }

    /**
     * To print the profile of the student.
     */
    public void printProfile(){
        UiPrinter.printStudentProfile(this);
    }

    /**
     * Creates a new enquiry object.
     * @param enquiryMessage The message of the enquiry.
     * @param enquiryCamp The camp name of the enquiry.
     * @param enquiryID The enquiry ID of the enquiry.
     * @return The enquiry object.
     */
    public Enquiry createEnquiry(String enquiryMessage, String enquiryCamp, int enquiryID) {
        return new Enquiry(this.getUserId(), enquiryMessage, enquiryCamp, enquiryID);
    }

    /**
     * Creates a new suggestion object.
     * @param suggestion The suggestion proposed by the suggester.
     * @param suggestionID The suggestion ID of the suggestion.
     * @return The suggestion object.
     */
    public Suggestion createSuggestion(String suggestion, int suggestionID) {
        if (campCommOf.isEmpty()) {
            System.out.println("[ You are not a camp committee of any camp. ]");
        }
        return new Suggestion(getUserId(), suggestion, suggestionID, campCommOf);
    }

    /**
     * Method to edit a suggestion
     * @param suggestionObject The suggestion object.
     * @param suggestion The new suggestion message.
     */
    public void editSuggestion(Suggestion suggestionObject, String suggestion){
        suggestionObject.setSuggestion(suggestion);
    }

    /**
     * Method to edit an enquiry
     * @param enquiryObject The enquiry object.
     * @param enquiryMessage The new enquiry message.
     */
    public void editEnquiry(Enquiry enquiryObject, String enquiryMessage){
        enquiryObject.setMessage(enquiryMessage);
    }
}
