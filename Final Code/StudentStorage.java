import java.util.ArrayList;

/**
 * StudentStorage is an implementation of Storage that stores StudentAccount objects.
 * It contains methods to get data, get a specific object, add items, and populate data from a CSV file.
 * @version 1.0
 * @since 2023-11-24
 * @see Storage
 */
public class StudentStorage implements Storage {
    /**
     * The list of students.
     */
    private ArrayList<StudentAccount> students;

    /**
     * Constructor for StudentStorage class.
     */
    public StudentStorage() {
        super();
        students = new ArrayList<StudentAccount>();
    }

    /**
     * To get the list of students.
     * @return The list of students.
     */
    public ArrayList<StudentAccount> getData() {
        return students;
    }

    /**
     * To get a specific student.
     * @param s The user ID of the student.
     * @return The StudentAccount object.
     */
    public StudentAccount getData(String s) {
        for (StudentAccount st : students) {
            if (st.getUserId().equals(s)) {
                return st;
            }
        }
        return null;
    }

    /**
     * To add a student to the list.
     * @param object The StudentAccount object.
     */
    public void addItem(Object object) {
        if (object instanceof StudentAccount)
        {
            students.add((StudentAccount)object);
        }
        else
        {
            System.out.println("[ Invalid object type ]");
            System.exit(0);
        }
    }

    /**
     * To populate data from a CSV file.
     * @param reader The CSVReader object.
     */
    public void populateData(CSVReader reader) {
        ArrayList<String> studentData = reader.performRead("student.csv");
        // Check if at least an entry exists
        if (studentData.isEmpty()) {
            System.out.println("[ Error: Student CSV file is empty. ]");
            System.exit(2);
        }
        try {
            for (String student : studentData) { // Iterate through each line
                String[] studentDetails = student.split(",", -1);
                // Trim to remove whitespace
                String name = studentDetails[0].trim();
                // Check if email is valid
                if (!studentDetails[1].trim().contains("@")) {
                    System.out.println("[ Error: Student CSV file contains invalid email. ]");
                    System.exit(4);
                }
                String userId = studentDetails[1].trim().split("@")[0];
                String faculty = studentDetails[2].trim();
                String password = studentDetails[3];
                int points = Integer.parseInt(studentDetails[4].trim());
                String campCommOf = studentDetails[5].trim();
                ArrayList<String> campsAttending = new ArrayList<String>();
                String[] campsAttendingArray = studentDetails[6].trim().split(";");
                for (String camp : campsAttendingArray) {
                    if (!camp.isEmpty()) {
                        campsAttending.add(camp);
                    }
                }
                this.addItem(new StudentAccount(name, userId, password, faculty, points, campCommOf, campsAttending));
            }
        }
        catch (Exception e) {
            System.out.println("[ Error: Student CSV file may be missing an entry or contain invalid entries. ]");
            System.exit(3);
        }
    }
    
}
