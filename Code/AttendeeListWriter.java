import java.util.ArrayList;
import java.util.Dictionary;

public interface AttendeeListWriter {
    public void writeData(String fileName, ArrayList<Dictionary<String, String>> nameAndRoles, Camp c, Filter filter);
}
