import java.util.ArrayList;
import java.util.Dictionary;

/**
 * Filter is an interface that filters an ArrayList containing names and roles.
 * @version 1.0
 * @since 2023-11-24
 */

public interface Filter {
    /**
     * Abstract method that performs filtering on an ArrayList containing names and roles.
     * @param namesAndRoles ArrayList of dictionaries containing participant name and role.
     * @return Returns an ArrayList of dictionaries containing participant name and role after filtering.
     */
    public abstract ArrayList<Dictionary<String, String>> performFilter(ArrayList<Dictionary<String, String>> namesAndRoles);
}
