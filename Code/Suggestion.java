public class Suggestion {
    private String suggestor;
    private String suggestion;
    private boolean approval;
    private boolean processed;
    private int suggestionID;
    private String campName;

    public Suggestion(String suggestor, String suggestion, int suggestionID, String campName) {
        this.suggestor = suggestor;
        this.suggestion = suggestion;
        this.approval = false;
        this.processed = false;
        this.suggestionID = suggestionID;
        this.campName = campName;
    }

    public Suggestion(String suggestor, String suggestion, int suggestionID, boolean approval, boolean processed, String campName) {
        this.suggestor = suggestor;
        this.suggestion = suggestion;
        this.approval = approval;
        this.processed = processed;
        this.suggestionID = suggestionID;
        this.campName = campName;
    }
    
    public int getSuggestionId(){
        return suggestionID;
    }

    public void setSuggestionId(int id){
        this.suggestionID = id;
    }

    public String getSuggestor(){
        return suggestor;
    }

    public void setSuggestor(String suggestor){
        this.suggestor = suggestor;
    }

    public String getSuggestion(){
        return suggestion;
    }

    public void setSuggestion(String suggestion){
        this.suggestion = suggestion;
    }

    public boolean getApproval(){
        return approval;
    }

    public void setApproval(boolean approval){
        this.approval = approval;
    }

    public boolean getProcessed(){
        return processed;
    }

    public void setProcessed(boolean processed){
        this.processed = processed;
    }

    public String getCampName(){return campName;}

    public void setCampName(String campName){this.campName = campName;}


}
