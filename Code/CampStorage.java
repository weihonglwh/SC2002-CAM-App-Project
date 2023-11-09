import java.util.ArrayList;

public class CampStorage extends Storage{
    private ArrayList<Camp> camps;

    public CampStorage() {
        super();
        camps = new ArrayList<Camp>();
    }

    public void printData() {
        for (Camp c : camps) {
            System.out.println("the name of the camp is " + c.getName());
            System.out.println("the startdate for the camp is " + c.getStartDate());
            System.out.println("the enddate for the camp is " + c.getEndDate());
            System.out.println("the regdeadline for the camp is " + c.getRegDeadline());
            System.out.println("the usergroup for the camp is " + c.getUserGroup());
            System.out.println("the location for the camp is " + c.getLocation());
            System.out.println("the total slots for the camp is " + c.getTotalSlots());
            System.out.println("the campcomm slots for the camp is " + c.getCampCommSlots());
            System.out.println("the description of the camp is " + c.getDescription());
            System.out.println("the staffIC for the camp is " + c.getStaffIC());
            System.out.println("the visibility for the camp is " + c.getVisibility());
            System.out.println("the withdrawal list for the camp is " + c.getWithdrawalList());
            System.out.println("the attendees for the camp is " + c.getAttendees());
            System.out.println("the campcomms for the camp is " + c.getCampComms());
            System.out.println("");

        }
    }

    public void printData(Camp c) {
            System.out.println("the name of the camp is " + c.getName());
            System.out.println("the startdate for the camp is " + c.getStartDate());
            System.out.println("the enddate for the camp is " + c.getEndDate());
            System.out.println("the regdeadline for the camp is " + c.getRegDeadline());
            System.out.println("the usergroup for the camp is " + c.getUserGroup());
            System.out.println("the location for the camp is " + c.getLocation());
            System.out.println("the total slots for the camp is " + c.getTotalSlots());
            System.out.println("the campcomm slots for the camp is " + c.getCampCommSlots());
            System.out.println("the description of the camp is " + c.getDescription());
            System.out.println("the staffIC for the camp is " + c.getStaffIC());
            System.out.println("the visibility for the camp is " + c.getVisibility());
            System.out.println("the withdrawal list for the camp is " + c.getWithdrawalList());
            System.out.println("the attendees for the camp is " + c.getAttendees());
            System.out.println("the campcomms for the camp is " + c.getCampComms());
            System.out.println("");

    }

    public ArrayList getData() {

        return camps;
    }

    public Camp getData(String s) {
        for (Camp c : camps) {
            if (c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }

    public void addItem(Object o) {
        if (o instanceof Camp){
            camps.add((Camp)o);
        }
        else{
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }

    public void removeItem(Object o) {
        if (o instanceof Camp){
            camps.remove((Camp)o);
        }
        else{
            System.out.println("Invalid object type");
            System.exit(0);
        }
    }
    
}
