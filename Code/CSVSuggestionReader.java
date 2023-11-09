import java.util.ArrayList;

/* 
    Col 0: Id
    Col 1: Suggestor
    Col 2: Suggestion
    Col 3: Approval
    Col 4: Processed
*/

public class CSVSuggestionReader extends CSVReader{
    public void populateStorage(Storage s) {
        if (!(s instanceof SuggestionStorage)) {
            System.out.println("Error: Storage is not a SuggestionStorage.");
            System.exit(4);
        }
        ArrayList<String> suggestionData = performRead("suggestion.csv");
        try {
            for (String suggestion : suggestionData) { // Iterate through each line
                String[] suggestionDetails = suggestion.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // regex to prevent splitting commas in messages
                // Check if id/sender/message is empty
                if (suggestionDetails[0].isBlank() || suggestionDetails[1].isBlank() || suggestionDetails[2].replace("\"", "").isBlank()) {
                    throw new Exception();
                }
                // Trim to remove whitespace
                String suggestionID = suggestionDetails[0].trim();
                String suggestor = suggestionDetails[1].trim();
                String suggestionMsg = suggestionDetails[2].trim().replace("\"", ""); // Remove quotes from message
                // Replace 1/0 with true/false for boolean parsing
                suggestionDetails[3] = suggestionDetails[3].replace("1", "true");
                suggestionDetails[3] = suggestionDetails[3].replace("0", "false");
                suggestionDetails[4] = suggestionDetails[4].replace("1", "true");
                suggestionDetails[4] = suggestionDetails[4].replace("0", "false");
                boolean approval = Boolean.parseBoolean(suggestionDetails[3].trim());
                boolean processed = Boolean.parseBoolean(suggestionDetails[4].trim());
                //System.out.println("Adding suggestion: " + suggestionID + " " + suggestor + " " + suggestionMsg + " " + approval + " " + processed);
                s.addItem(new Suggestion(suggestor, suggestionMsg, suggestionID, approval, processed));
            }
        }
        catch (Exception e) {
            System.out.println("Error: Suggestion CSV file may be missing an entry.");
            System.exit(3);
        }
    }

    // public static void main(String[] args) {
    //     CSVSuggestionReader reader = new CSVSuggestionReader();
    //     SuggestionStorage storage = new SuggestionStorage();
    //     reader.populateStorage(storage);
    //     ArrayList<Suggestion> suggestions = storage.getData();
    //     for (Suggestion s : suggestions) {
    //         System.out.println(s.getSuggestionId() + " " + s.getSuggestor() + " " + s.getSuggestion() + " " + s.getApproval() + " " + s.getProcessed());
    //         System.out.println();
    //     }
    // }
}
