import java.util.ArrayList;

public abstract class Storage
{
    public abstract ArrayList getData();
    public abstract Object getData(String s);
    public abstract void addItem(Object o);

    public abstract void populateData(CSVReader reader);
}