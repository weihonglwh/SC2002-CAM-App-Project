import java.io.*;
import java.util.ArrayList;

/**
 * Class that writes the performance report of camp committee members into a TXT file.
 * Implements the abstract method writeData() from PerformanceReportWriter.
 * @version 1.0
 * @since 2023-11-24
 * @see PerformanceReportWriter
 */
public class PerformanceReportTXTWriter implements PerformanceReportWriter{
    /**
     * Writes the data of the camp committee members and their respective points into a TXT file.
     * @param fileName The name of the TXT file to be written to.
     * @param camp The camp object consisting of the camp committee members.
     * @param studentStorage The StudentStorage object that stores the student data.
     */
    public void writeData(String fileName, Camp camp, StudentStorage studentStorage) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            // Write the camp name and columns
            printWriter.println("===============================================");
            printWriter.println("Camp Name: " + camp.getName());
            printWriter.println("===============================================");
            printWriter.printf("| %-20s | %-20s | %n", "Committee Member", "Points");
            printWriter.println("===============================================");
            ArrayList<String> campCommitteeMembers = camp.getCampCommitteeMembers();
            // Check if there are camp committee members
            if (campCommitteeMembers.isEmpty()) {
                printWriter.printf("| %-43s | %n", "No committee members");
            }
            for (String committeeMember : campCommitteeMembers) {
                StudentAccount committeeMemberAccount = studentStorage.getData(committeeMember);
                String nameAndId = committeeMemberAccount.getName() + " (" + committeeMemberAccount.getUserId() + ")";
                printWriter.printf("| %-20s | %-20s | %n", nameAndId, committeeMemberAccount.getPoints());
            }
            printWriter.println("===============================================");
            printWriter.println();
            printWriter.close();
            bufferedWriter.close();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("[ Unable to write to file '" + fileName + "' ]");
        }
    }
}
