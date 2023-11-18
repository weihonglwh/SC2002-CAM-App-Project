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
}
