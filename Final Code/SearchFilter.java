import java.util.ArrayList;

/**
 * This interface is to apply a filter when listing camps.
 * @version 1.0
 * @since 2023-11-26
 */
public interface SearchFilter {
    /**
     * This abstract method is to be implemented by the classes that implement this interface.
     * The method takes in an ArrayList of camps and filters it according to the filter object.
     * @param camps The list of camps to be filtered.
     * @param o The filter object to be compared within the method body.
     * @return The filtered list of camps.
     */
    public abstract ArrayList<Camp> performFilter(ArrayList<Camp> camps, Object o);
}
