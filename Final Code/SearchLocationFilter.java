import java.util.ArrayList;

/**
 * This class implements the SearchFilter interface.
 * It filters the list of camps whose location name consists of the filter location.
 * @version 1.0
 * @since 2023-11-26
 */
public class SearchLocationFilter implements SearchFilter {
    /**
     * This method filters the list of camps whose location name consists of the filter location.
     * @param camps The list of camps to be filtered.
     * @param location The filter object to be compared within the method body.
     * @return The filtered list of camps.
     */
    public ArrayList<Camp> performFilter(ArrayList<Camp> camps, Object location) {
        if (location instanceof String) {
            String searchLocation = (String) location;
            ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
            for (Camp c : camps) {
                if (c.getLocation().contains(searchLocation)) {
                    filteredCamps.add(c);
                }
            }
            return filteredCamps;
        } else {
            // Invalid filter object, return original list
            System.out.println("[ Warning: Invalid filter object, no filter performed ]");
            return camps;
        }
    }
}
