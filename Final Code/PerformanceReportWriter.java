import java.io.File;

/**
 * Interface that generalizes the writing of performance report.
 * Implement class to write performance report in different formats.
 * @version 1.0
 * @since 2023-11-24
 */
public interface PerformanceReportWriter {

    /**
     * Abstract method to write the data of the camp committee members and their respective points into a file.
     * Implement this method to write the performance report in different formats.
     * @param fileName The name of the file to be written to.
     * @param camp The camp object consisting of the camp committee members.
     * @param studentStorage The StudentStorage object that stores the students data.
     */
    public abstract void writeData(String fileName, Camp camp, StudentStorage studentStorage);
}
