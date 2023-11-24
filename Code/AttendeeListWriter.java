import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;

/**
 * Interface for writing attendee list to file to accommodate multiple file formats.
 * @version 1.0
 * @since 2023-11-24
 */
public interface AttendeeListWriter {
    /**
     * Abstract method for writing attendee list data to file.
     * @param fileName Name of file to write to.
     * @param nameAndRoles ArrayList of dictionaries containing participant name and role.
     * @param camp Camp object containing camp data.
     * @param filter Filter object containing filter criteria for attendee dictionary.
     */
    public abstract void writeData(String fileName, ArrayList<Dictionary<String, String>> nameAndRoles, Camp camp, Filter filter);
}
