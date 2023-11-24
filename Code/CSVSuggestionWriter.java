import java.io.*;
import java.util.ArrayList;

/**
 * Class to write all suggestions in the system to a CSV file
 * @version 1.0
 * @since 2023-11-24
 * @see DataWriter
 */
public class CSVSuggestionWriter implements DataWriter{
    /**
     * Writes all suggestions in the system to a CSV file.
     * Overwrites existing data in the CSV file.
     * @param fileName Name of the file to be written to.
     *                 Should be "suggestion.csv" by default.
     * @param storage Storage containing the suggestions to be written to the CSV file.
     *                Should be a SuggestionStorage object.
     */
    public void performWrite(String fileName, Storage storage) {
        // Check if storage is a SuggestionStorage object
        if (!(storage instanceof SuggestionStorage)) {
            System.out.println("[ Error: Storage is not a SuggestionStorage. ]");
            System.exit(4);
        }
        try {
            String header = CSVReader.getHeader(fileName);
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write the header first
            if (!header.isBlank()) {
                bw.write(header);
                bw.newLine();
            }
            // Write the data
            ArrayList<Suggestion> suggestionList = storage.getData();
            for (Suggestion suggestion : suggestionList) {
                String approvalString = suggestion.getApproval() ? "1" : "0";
                String processedString = suggestion.getProcessed() ? "1" : "0";
                bw.write(suggestion.getSuggestionId() + "," + suggestion.getSuggester() + "," +
                 "\""+suggestion.getSuggestion()+"\"" + "," + approvalString + "," + processedString + "," + suggestion.getCampName());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("[ Error: Suggestion CSV file could not be written. ]");
            System.exit(2);
        }
    }
}
