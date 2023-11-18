import java.util.ArrayList;

/* 
    Col 0: Id
    Col 1: Suggestor
    Col 2: Suggestion
    Col 3: Approval
    Col 4: Processed
    Col 5: CampName
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
                int idInt = Integer.parseInt(suggestionID);
                String suggestor = suggestionDetails[1].trim();
                String suggestionMsg = suggestionDetails[2].trim().replace("\"", ""); // Remove quotes from message
                // Replace 1/0 with true/false for boolean parsing
                suggestionDetails[3] = suggestionDetails[3].replace("1", "true");
                suggestionDetails[3] = suggestionDetails[3].replace("0", "false");
                suggestionDetails[4] = suggestionDetails[4].replace("1", "true");
                suggestionDetails[4] = suggestionDetails[4].replace("0", "false");
                boolean approval = Boolean.parseBoolean(suggestionDetails[3].trim());
                boolean processed = Boolean.parseBoolean(suggestionDetails[4].trim());
                String campName = suggestionDetails[5].trim();
                //System.out.println("Adding suggestion: " + suggestionID + " " + suggestor + " " + suggestionMsg + " " + approval + " " + processed + " " + campName);
                s.addItem(new Suggestion(suggestor, suggestionMsg, idInt, approval, processed, campName));
            }
        }
        catch (Exception e) {
            System.out.println("Error: Suggestion CSV file may be missing an entry.");
            System.exit(3);
        }
    }
}
