import java.util.ArrayList;
import java.util.Dictionary;

/**
 * UserAccount is an abstract class that stores UserAccount objects.
 * It contains methods to get name, user ID, password, and faculty.
 * @version 1.0
 * @since 2023-11-24
 */
public abstract class UserAccount {
    /**
     * The name of the user.
     */
    private String name;

    /**
     * The user ID of the user.
     */
    private String userId;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The faculty of the user.
     */
    private String faculty;

    /**
     * Constructor for UserAccount class
     * @param name The name of the user.
     * @param userId The user ID of the user.
     * @param password The password of the user.
     * @param faculty The faculty of the user.
     */
    public UserAccount(String name, String userId, String password, String faculty){
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.faculty = faculty;
    }

    /**
     * To get the name of the user.
     * @return The name of the user.
     */
    public String getName(){
        return name;
    }

    /**
     * To set the name of the user.
     * @param name The name of the user.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * To get the user ID of the user.
     * @return The user ID of the user.
     */
    public String getUserId(){
        return userId;
    }

    /**
     * To get the password of the user.
     * @return The password of the user.
     */
    public String getPassword(){
        return password;
    }

    /**
     * To set the password of the user.
     * @param password The password of the user.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * To get the faculty of the user.
     * @return The faculty of the user.
     */
    public String getFaculty(){
        return faculty;
    }

    /**
     * To generate the attendee list in CSV format.
     * @param filter The filter to be applied.
     * @param campStorage The storage of camps.
     * @param studentStorage The storage of students.
     * @param fileName The name of the CSV file.
     * @param accountType The type of user account.
     */
    public void generateAttendeeListCSV(Filter filter, CampStorage campStorage, StudentStorage studentStorage, String fileName, UserAccount accountType) {
        AttendeeListCSVWriter writer = new AttendeeListCSVWriter();
        if (accountType instanceof StaffAccount) {
            ArrayList<Camp> campsByStaff = ReportPrepper.findCampsByStaff(userId, campStorage);
            writer.writeHeader(fileName);
            for (Camp camp : campsByStaff) {
                ArrayList<Dictionary<String, String>> namesAndRoles = ReportPrepper.buildDictionary(camp, studentStorage);
                writer.writeData(fileName, namesAndRoles, camp, filter);
            }
        }
        else if (accountType instanceof StudentAccount) {
            // Check if student is camp committee
            StudentAccount studentAccount = (StudentAccount) accountType;
            String campCommCamp = studentAccount.getCampCommOf();
            if (campCommCamp.isEmpty()) {
                System.out.println("[ You are not a camp committee of any camp. ]");
                return;
            }
            else {
                writer.writeHeader(fileName);
                Camp camp = campStorage.getData(campCommCamp);
                ArrayList<Dictionary<String, String>> namesAndRoles = ReportPrepper.buildDictionary(camp, studentStorage);
                writer.writeData(fileName, namesAndRoles, camp, filter);
            }
        }
        System.out.println("[ Participant list outputted to " + fileName + " successfully. ]");
    }

    /**
     * To generate the attendee list in TXT format.
     * @param filter The filter to be applied.
     * @param campStorage The storage of camps.
     * @param studentStorage The storage of students.
     * @param fileName The file name to be written to.
     * @param accountType The account type of the user.
     */
    public void generateAttendeeListTXT(Filter filter, CampStorage campStorage, StudentStorage studentStorage, String fileName, UserAccount accountType) {
        AttendeeListTXTWriter writer = new AttendeeListTXTWriter();
        if (FileExistenceChecker.fileExists(fileName)) {
            System.out.println("[ File already exists. Please choose another file name. ]");
            return;
        }
        if (accountType instanceof StaffAccount) {
            ArrayList<Camp> campsByStaff = ReportPrepper.findCampsByStaff(userId, campStorage);
            for (Camp camp : campsByStaff) {
                ArrayList<Dictionary<String, String>> namesAndRoles = ReportPrepper.buildDictionary(camp, studentStorage);
                writer.writeData(fileName, namesAndRoles, camp, filter);
            }
        }
        else if (accountType instanceof StudentAccount) {
            // Check if student is camp committee
            StudentAccount studentAccount = (StudentAccount) accountType;
            String campCommCamp = studentAccount.getCampCommOf();
            if (campCommCamp.isEmpty()) {
                System.out.println("[ You are not a camp committee of any camp. ]");
                return;
            }
            else {
                Camp camp = campStorage.getData(campCommCamp);
                ArrayList<Dictionary<String, String>> namesAndRoles = ReportPrepper.buildDictionary(camp, studentStorage);
                writer.writeData(fileName, namesAndRoles, camp, filter);
            }
        }
        System.out.println("[ Participant list outputted to " + fileName + " successfully. ]");
    }
}
