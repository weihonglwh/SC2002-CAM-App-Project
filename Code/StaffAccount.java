import java.util.ArrayList;
import java.util.Date;

/**
 * StaffAccount is a subclass of UserAccount that stores StaffAccount objects.
 * It contains methods to create camps, process suggestions, and edit camps.
 * @version 1.0
 * @since 2023-11-24
 * @see UserAccount
 */
public class StaffAccount extends UserAccount {

    /**
     * Constructor for StaffAccount class
     * @param name The name of the staff.
     * @param userId The user ID of the staff.
     * @param password The password of the staff.
     * @param faculty The faculty of the staff.
     */
    public StaffAccount(String name, String userId, String password, String faculty){
        super(name, userId, password, faculty);
    }

    /**
     * To get the userID of the staff.
     * @return The userID of the staff.
     */
    public String getUsername(){
        return super.getUserId();
    }

    /**
     * For the staff to create a camp
     * @param campName The camp name.
     * @param startDate The start date of the camp.
     * @param endDate The end date of the camp.
     * @param regDeadLine The registration deadline of the camp.
     * @param userGroup The user group of the camp.
     * @param location The location of the camp.
     * @param totalSlots The total slots of the camp.
     * @param campCommSlots The camp committee slots of the camp.
     * @param description   The description of the camp.
     * @param visibility   The visibility of the camp.
     * @param campStorage  The camp storage.
     */
    public void createCamp(String campName, Date startDate, Date endDate, Date regDeadLine, String userGroup, String location, int totalSlots, int campCommSlots, String description, boolean visibility, CampStorage campStorage){
        Camp newCamp = new Camp(campName, startDate, endDate, regDeadLine, userGroup, location, totalSlots, campCommSlots, description, getUserId(), visibility);
        System.out.println("[ Camp created successfully ]");
        campStorage.addItem(newCamp);
        System.out.println("[ Camp added to storage ]");
    }

    /**
     * For the staff to process a suggestion.
     * @param tarSuggestion The suggestion to be processed.
     * @param studentStorage The student storage.
     * @param approval The choice of the staff(accept or reject).
     */
    public void processSuggestion(Suggestion tarSuggestion, StudentStorage studentStorage, boolean approval){
        tarSuggestion.setApproval(approval);
        tarSuggestion.setProcessed(true);
        if (approval) {
            System.out.println("[ Suggestion Accepted ]");
            String studentName = tarSuggestion.getSuggester();
            StudentAccount studentNameObj = studentStorage.getData(studentName);
            studentNameObj.addPoints();
        }
        else {
            System.out.println("[ Suggestion Rejected ]");
        }
    }

    /**
     * For the staff to edit a camp.
     * @param campToBeEdited The camp to be edited.
     * @param editOperation The edit operation for staff.
     */
    public void editCamp(Camp campToBeEdited, EditCampOperation editOperation) {
        if (campToBeEdited == null || editOperation == null) {
            System.out.println("[ Invalid arguments ]");
            return;
        }
        campToBeEdited.editCamp(editOperation);
    }

    /**
     * For the staff to generate a performance report.
     * @param campStorage The camp storage.
     * @param studentStorage The student storage.
     * @param fileName The file name of the performance report.
     * @param writer The writer for the performance report.
     */
    public void generatePerformanceReport(CampStorage campStorage, StudentStorage studentStorage, String fileName, PerformanceReportWriter writer) {
        if (FileExistenceChecker.fileExists(fileName)) {
            System.out.println("[ File already exists. Please choose another file name. ]");
            return;
        }
        ArrayList<Camp> campsByStaff = ReportPrepper.findCampsByStaff(getUserId(), campStorage);
        for (Camp camp : campsByStaff) {
            writer.writeData(fileName, camp, studentStorage);
        }
        System.out.println("[ Performance report outputted to " + fileName + " successfully. ]");
    }
}
