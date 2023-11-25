import java.util.ArrayList;

/**
 * Interface to read data from a file.
 * Allows generalization of reading multiple file types.
 * @version 1.0
 * @since 2023-11-24
 */
public interface DataReader {
    /**
     * Abstract method to read data from a file.
     * To be implemented by classes that implement this interface.
     * @param fileName Name of the file to be read from.
     * @return ArrayList of Strings containing the data read from the file.
     *         Each string in the ArrayList represents a line in the file.
     */
    public abstract ArrayList<String> performRead(String fileName);
}
