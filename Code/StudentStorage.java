import java.util.ArrayList;

public class StudentStorage extends Storage {
    private ArrayList<StudentAccount> students;
    
    public StudentStorage() {
        super();
        students = new ArrayList<StudentAccount>();
    }

    public void printData() {
        for (StudentAccount s : students) {
            System.out.println("The name of the student is " + s.getName());
            System.out.println("The username of the student is " + s.getUserId());
            System.out.println("The password of the student is " + s.getPassword());
            System.out.println("The faculty is " + s.getFaculty());
        }
    }


    public ArrayList getData() {
        return students;
    }
    
    public StudentAccount getData(String s) {
        for (StudentAccount st : students) {
            if (st.getUserId().equals(s)) {
                return st;
            }
        }
        return null;
    }
    
    public void addItem(Object o) {
        if (o instanceof StudentAccount)
        {
            students.add((StudentAccount)o);
        }
        else
        {
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }

    public void populateData(CSVReader reader) {
        ArrayList<String> studentData = reader.performRead("student.csv");
        try {
            for (String student : studentData) { // Iterate through each line
                String[] studentDetails = student.split(",", -1);
                // Trim to remove whitespace
                String name = studentDetails[0].trim();
                // Check if email is valid
                if (!studentDetails[1].trim().contains("@")) {
                    System.out.println("Error: Student CSV file contains invalid email.");
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
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Student CSV file may be missing an entry.");
            System.exit(3);
        }
    }
    
}
