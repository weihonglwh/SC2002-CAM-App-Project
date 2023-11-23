import java.util.ArrayList;
import java.util.Dictionary;

public class CampCommFilter implements Filter{
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
