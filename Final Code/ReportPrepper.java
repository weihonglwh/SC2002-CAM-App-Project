import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * ReportPrepper is a class that prepares data for the report.
 * It contains methods to find camps created by staff, build dictionary of students and roles, and prepare student name for reports.
 * @version 1.0
 * @since 2023-11-24
 */

public class ReportPrepper {
    /**
     * To find camps created by a specific staff.
     * @param staffId The user ID of the staff.
     * @param campStorage The CampStorage object.
     * @return The list of camps created by the staff.
     */
    public static ArrayList<Camp> findCampsByStaff(String staffId, CampStorage campStorage) {
        ArrayList<Camp> camps = campStorage.getData();
        ArrayList<Camp> campsByStaff = new ArrayList<>();
        for (Camp camp : camps) {
            if (camp.getStaffIC().equals(staffId)) {
                campsByStaff.add(camp);
            }
        }
        return campsByStaff;
    }

    /**
     * Creates a ArrayList containing dictionaries of student names and roles.
     * @param camp The camp to build the dictionary for.
     * @param studentStorage The StudentStorage object containing all the students' data.
     * @return The ArrayList containing dictionaries of student names and roles for the camp.
     */
    public static ArrayList<Dictionary<String, String>> buildDictionary(Camp camp, StudentStorage studentStorage) {
        ArrayList<Dictionary<String, String>> namesAndRoles = new ArrayList<>();
        ArrayList<String> attendees = camp.getAttendees();
        ArrayList<String> campCommitteeMembers = camp.getCampCommitteeMembers();
        // Add attendees
        for (String attendee : attendees) {
            String nameAndId = prepareStudentName(attendee, studentStorage);
            Dictionary<String, String> nameAndRole = new Hashtable<>();
            nameAndRole.put(nameAndId, "Attendee");
            namesAndRoles.add(nameAndRole);
        }
        // Add camp committee members
        for (String campComm : campCommitteeMembers) {
            String nameAndId = prepareStudentName(campComm, studentStorage);
            Dictionary<String, String> nameAndRole = new Hashtable<>();
            nameAndRole.put(nameAndId, "Camp Committee");
            namesAndRoles.add(nameAndRole);
        }
        return namesAndRoles;
    }

    /**
     * Prepares the student name for the report consisting of both the name and the student ID.
     * @param studentId The student ID.
     * @param studentStorage The StudentStorage object containing the student data.
     * @return The student name for the report containing name and ID.
     */
    public static String prepareStudentName(String studentId, StudentStorage studentStorage) {
        StudentAccount studentAccount = studentStorage.getData(studentId);
        return studentAccount.getName() + " (" + studentId + ")";
    }
}
