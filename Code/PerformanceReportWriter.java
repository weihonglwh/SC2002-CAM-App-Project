import java.io.File;

public abstract class PerformanceReportWriter {
    public static boolean fileExists(String fileName){
        File f = new File(fileName);
        return f.exists() && !f.isDirectory();
    }
    public abstract void writeData(String fileName, Camp camp, StudentStorage studentStorage);
}
