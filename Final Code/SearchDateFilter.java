import java.util.ArrayList;
import java.util.Date;

/**
 * This class implements the SearchFilter interface.
 * It filters the list of camps that are within the date range of the filter date.
 * @version 1.0
 * @since 2023-11-26
 */
public class SearchDateFilter implements SearchFilter{
    /**
     * This method filters the list of camps that are within the date range of the filter date.
     * @param camps The list of camps to be filtered.
     * @param date The filter object to be compared within the method body.
     * @return The filtered list of camps.
     */
    public ArrayList<Camp> performFilter(ArrayList<Camp> camps, Object date) {
        if (date instanceof Date) {
            Date filterDate = (Date) date;
            ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
            for (Camp c : camps) {
                // Add to filteredCamps if filter date is within date range of start and end date of camp
                if (filterDate.equals(c.getStartDate()) || filterDate.equals(c.getEndDate()) ||
                        (filterDate.after(c.getStartDate()) && filterDate.before(c.getEndDate()))) {
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
