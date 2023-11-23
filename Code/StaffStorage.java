import java.util.ArrayList;

public class StaffStorage extends Storage {
    private ArrayList<StaffAccount> staffs;

    public StaffStorage() {
        super();
        staffs = new ArrayList<StaffAccount>();
    }

    public void printData() {
        for (StaffAccount s : staffs) {
            System.out.println("The name of the staff is " + s.getName());
            System.out.println("The username of the staff is " + s.getUsername());
            System.out.println("The password of the staff is " + s.getPassword());
            System.out.println("The faculty is " + s.getFaculty());
        }
    }

    public ArrayList getData() {
        return staffs;
    }

    public StaffAccount getData(String s) {
        for (StaffAccount s1 : staffs) {
            if (s1.getUsername().equals(s)) {
                return s1;
            }
        }
        return null;
    }

    public void addItem(Object o) {
        if (o instanceof StaffAccount)
        {
            staffs.add((StaffAccount)o);
        }
        else
        {
            System.out.println("Invalid object type");
            System.exit(0);
        }

    }

    public void populateData(CSVReader reader) {
        ArrayList<String> staffData = reader.performRead("staff.csv");
        try {
            for (String staff : staffData) { // Iterate through each line
                String[] staffDetails = staff.split(",");
                // Trim to remove whitespace
                String name = staffDetails[0].trim();
                String userId = staffDetails[1].trim().split("@")[0];
                String faculty = staffDetails[2].trim();
                String password = staffDetails[3];
                //System.out.println("Adding staff: " + name + " " + userId + " " + faculty + " " + password);
                this.addItem(new StaffAccount(name, userId, password, faculty));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Staff CSV file may be missing an entry.");
            System.exit(3);
        }
    }
}
