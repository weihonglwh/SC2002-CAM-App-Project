/**
 * This class is used to print the different menus for the different users.
 * It is also used to print the camp information for the camp committee.
 * @version 1.0
 * @since 2023-11-24
 */
public class UiPrinter {

    /**
     * This method is used to print the login menu.
     */
    public static void printLoginInMenu() {
        System.out.println("==================================================================");
        System.out.println("> Please select who would you like to log in as <");
        System.out.println("1) Staff");
        System.out.println("2) Student");
        System.out.println("3) Exit app");
        System.out.println("==================================================================");
    }

    /**
     * This method is used to print the staff menu.
     */
    public static void printStaffMenu() {
        System.out.println("==================================================================");
        System.out.println("> What would you like to do today? <");
        System.out.println("1) Create Camps");
        System.out.println("2) Edit Camps");
        System.out.println("3) Delete Camps");
        System.out.println("4) View all Camps");
        System.out.println("5) View camps created by you");
        System.out.println("6) View Enquires by students");
        System.out.println("7) Reply to enquires by students");
        System.out.println("8) View Suggestions to camp");
        System.out.println("9) Approve/Reject Suggestions");
        System.out.println("10) View attendees of a camp");
        System.out.println("11) View camp committee of a camp");
        System.out.println("12) Generate performance report of camp committee for all camps");
        System.out.println("13) Generate an attendees list report for each camp created");
        System.out.println("14) Change password");
        System.out.println("15) Log out");
        System.out.println("==================================================================");
    }

    /**
     * This method is used to print the staff edit menu.
     */
    public static void printStaffEditMenu() {
        System.out.println("==================================================================");
        System.out.println("> Please select what you would like to edit <");
        System.out.println("1) Edit start date");
        System.out.println("2) Edit end date");
        System.out.println("3) Edit registration deadline");
        System.out.println("4) Edit location");
        System.out.println("5) Edit description");
        System.out.println("6) Toggle visibility");
        System.out.println("7) Exit edit page");
        System.out.println("==================================================================");
    }

    /**
     * This method is used to print the student menu.
     */
    public static void printStudentMenu() {
        System.out.println("==================================================================");
        System.out.println("> What would you like to do today? <");
        System.out.println("1) View your profile");
        System.out.println("2) View list of camp available");
        System.out.println("3) View the remaining slots for a camp");
        System.out.println("4) Register for camp");
        System.out.println("5) Register for camp committee");
        System.out.println("6) Submit enquiries about a camp");
        System.out.println("7) View enquiries made");
        System.out.println("8) Edit enquiries made");
        System.out.println("9) Delete enquires made");
        System.out.println("10) Withdraw from camp");
        System.out.println("11) Change password");
        System.out.println("12) Enter Camp Committee Mode");
        System.out.println("13) Log out");
        System.out.println("==================================================================");
    }

    /**
     * This method is used to print the camp committee menu.
     */
    public static void printCampCommitteeMenu(){
        System.out.println("==================================================================");
        System.out.println("> What would you like to do? <");
        System.out.println("1) View your profile");
        System.out.println("2) Submit suggestions for camps");
        System.out.println("3) View suggestion");
        System.out.println("4) Edit suggestion");
        System.out.println("5) Delete suggestion");
        System.out.println("6) View enquiries");
        System.out.println("7) Reply enquiries");
        System.out.println("8) Generate a report of the list of students attending");
        System.out.println("9) Print Camp Information");
        System.out.println("10) Return back to student Mode");
        System.out.println("==================================================================");
    }

    /**
     * This method is used to print the camp information for the camp committee.
     * @param camp The specific camp in which the information will be printed.
     */
    public static void printCampInformation(Camp camp){
        System.out.println("=================== Camp Information - Detailed ===================");
        System.out.println("Camp Name: " + camp.getName());
        System.out.println("Camp Start Date: " + DateUtility.dateToString(camp.getStartDate()));
        System.out.println("Camp End Date: " + DateUtility.dateToString(camp.getEndDate()));
        System.out.println("Camp Registration Deadline: "  + DateUtility.dateToString(camp.getRegDeadline()));
        System.out.println("Camp User group: " + camp.getUserGroup());
        System.out.println("Camp Location: " + camp.getLocation());
        System.out.println("Remaining slots: " + camp.getRemainingSlots());
        System.out.println("Remaining Camp Committee Slots: " + camp.getRemainingSlotsCampComm());
        System.out.println("Camp Description: " + camp.getDescription());
        System.out.println("Camp Staff In-Charge: " + camp.getStaffIC());
        System.out.println("Camp Visibility: " + camp.getVisibility());
        System.out.println("Camp Withdrawal List: "  + camp.getWithdrawalList());
        System.out.println("Camp Attendees: " + camp.getAttendees());
        System.out.println("Camp Committee Members: " + camp.getCampCommitteeMembers());
        System.out.println();
    }

    /**
     * This method is used to print camp information for a student.
     * @param campStudent The specific camp in which the information will be printed.
     */
    public static void printCampInfoForStudents(Camp campStudent){
        System.out.println("=================== Camp Information - Student ===================");
        System.out.println("Camp Name: "  + campStudent.getName());
        System.out.println("Camp Start Date: " + DateUtility.dateToString(campStudent.getStartDate()));
        System.out.println("Camp End Date: " + DateUtility.dateToString(campStudent.getEndDate()));
        System.out.println("Camp Registration Deadline: " + DateUtility.dateToString(campStudent.getRegDeadline()));
        System.out.println("Camp User Group: " + campStudent.getUserGroup());
        System.out.println("Camp Location: " + campStudent.getLocation());
        System.out.println("Remaining Slots: " + campStudent.getRemainingSlots());
        System.out.println("Remaining Camp Committee Slots " + campStudent.getRemainingSlotsCampComm());
        System.out.println("Camp Description: " + campStudent.getDescription());
        System.out.println();
    }

}
