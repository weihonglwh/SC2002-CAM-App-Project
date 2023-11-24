/**
 * Suggestion class
 * @version 1.0
 * @since 2023-11-24
 */
public class Suggestion {
    /**
     * The name of the suggester.
     */
    private String suggester;

    /**
     * The suggestion proposed by the suggester.
     */
    private String suggestion;

    /**
     * The approval status of the suggestion.
     */
    private boolean approval;

    /**
     * The processed status of the suggestion.
     */
    private boolean processed;

    /**
     * The suggestion ID of the suggestion.
     */
    private int suggestionID;

    /**
     * The camp name of the suggestion.
     */
    private String campName;

    /**
     * Constructor for Suggestion class.
     * @param suggester The name of the suggester.
     * @param suggestion The suggestion proposed by the suggester.
     * @param suggestionID The suggestion ID of the suggestion.
     * @param campName The camp name of the suggestion.
     */
    public Suggestion(String suggester, String suggestion, int suggestionID, String campName) {
        this.suggester = suggester;
        this.suggestion = suggestion;
        this.approval = false;
        this.processed = false;
        this.suggestionID = suggestionID;
        this.campName = campName;
    }

    /**
     * Constructor for Suggestion class
     * @param suggester The name of the suggester.
     * @param suggestion The suggestion proposed by the suggester.
     * @param suggestionID The suggestion ID of the suggestion.
     * @param approval The approval status of the suggestion.
     * @param processed The processed status of the suggestion.
     * @param campName The camp name of the suggestion.
     */
    public Suggestion(String suggester, String suggestion, int suggestionID, boolean approval, boolean processed, String campName) {
        this.suggester = suggester;
        this.suggestion = suggestion;
        this.approval = approval;
        this.processed = processed;
        this.suggestionID = suggestionID;
        this.campName = campName;
    }

    /**
     * Get the suggestion ID.
     * @return The ID of the suggestion.
     */
    public int getSuggestionId(){
        return suggestionID;
    }

    /**
     * Get the suggester.
     * @return Name of the suggester.
     */
    public String getSuggester(){
        return suggester;
    }

    /**
     * Get the suggestion.
     * @return Suggestion the suggestion proposed by the suggester.
     */
    public String getSuggestion(){
        return suggestion;
    }

    /**
     * Set the suggestion.
     * @param suggestion The suggestion proposed by the suggester.
     */
    public void setSuggestion(String suggestion){
        this.suggestion = suggestion;
    }

    /**
     * Get the approval status of the suggestion.
     * @return The approval status of the suggestion.
     */
    public boolean getApproval(){
        return approval;
    }

    /**
     * Set the approval status of the suggestion.
     * @param approval The approval status of the suggestion(accept/reject).
     */
    public void setApproval(boolean approval){
        this.approval = approval;
    }

    /**
     * Get the processed status of the suggestion.
     * @return The processed status of the suggestion.
     */
    public boolean getProcessed(){
        return processed;
    }

    /**
     * Set the processed status of the suggestion.
     * @param processed The processed status of the suggestion.
     */
    public void setProcessed(boolean processed){
        this.processed = processed;
    }

    /**
     * Get the camp name of the suggestion.
     * @return The camp name of the suggestion.
     */
    public String getCampName(){return campName;}

}
