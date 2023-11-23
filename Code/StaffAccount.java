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

    public void processSuggestion(int choice, Suggestion tarSuggestion, StudentStorage studentStorage ){
        switch (choice) {
            case 1:
                tarSuggestion.setApproval(true);
                tarSuggestion.setProcessed(true);
                System.out.println("Suggestion Accepted");
                String studentName = tarSuggestion.getSuggestor();
                StudentAccount studentNameObj = studentStorage.getData(studentName);
                studentNameObj.addPoints();
                break;
            case 2:
                tarSuggestion.setApproval(false);
                tarSuggestion.setProcessed(true);
                System.out.println("Suggestion Rejected");
                break;
        }
    }

    public void editCamp(Camp campToBeEdited, EditOperationForStaff editOperationForStaff) {
        if (campToBeEdited == null || editOperationForStaff == null) {
            System.out.println("Invalid arguments");
            return;
        }
        campToBeEdited.editCamp(editOperationForStaff);
    }
}
