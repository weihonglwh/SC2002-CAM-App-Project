import java.util.ArrayList;
import java.util.Date;

public class StaffAccount extends UserAccount {
    
    public StaffAccount(String name, String userId, String password, String faculty){
        super(name, userId, password, faculty);
    }

    public String getUsername(){
        return super.getUserId();
    }

    public void createCamp(String campName, Date startDate, Date endDate, Date regDeadLine, String userGroup, String location, int totalSlots, int campCommSlots, String description, boolean visibility, CampStorage campStorage){
        Camp newCamp = new Camp(campName, startDate, endDate, regDeadLine, userGroup, location, totalSlots, campCommSlots, description, getUserId(), visibility);
        System.out.println("Camp created successfully");
        campStorage.addItem(newCamp);
        System.out.println("Camp added to storage");
    }

    public void processSuggestion(Suggestion tarSuggestion, StudentStorage studentStorage, boolean approval){
        tarSuggestion.setApproval(approval);
        tarSuggestion.setProcessed(true);
        if (approval) {
            System.out.println("Suggestion Accepted");
            String studentName = tarSuggestion.getSuggestor();
            StudentAccount studentNameObj = studentStorage.getData(studentName);
            studentNameObj.addPoints();
        }
        else {
            System.out.println("Suggestion Rejected");
        }
    }

    public void editCamp(Camp campToBeEdited, EditCampOperation editOperation) {
        if (campToBeEdited == null || editOperation == null) {
            System.out.println("Invalid arguments");
            return;
        }
        campToBeEdited.editCamp(editOperation);
    }

    public void generatePerformanceReport(CampStorage campStorage, StudentStorage studentStorage, String fileName) {
        PerformanceReportTXTWriter writer = new PerformanceReportTXTWriter();
        if (PerformanceReportWriter.fileExists(fileName)) {
            System.out.println("File already exists. Please choose another file name.");
            return;
        }
        ArrayList<Camp> campsByStaff = ReportPrepper.findCampsByStaff(getUserId(), campStorage);
        for (Camp camp : campsByStaff) {
            writer.writeData(fileName, camp, studentStorage);
        }
        System.out.println("Performance report outputted to " + fileName + " successfully.");
    }
}
