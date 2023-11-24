import java.util.ArrayList;

/**
 * StaffStorage is an implementation of Storage that stores StaffAccount objects.
 * It contains methods to get data, get a specific object, add items, and populate data from a CSV file.
 * @version 1.0
 * @since 2023-11-24
 * @see Storage
 */
public class StaffStorage implements Storage {
    /**
     * The list of staffs.
     */
    private ArrayList<StaffAccount> staffs;

    /**
     * Constructor for StaffStorage class.
     */
    public StaffStorage() {
        super();
        staffs = new ArrayList<StaffAccount>();
    }

    /**
     * To get the list of staffs.
     */
    public ArrayList<StaffAccount> getData() {
        return staffs;
    }

    /**
     * To get details of a specific staff.
     */
    public StaffAccount getData(String s) {
        for (StaffAccount s1 : staffs) {
            if (s1.getUsername().equals(s)) {
                return s1;
            }
        }
        return null;
    }

    /**
     * To add a staff to the list of staff.
     */
    public void addItem(Object o) {
        if (o instanceof StaffAccount)
        {
            staffs.add((StaffAccount)o);
        }
        else
        {
            System.out.println("[ Invalid object type ]");
            System.exit(0);
        }

    }

    /**
     * To populate data from a CSV file.
     */
    public void populateData(CSVReader reader) {
        ArrayList<String> staffData = reader.performRead("staff.csv");
        try {
            for (String staff : staffData) { // Iterate through each line
                String[] staffDetails = staff.split(",");
                // Trim to remove whitespace
                String name = staffDetails[0].trim();
                // Check if email is valid
                if (!staffDetails[1].trim().contains("@")) {
                    System.out.println("[ Error: Staff CSV file contains invalid email. ]");
                    System.exit(4);
                }
                String userId = staffDetails[1].trim().split("@")[0];
                String faculty = staffDetails[2].trim();
                String password = staffDetails[3];
                this.addItem(new StaffAccount(name, userId, password, faculty));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[ Error: Staff CSV file may be missing an entry. ]");
            System.exit(3);
        }
    }
}
