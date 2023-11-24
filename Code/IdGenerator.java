/**
 * IdGenerator is a class that generates a unique ID for a new enquiry or suggestion.
 * @version 1.0
 * @since 2023-11-24
 */

public class IdGenerator {
    /**
     * Generates a unique ID for a new enquiry or suggestion.
     * @param storage Storage object.
     * @return Unique ID for a new enquiry or suggestion.
     */
    public static int generateID(Storage storage) {
        // Generate a unique ID for a new enquiry
        if (storage instanceof EnquiryStorage) {
            String id = "1";
            boolean idExists = true;
            while (idExists) {
                // Check if the ID already exists in the storage
                Enquiry enquiry = ((EnquiryStorage) storage).getData(id);
                if (enquiry != null) {
                    // If the ID exists, increment the ID by 1 and retry
                    id = Integer.toString(Integer.parseInt(id) + 1);
                } else {
                    idExists = false;
                }
            }
            return Integer.parseInt(id);
        }
        // Generate a unique ID for a new suggestion
        else if (storage instanceof SuggestionStorage) {
            String id = "1";
            boolean idExists = true;
            while (idExists) {
                // Check if the ID already exists in the storage
                Suggestion suggestion = ((SuggestionStorage) storage).getData(id);
                if (suggestion != null) {
                    // If the ID exists, increment the ID by 1 and retry
                    id = Integer.toString(Integer.parseInt(id) + 1);
                } else {
                    idExists = false;
                }
            }
            return Integer.parseInt(id);
        }
        // If the storage is not an instance of EnquiryStorage or SuggestionStorage, return -1
        else {
            System.out.println("[ Invalid storage type used for IdGenerator ]");
            return -1;
        }
    }
}
