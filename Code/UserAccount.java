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

    // No filter report
    public void generateAttendeeListCSV(CampStorage campStorage) {
        ArrayList<Camp> campsByStaff = ReportPrepper.findCampsByStaff(userId, campStorage);
        for (Camp camp : campsByStaff) {
            ArrayList<Dictionary> namesAndRoles = ReportPrepper.buildDictionary(camp);
            AttendeeListCSVWriter.performWrite(camp.getName() + ".csv", camp, namesAndRoles);
        }
    }

    // Report with filter
    public void generateAttendeeListCSV(Filter filter, CampStorage campStorage) {
        ArrayList<Camp> campsByStaff = ReportPrepper.findCampsByStaff(userId, campStorage);
        for (Camp camp : campsByStaff) {
            ArrayList<Dictionary> namesAndRoles = ReportPrepper.buildDictionary(camp);
            namesAndRoles = filter.performFilter(namesAndRoles);
            AttendeeListCSVWriter.performWrite(camp.getName() + ".csv", camp, namesAndRoles);
        }
    }
}
