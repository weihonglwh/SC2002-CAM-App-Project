import java.util.ArrayList;

/*
    Col 0: Name
    Col 1: Email
    Col 2: Faculty
    Col 3: Password
    Col 4: Points
    Col 5: Camp Comm Of
    Col 6: Camps Attending
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
                String[] studentDetails = student.split(",", -1);
                // Trim to remove whitespace
                String name = studentDetails[0].trim();
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
                //System.out.println("Adding student: " + name + " " + userId + " " + faculty + " " + password + " " + points + " " + campCommOf + " " + campsAttending);
                s.addItem(new StudentAccount(name, userId, password, faculty, points, campCommOf, campsAttending));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Student CSV file may be missing an entry.");
            System.exit(3);
        }
    }
}
