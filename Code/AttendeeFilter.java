import java.util.ArrayList;
import java.util.Dictionary;

/**
 * This class is an implementation of the Filter interface.
 * It is used to filter out only the attendees from a ArrayList of dictionaries containing names and roles.
 * @version 1.0
 * @since 2023-11-24
 * @see Filter
 */

public class AttendeeFilter implements Filter{
    /**
     * Filters to have only attendees in the ArrayList of dictionaries containing names and roles.
     * @param namesAndRoles ArrayList of dictionaries containing participant name and role.
     * @return Filtered ArrayList of dictionaries containing only attendees.
     */
    public ArrayList<Dictionary<String, String>> performFilter(ArrayList<Dictionary<String, String>> namesAndRoles){
        ArrayList<Dictionary<String, String>> filteredNamesAndRoles = new ArrayList<>();
        for (Dictionary<String, String> nameAndRole : namesAndRoles) {
            String name = nameAndRole.keys().nextElement();
            String role = nameAndRole.get(name);
            if (role.equals("Attendee")) {
                filteredNamesAndRoles.add(nameAndRole);
            }
        }
        return filteredNamesAndRoles;
    }
}
