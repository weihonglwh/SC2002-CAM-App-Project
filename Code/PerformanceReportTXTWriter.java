import java.io.*;
import java.util.ArrayList;

public class PerformanceReportTXTWriter extends PerformanceReportWriter{
    public static boolean fileExists(String fileName){
        File f = new File(fileName);
        return f.exists() && !f.isDirectory();
    }
    public void writeData(String fileName, Camp camp, StudentStorage studentStorage) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println("===============================================");
            printWriter.println("Camp Name: " + camp.getName());
            printWriter.println("===============================================");
            printWriter.printf("| %-20s | %-20s | %n", "Committee Member", "Points");
            printWriter.println("===============================================");
            ArrayList<String> campComms = camp.getCampComms();
            if (campComms.isEmpty()) {
                printWriter.printf("| %-43s | %n", "No committee members");
            }
            for (String committeeMember : campComms) {
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
            System.out.println("Unable to write to file '" + fileName + "'");
        }
    }
}
