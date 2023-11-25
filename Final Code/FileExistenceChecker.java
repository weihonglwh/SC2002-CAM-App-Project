import java.io.File;

/**
 * Class to check if a file exists.
 * @version 1.0
 * @since 2023-11-24
 */
public class FileExistenceChecker {
    /**
     * Method to check if a file exists.
     * @param fileName The name of the file to be checked.
     * @return true if the file exists, false otherwise.
     */
    public static boolean fileExists(String fileName){
        File f = new File(fileName);
        return f.exists() && !f.isDirectory();
    }
}
