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
    
}
