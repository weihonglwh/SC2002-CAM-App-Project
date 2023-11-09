import java.util.ArrayList;

public class SuggestionStorage extends Storage {
    private ArrayList<Suggestion> suggestions;

    public SuggestionStorage() {
        super();
        suggestions = new ArrayList<Suggestion>();
    }

    public void printData() {
        for (Suggestion s : suggestions) {
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

    public ArrayList getData() {
        return suggestions;
    }

    public Object getData(String s) {
        for (Suggestion tar_suggestion : suggestions) {
            if (tar_suggestion.getSuggestionId().equals(s)) {
                return s;
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
}
