import java.io.*;
import java.util.ArrayList;

/**
 * Class to write all students in the system to a CSV file
 * @version 1.0
 * @since 2023-11-24
 * @see DataWriter
 */
public class CSVStudentWriter implements DataWriter{
    /**
     * Writes all students in the system to a CSV file.
     * Overwrites existing data in the CSV file.
     * @param fileName Name of the file to be written to.
     *                 Should be "student.csv" by default.
     * @param storage Storage that contains the student data to be written to the CSV file.
     *                Should be a StudentStorage object.
     */
    public void performWrite(String fileName, Storage storage) {
        // Check if storage is a StudentStorage object
        if (!(storage instanceof StudentStorage)) {
            System.out.println("[ Error: Storage is not a StudentStorage. ]");
            System.exit(4);
        }
        try {
            String header = CSVReader.getHeader(fileName);
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            // Write the header first
            if (!header.isBlank()) {
                bw.write(header);
                bw.newLine();
            }
            // Write the data
            ArrayList<StudentAccount> studentList = storage.getData();
            for (StudentAccount student : studentList) {
                ArrayList<String> campsAttending = student.getCampsRegistered();
                StringBuilder campsAttendingString = new StringBuilder();
                for (String camp : campsAttending) {
                    campsAttendingString.append(camp).append(";");
                }
                bw.write(student.getName() + "," + student.getUserId()+"@e.ntu.edu.sg" + "," +
                 student.getFaculty() + "," + student.getPassword() + "," + student.getPoints() + "," +
                 student.getCampCommOf() + "," + campsAttendingString);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("[ Error: Student CSV file could not be written. ]");
            System.exit(2);
        }
    }
}
