import java.util.ArrayList;

/**
 * Storage is an abstract class that stores objects.
 * It contains methods to get all data, get a specific object, add items, and populate data from a CSV file.
 * @version 1.0
 * @since 2023-11-24
 */
public interface Storage
{
    /**
     * To get a list of all objects.
     */
    public abstract ArrayList getData();

    /**
     * To get a specific object within the ArrayList
     */
    public abstract Object getData(String s);

    /**
     * To add an object to the list.
     */
    public abstract void addItem(Object o);

    /**
     * To populate data from a CSV file.
     */
    public abstract void populateData(CSVReader reader);
}

