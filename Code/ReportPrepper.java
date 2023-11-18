import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class ReportPrepper {
    public static ArrayList<Camp> findCampsByStaff(String staffId, CampStorage cs) {
        ArrayList<Camp> camps = cs.getData();
        ArrayList<Camp> campsByStaff = new ArrayList<>();
        for (Camp camp : camps) {
            if (camp.getStaffIC().equals(staffId)) {
                campsByStaff.add(camp);
            }
        }
        return campsByStaff;
    }

    public static ArrayList<Dictionary<String, String>> buildDictionary(Camp camp, StudentStorage ss) {
        ArrayList<Dictionary<String, String>> namesAndRoles = new ArrayList<>();
        ArrayList<String> attendees = camp.getAttendees();
        ArrayList<String> campComms = camp.getCampComms();
        for (String attendee : attendees) {
            String nameAndId = prepareStudentName(attendee, ss);
            Dictionary<String, String> nameAndRole = new Hashtable<>();
            nameAndRole.put(nameAndId, "Attendee");
            namesAndRoles.add(nameAndRole);
        }
        for (String campComm : campComms) {
            String nameAndId = prepareStudentName(campComm, ss);
            Dictionary<String, String> nameAndRole = new Hashtable<>();
            nameAndRole.put(nameAndId, "Camp Committee");
            namesAndRoles.add(nameAndRole);
        }
        return namesAndRoles;
    }

    public static String prepareStudentName(String studentId, StudentStorage ss) {
        StudentAccount studentAccount = ss.getData(studentId);
        return studentAccount.getName() + " (" + studentId + ")";
    }
}
