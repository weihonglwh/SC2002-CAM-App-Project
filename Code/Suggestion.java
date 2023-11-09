public class Suggestion {
    private String suggestor;
    private String suggestion;
    private boolean approval;
    private boolean processed;
    private String suggestionID;

    public Suggestion(String suggestor, String suggestion, String suggestionID){
        this.suggestor = suggestor;
        this.suggestion = suggestion;
        this.approval = false;
        this.processed = false;
        this.suggestionID = suggestionID;
    }

    public Suggestion(String suggestor, String suggestion, String suggestionID, boolean approval, boolean processed){
        this.suggestor = suggestor;
        this.suggestion = suggestion;
        this.approval = approval;
        this.processed = processed;
        this.suggestionID = suggestionID;
    }
    
    public String getSuggestionId(){
        return suggestionID;
    }

    public void setSuggestionId(String Id){
        this.suggestionID = Id;
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

}
