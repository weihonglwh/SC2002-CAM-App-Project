import java.io.*;
import java.util.ArrayList;

public abstract class CSVReader implements DataReader{
    public ArrayList<String> performRead(String file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); // Remove headers
            String line = br.readLine();
            ArrayList<String> result = new ArrayList<String>();
            while (line != null) {
                result.add(line);
                line = br.readLine();
            }
            br.close();
            fr.close();
            return result;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Required CSV file not found.");
            System.exit(1);
            return null;
        }
        catch (IOException e) {
            System.out.println("Error: Required CSV file could not be read.");
            System.exit(2);
            return null;
        }        
    }

    public abstract void populateStorage(Storage s);
}
