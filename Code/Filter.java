import java.util.ArrayList;
import java.util.Dictionary;

public interface Filter {
    public ArrayList<Dictionary<String, String>> performFilter(ArrayList<Dictionary<String, String>> namesAndRoles);
}
