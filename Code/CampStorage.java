import java.util.ArrayList;
import java.util.Collections;

public class CampStorage extends Storage{
    private ArrayList<Camp> camps;

    public CampStorage() {
        super();
        camps = new ArrayList<Camp>();
    }

    // Print all camps
    public void printData() {
        for (Camp c : camps) {
            System.out.println("Camp Name: " + c.getName());
            System.out.println("Camp Start Date: " + c.getStartDate());
            System.out.println("Camp End Date: " + c.getEndDate());
            System.out.println("Camp Registration Deadline: " + c.getRegDeadline());
            System.out.println("Camp User Group: " + c.getUserGroup());
            System.out.println("Camp Location: " + c.getLocation());
            System.out.println("Remaining Slots: " + c.getRemaindingSlots());
            System.out.println("Remaining Camp Committee Slots: " + c.getRemaindingSlotsCampComm());
            System.out.println("Camp Description: " + c.getDescription());
            System.out.println("Camp Staff In-Charge: " + c.getStaffIC());
            System.out.println("Camp Visibility: " + c.getVisibility());
            System.out.println("Camp Withdrawal List: " + c.getWithdrawalList());
            System.out.println("Camp Attendees: " + c.getAttendees());
            System.out.println("Camp Committee Members: " + c.getCampComms());
            System.out.println("");

        }
    }

    // Print camps created by a specific staff
    public void printData(StaffAccount staff) {
        for (Camp campStaff: camps){
            if (campStaff.getStaffIC().equals(staff.getUserId())){
                System.out.println("Camp Name: " + campStaff.getName());
                System.out.println("Camp Start Date: " + campStaff.getStartDate());
                System.out.println("Camp End Date: " + campStaff.getEndDate());
                System.out.println("Camp Registration Deadline: "  + campStaff.getRegDeadline());
                System.out.println("Camp User group: " + campStaff.getUserGroup());
                System.out.println("Camp Location: " + campStaff.getLocation());
                System.out.println("Remaining slots: " + campStaff.getRemaindingSlots());
                System.out.println("Remaining Camp Committee Slots: " + campStaff.getRemaindingSlotsCampComm());
                System.out.println("Camp Description: " + campStaff.getDescription());
                System.out.println("Camp Staff In-Charge: " + campStaff.getStaffIC());
                System.out.println("Camp Visibility: " + campStaff.getVisibility());
                System.out.println("Camp Withdrawal List: "  + campStaff.getWithdrawalList());
                System.out.println("Camp Attendees: " + campStaff.getAttendees());
                System.out.println("Camp Committee Members: " + campStaff.getCampComms());
                System.out.println();
            }
        }
    }

    // Print camps specific to student's faculty
    public void printData(StudentAccount student) {

        for (Camp campStudent: camps){
            if((campStudent.getUserGroup().equals(student.getFaculty()) || campStudent.getUserGroup().equals("NTU"))
                    && campStudent.getVisibility()){
                System.out.println("Camp Name: "  + campStudent.getName());
                System.out.println("Camp Start Date: " + campStudent.getStartDate());
                System.out.println("Camp End Date: " + campStudent.getEndDate());
                System.out.println("Camp Registration Deadline: " + campStudent.getRegDeadline());
                System.out.println("Camp User Group: " + campStudent.getUserGroup());
                System.out.println("Camp Location: " + campStudent.getLocation());
                System.out.println("Remaining Slots: " + campStudent.getRemaindingSlots());
                System.out.println("Remaining Camp Committee Slots " + campStudent.getRemaindingSlotsCampComm());
                System.out.println("Camp Description: " + campStudent.getDescription());
                System.out.println();
            }
        }
    }


    public ArrayList<Camp> getData() {
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
            sortCamps();
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

    public void sortCamps() {
        Collections.sort(camps);
    }
}
