import java.util.ArrayList;

public abstract class Storage
{   
    public abstract void printData();
    public abstract ArrayList getData();
    public abstract Object getData(String s);
    public abstract void addItem(Object o);
}