import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;

public abstract class AttendeeListWriter {
    public static boolean fileExists(String fileName){
        File f = new File(fileName);
        return f.exists() && !f.isDirectory();
    }
    public abstract void writeData(String fileName, ArrayList<Dictionary<String, String>> nameAndRoles, Camp c, Filter filter);
}
