import java.util.ArrayList;

/*
    Col 0: Name
    Col 1: Email
    Col 2: Faculty
    Col 3: Password
*/

public class CSVStaffReader extends CSVReader{
    public void populateStorage(Storage s) {
        if (!(s instanceof StaffStorage)) {
            System.out.println("Error: Storage is not a StaffStorage.");
            System.exit(4);
        }
        ArrayList<String> staffData = performRead("staff.csv");
        try {
            for (String staff : staffData) { // Iterate through each line
                String[] staffDetails = staff.split(",");
                // Trim to remove whitespace
                String name = staffDetails[0].trim();
                String userId = staffDetails[1].trim().split("@")[0];
                String faculty = staffDetails[2].trim();
                String password = staffDetails[3];
                //System.out.println("Adding staff: " + name + " " + userId + " " + faculty + " " + password);
                s.addItem(new StaffAccount(name, userId, password, faculty));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Staff CSV file may be missing an entry.");
            System.exit(3);
        }
    }
}
