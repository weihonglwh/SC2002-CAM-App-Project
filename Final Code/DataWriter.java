/**
 * Interface for writing data to a file.
 * Allows generalization of writing multiple file types.
 * @version 1.0
 * @since 2023-11-24
 */
public interface DataWriter {
    /**
     * Abstract method to write data to a file.
     * To be implemented by classes that implement this interface.
     * @param fileName Name of the file to be written to.
     * @param storage Storage object containing the data to be written to the file.
     */
    public abstract void performWrite(String fileName, Storage storage);
}
