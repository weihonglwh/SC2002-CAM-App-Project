import java.io.*;
import java.util.ArrayList;

public class CSVStudentWriter implements DataWriter{
    public void performWrite(String file, Storage s) {
        if (!(s instanceof StudentStorage)) {
            System.out.println("Error: Storage is not a StudentStorage.");
            System.exit(4);
        }
        try {
            String header = CSVReader.getHeader(file);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write the header first
            bw.write(header);
            bw.newLine();
            // Write the data
            ArrayList<StudentAccount> studentList = s.getData();
            for (StudentAccount student : studentList) {
                bw.write(student.getName() + "," + student.getUserId()+"@e.ntu.edu.sg" + "," +
                 student.getFaculty() + "," + student.getPassword());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error: Student CSV file could not be written.");
            System.exit(2);
        }
    }
}
