import java.io.*;
import java.util.ArrayList;

public class CSVSuggestionWriter implements DataWriter{
    public void performWrite(String file, Storage s) {
        if (!(s instanceof SuggestionStorage)) {
            System.out.println("Error: Storage is not a SuggestionStorage.");
            System.exit(4);
        }
        try {
            String header = CSVReader.getHeader(file);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write the header first
            bw.write(header);
            bw.newLine();
            // Write the data
            ArrayList<Suggestion> suggestionList = s.getData();
            for (Suggestion suggestion : suggestionList) {
                String approvalString = suggestion.getApproval() ? "1" : "0";
                String processedString = suggestion.getProcessed() ? "1" : "0";
                bw.write(suggestion.getSuggestionId() + "," + suggestion.getSuggestor() + "," +
                 "\""+suggestion.getSuggestion()+"\"" + "," + approvalString + "," + processedString);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error: Suggestion CSV file could not be written.");
            System.exit(2);
        }
    }
}
