import java.util.ArrayList;
import java.util.Dictionary;

public interface Filter {
    public abstract ArrayList<Dictionary<String, String>> performFilter(ArrayList<Dictionary<String, String>> namesAndRoles);
}
