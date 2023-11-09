import java.util.ArrayList;

/*
    Col 0: Name
    Col 1: Email
    Col 2: Faculty
    Col 3: Password
*/

public class CSVStudentReader extends CSVReader{
    public void populateStorage(Storage s) {
        if (!(s instanceof StudentStorage)) {
            System.out.println("Error: Storage is not a StudentStorage.");
            System.exit(4);
        }
        ArrayList<String> studentData = performRead("student.csv");
        try {
            for (String student : studentData) { // Iterate through each line
                String[] studentDetails = student.split(",");
                // Trim to remove whitespace
                String name = studentDetails[0].trim();
                String userId = studentDetails[1].trim().split("@")[0];
                String faculty = studentDetails[2].trim();
                String password = studentDetails[3];
                //System.out.println("Adding student: " + name + " " + userId + " " + faculty + " " + password);
                s.addItem(new StudentAccount(name, userId, password, faculty));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Student CSV file may be missing an entry.");
            System.exit(3);
        }
    }
}
