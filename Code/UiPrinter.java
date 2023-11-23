public class UiPrinter {
    // Method to print the log-in menu
    public static void printLoginInMenu() {
        System.out.println("===================================================================");
        System.out.println("Please select who would you like to log in as: ");
        System.out.println("1) Staff");
        System.out.println("2) Student");
        System.out.println("3) Exit app");
        System.out.println("===================================================================");
    }

    // Method to print the staff menu
    public static void printStaffMenu() {
        System.out.println("===================================================================");
        System.out.println("What would you like to do today?");
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
        System.out.println("===================================================================");
    }

    // Method to print the staff edit menu
    public static void printStaffEditMenu() {
        System.out.println("===================================================================");
        System.out.println("Please select what you would like to edit");
        System.out.println("1) Edit start date");
        System.out.println("2) Edit end date");
        System.out.println("3) Edit registration deadline");
        System.out.println("4) Edit location");
        System.out.println("5) Edit description");
        System.out.println("6) Toggle visibility");
        System.out.println("7) Exit edit page");
        System.out.println("===================================================================");
    }

    // Method to print the student menu
    public static void printStudentMenu() {
        System.out.println("===================================================================");
        System.out.println("What would you like to do today");
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
        System.out.println("===================================================================");
    }

    // Method to print the camp committee menu
    public static void printCampCommitteeMenu(){
        System.out.println("===================================================================");
        System.out.println("What would you like to do?");
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
        System.out.println("===================================================================");
    }

    public static void printCampInformation(Camp studentCampCommObj){
        System.out.println("=============== Camp Information - Committee View =================");
        System.out.println("Camp Name: " + studentCampCommObj.getName());
        System.out.println("Camp Start Date: " + DateUtility.dateToString(studentCampCommObj.getStartDate()));
        System.out.println("Camp End Date: " + DateUtility.dateToString(studentCampCommObj.getEndDate()));
        System.out.println("Camp Registration Deadline: "  + DateUtility.dateToString(studentCampCommObj.getRegDeadline()));
        System.out.println("Camp User group: " + studentCampCommObj.getUserGroup());
        System.out.println("Camp Location: " + studentCampCommObj.getLocation());
        System.out.println("Remaining slots: " + studentCampCommObj.getRemaindingSlots());
        System.out.println("Remaining Camp Committee Slots: " + studentCampCommObj.getRemaindingSlotsCampComm());
        System.out.println("Camp Description: " + studentCampCommObj.getDescription());
        System.out.println("Camp Staff In-Charge: " + studentCampCommObj.getStaffIC());
        System.out.println("Camp Visibility: " + studentCampCommObj.getVisibility());
        System.out.println("Camp Withdrawal List: "  + studentCampCommObj.getWithdrawalList());
        System.out.println("Camp Attendees: " + studentCampCommObj.getAttendees());
        System.out.println("Camp Committee Members: " + studentCampCommObj.getCampComms());
    }
}
