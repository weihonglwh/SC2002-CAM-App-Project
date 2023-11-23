import java.io.StringReader;
import java.util.ArrayList;
import java.util.Dictionary;

public abstract class UserAccount {
    private String name;
    private String userId;
    private String password;
    private String faculty;

    
    public UserAccount(String name, String userId, String password, String faculty){
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.faculty = faculty;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFaculty(){
        return faculty;
    }

    public void setFaculty(String faculty){
        this.faculty = faculty;
    };

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
            if (campCommCamp.equals("")) {
                System.out.println("You are not a camp committee of any camp.");
                return;
            }
            else {
                writer.writeHeader(fileName);
                Camp camp = campStorage.getData(campCommCamp);
                ArrayList<Dictionary<String, String>> namesAndRoles = ReportPrepper.buildDictionary(camp, studentStorage);
                writer.writeData(fileName, namesAndRoles, camp, filter);
            }
        }
        System.out.println("Attendee list outputted to " + fileName + " successfully.");
    }

    public void generateAttendeeListTXT(Filter filter, CampStorage campStorage, StudentStorage studentStorage, String fileName, UserAccount accountType) {
        AttendeeListTXTWriter writer = new AttendeeListTXTWriter();
        if (writer.fileExists(fileName)) {
            System.out.println("File already exists. Please choose another file name.");
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
            if (campCommCamp.equals("")) {
                System.out.println("You are not a camp committee of any camp.");
                return;
            }
            else {
                Camp camp = campStorage.getData(campCommCamp);
                ArrayList<Dictionary<String, String>> namesAndRoles = ReportPrepper.buildDictionary(camp, studentStorage);
                writer.writeData(fileName, namesAndRoles, camp, filter);
            }
        }
        System.out.println("Attendee list outputted to " + fileName + " successfully.");
    }
}
