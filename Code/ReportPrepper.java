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

    public static ArrayList<Dictionary> buildDictionary(Camp camp) {
        ArrayList<Dictionary> namesAndRoles = new ArrayList<>();
        ArrayList<String> attendees = camp.getAttendees();
        ArrayList<String> campComms = camp.getCampComms();
        for (String attendee : attendees) {
            Dictionary<String, String> nameAndRole = new Hashtable<>();
            nameAndRole.put(attendee, "Attendee");
            namesAndRoles.add(nameAndRole);
        }
        for (String campComm : campComms) {
            Dictionary<String, String> nameAndRole = new Hashtable<>();
            nameAndRole.put(campComm, "Camp Comm");
            namesAndRoles.add(nameAndRole);
        }
        return namesAndRoles;
    }
}
