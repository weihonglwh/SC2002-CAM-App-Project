import java.io.*;
import java.util.ArrayList;

/**
 * CSVReader is a realization of DataReader that reads data from a CSV file.
 * @version 1.0
 * @since 2023-11-24
 * @see DataReader
 */
public class CSVReader implements DataReader{
    /**
     * Reads data from a CSV file.
     * @param fileName Name of the file to be read from.
     * @return All data in the CSV file as an ArrayList of Strings.
     *         Each String is a line in the CSV file.
     */
    public ArrayList<String> performRead(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); // Don't read headers
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
            System.out.println("[ Error: Required CSV file not found. ]");
            System.exit(1);
            return null;
        }
        catch (IOException e) {
            System.out.println("[ Error: Required CSV file could not be read. ]");
            System.exit(2);
            return null;
        }        
    }

    /**
     * Retrieves the column headers from a CSV file.
     * @param fileName Name of the file to be read from.
     * @return The column headers as a String.
     */
    public static String getHeader(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String header = br.readLine();
            br.close();
            fr.close();
            if (header == null) {
                throw new IOException();
            }
            else {
                return header;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("[ Error: Required CSV file not found. ]");
            System.exit(1);
            return null;
        }
        catch (IOException e) {
            System.out.println("[ Error: Required CSV file could not be read. ]");
            System.exit(2);
            return null;
        }
    }
}
