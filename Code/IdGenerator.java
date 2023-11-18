import java.util.ArrayList;

public class IdGenerator {
    public static int generateID(Storage s) {
        if (s instanceof EnquiryStorage) {
            String id = "1";
            boolean idExists = true;
            while (idExists) {
                Enquiry enquiry = ((EnquiryStorage) s).getData(id);
                if (enquiry != null) {
                    id = Integer.toString(Integer.parseInt(id) + 1);
                } else {
                    idExists = false;
                }
            }
            return Integer.parseInt(id);
        } else if (s instanceof SuggestionStorage) {
            String id = "1";
            boolean idExists = true;
            while (idExists) {
                Suggestion suggestion = ((SuggestionStorage) s).getData(id);
                if (suggestion != null) {
                    id = Integer.toString(Integer.parseInt(id) + 1);
                } else {
                    idExists = false;
                }
            }
            return Integer.parseInt(id);
        } else return -1;
    }
}
