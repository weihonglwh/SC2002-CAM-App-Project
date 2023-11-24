import java.util.ArrayList;

/**
 * SuggestionStorage is a subclass of Storage that stores Suggestion objects.
 * It contains methods to get data, get a specific object, add items, and populate data from a CSV file.
 * @version 1.0
 * @since 2023-11-24
 * @see Storage
 */
public class SuggestionStorage extends Storage {
    /**
     * The list of suggestions.
     */
    private ArrayList<Suggestion> suggestions;

    /**
     * Constructor for SuggestionStorage class.
     */
    public SuggestionStorage() {
        super();
        suggestions = new ArrayList<Suggestion>();
    }

    /**
     * To print the data of the all suggestions for a certain camp.
     * @param campName The name of the camp.
     */
    public void printData(String campName) {
        boolean no_suggestion = true;
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getCampName().equals(campName) && !suggestion.getSuggestion().isEmpty()){
                no_suggestion = false;
                System.out.println("[ The suggester is " + suggestion.getSuggester()+ " ]");
                System.out.println("[ The suggestion is " + suggestion.getSuggestion()+ " ]");
                System.out.println("[ The suggestion id is " + suggestion.getSuggestionId()+ " ]");
                if (suggestion.getApproval()) {
                    System.out.println("[ The suggestion has been approved ]");
                } else {
                    System.out.println("[ The suggestion has not been approved ]");
                }
                if(suggestion.getProcessed()){
                    System.out.println("[ The suggestion has been processed ]");
                }
                else{
                    System.out.println("[ The suggestion has not been processed ]");
                }
                System.out.println();
            }
        }
        if (no_suggestion){
            System.out.println("[ There is no suggestion made for this camp. ]");
        }
    }

    /**
     * To get the list of suggestions.
     * @return The list of suggestions.
     */
    public ArrayList getData() {
        return suggestions;
    }

    /**
     * To get the object of a specific suggestion.
     * @param id The suggestion id.
     * @return The Suggestion object.
     */
    public Suggestion getData(String id) {
        int idInt;
        try {
            idInt = Integer.parseInt(id);
        }
        catch (NumberFormatException e) {
            System.out.println("[ Error: Invalid suggestion ID. ]");
            return null;
        }
        for (Suggestion tar_suggestion : suggestions) {
            if (tar_suggestion.getSuggestionId() == idInt) {
                return tar_suggestion;
            }
        }
        return null;
    }

    /**
     * To add a suggestion to the list.
     * @param object The Suggestion object.
     */
    public void addItem(Object object) {
        if (object instanceof Suggestion){
            suggestions.add((Suggestion)object);
        }
        else{
            System.out.println("[ Invalid object type ]");
            System.exit(0);
        }
    }

    /**
     * To delete a suggestion from the list.
     * @param object The Suggestion object.
     */
    public void deleteItem(Object object) {
        if (object instanceof Suggestion){
            suggestions.remove((Suggestion) object);
        }
        else{
            System.out.println("[ Invalid object type ]");
            System.exit(0);
        }
    }

    /**
     * To populate the data from a CSV file.
     * @param reader The CSVReader object.
     */
    public void populateData(CSVReader reader) {
        ArrayList<String> suggestionData = reader.performRead("suggestion.csv");
        try {
            for (String suggestion : suggestionData) { // Iterate through each line
                String[] suggestionDetails = suggestion.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // regex to prevent splitting commas in messages
                // Check if id/sender/message is empty
                if (suggestionDetails[0].isBlank() || suggestionDetails[1].isBlank() || suggestionDetails[2].replace("\"", "").isBlank()) {
                    throw new Exception();
                }
                // Trim to remove whitespace
                String suggestionID = suggestionDetails[0].trim();
                int idInt = Integer.parseInt(suggestionID);
                String suggester = suggestionDetails[1].trim();
                String suggestionMsg = suggestionDetails[2].trim().replace("\"", ""); // Remove quotes from message
                // Replace 1/0 with true/false for boolean parsing
                suggestionDetails[3] = suggestionDetails[3].replace("1", "true");
                suggestionDetails[3] = suggestionDetails[3].replace("0", "false");
                suggestionDetails[4] = suggestionDetails[4].replace("1", "true");
                suggestionDetails[4] = suggestionDetails[4].replace("0", "false");
                boolean approval = Boolean.parseBoolean(suggestionDetails[3].trim());
                boolean processed = Boolean.parseBoolean(suggestionDetails[4].trim());
                String campName = suggestionDetails[5].trim();
                this.addItem(new Suggestion(suggester, suggestionMsg, idInt, approval, processed, campName));
            }
        }
        catch (Exception e) {
            System.out.println("[ Error: Suggestion CSV file may be missing an entry. ]");
            System.exit(3);
        }
    }
}
