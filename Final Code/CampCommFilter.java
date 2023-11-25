import java.util.ArrayList;
import java.util.Dictionary;

/**
 * Filter for camp committee members only in an Arraylist of names and roles
 * @version 1.0
 * @since 2023-11-24
 * @see Filter
 */
public class CampCommFilter implements Filter{
    /**
     * Filters to have only camp committee members in the ArrayList of dictionaries containing names and roles.
     * @param namesAndRoles ArrayList of dictionaries containing participant name and role.
     * @return Filtered ArrayList of dictionaries containing only camp committee members.
     */
    public ArrayList<Dictionary<String, String>> performFilter(ArrayList<Dictionary<String, String>> namesAndRoles){
        ArrayList<Dictionary<String, String>> filteredNamesAndRoles = new ArrayList<>();
        for (Dictionary<String, String> nameAndRole : namesAndRoles) {
            String name = nameAndRole.keys().nextElement();
            String role = nameAndRole.get(name);
            if (role.equals("Camp Committee")) {
                filteredNamesAndRoles.add(nameAndRole);
            }
        }
        return filteredNamesAndRoles;
    }
}
