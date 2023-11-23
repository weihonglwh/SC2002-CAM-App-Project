import java.util.ArrayList;

public class SuggestionStorage extends Storage {
    private ArrayList<Suggestion> suggestions;

    public SuggestionStorage() {
        super();
        suggestions = new ArrayList<Suggestion>();
    }

    public void printData(String campName) {
        boolean no_suggestion = true;
        for (Suggestion s : suggestions) {
            if (s.getCampName().equals(campName) && !s.getSuggestion().isEmpty()){
                no_suggestion = false;
                System.out.println("The suggestor is " + s.getSuggestor());
                System.out.println("The suggestion is " + s.getSuggestion());
                System.out.println("The suggestion id is " + s.getSuggestionId());
                if (s.getApproval() == true) {
                    System.out.println("The suggestion has been approved");
                } else {
                    System.out.println("The suggestion has not been approved");
                }
                if(s.getProcessed() == true){
                    System.out.println("The suggestion has been processed");
                }
                else{
                    System.out.println("The suggestion has not been processed");
                }
                System.out.println("");
            }
        }
        if (no_suggestion){
            System.out.println("There is no suggestion made for this camp.");
        }
    }

    public ArrayList getData() {
        return suggestions;
    }

    public Suggestion getData(String id) {
        for (Suggestion tar_suggestion : suggestions) {
            if (tar_suggestion.getSuggestionId() == Integer.parseInt(id)) {
                return tar_suggestion;
            }
        }
        return null;
    }

    public void addItem(Object o) {
        if (o instanceof Suggestion){
            suggestions.add((Suggestion)o);
        }
        else{
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }

    public void deleteItem(Object o) {
        if (o instanceof Suggestion){
            suggestions.remove((Suggestion) o);
        }
        else{
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }

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
                this.addItem(new Suggestion(suggestor, suggestionMsg, idInt, approval, processed, campName));
            }
        }
        catch (Exception e) {
            System.out.println("Error: Suggestion CSV file may be missing an entry.");
            System.exit(3);
        }
    }
}
