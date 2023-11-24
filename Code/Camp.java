import java.util.Date;
import java.util.ArrayList;

/**
 * Camp class that contains all the information of a camp
 * @version 1.0
 * @since 2023-11-24
 */
public class Camp implements Comparable {
    /**
     * Name of the camp.
     */
    private String name;

    /**
     * Start date of the camp.
     */
    private Date startDate;

    /**
     * End date of the camp.
     */
    private Date endDate;

    /**
     * Registration deadline of the camp.
     */
    private Date regDeadLine;

    /**
     * User group of the camp.
     */
    private String userGroup;

    /**
     * Location of the camp.
     */
    private String location;

    /**
     * Total slots of the camp.
     */
    private int totalSlots;

    /**
     * Number of camp committee slots of the camp.
     */
    private int campCommSlots;

    /**
     * Description of the camp.
     */
    private String description;

    /**
     * Staff in charge of the camp.
     */
    private String staffIC;

    /**
     * List of attendees of the camp.
     */
    private ArrayList<String> attendees;

    /**
     * List of camp committee of the camp.
     */
    private ArrayList<String> campCommitteeMembers;

    /**
     * Visibility of the camp.
     */
    private boolean visibility;

    /**
     * List of students who withdraw from the camp.
     */
    private ArrayList<String> withdrawalList;

    /**
     * Creates a new camp with the specified name, start date, end date, registration deadline, user group,
     * location, total slots, number of camp committee slots, description, staff in charge and visibility.
     * @param name Name of the camp.
     * @param startDate Start date of the camp.
     * @param endDate End date of the camp.
     * @param regDeadline Registration deadline of the camp.
     * @param userGroup User group of the camp.
     * @param location Location of the camp.
     * @param totalSlots Total slots of the camp.
     * @param campCommSlots Number of camp committee slots of the camp≥
     * @param description Description of the camp.
     * @param staffIC Staff in charge of the camp.
     * @param visibility Visibility of the camp.
     */
    public Camp(String name, Date startDate, Date endDate, Date regDeadline, String userGroup,
                String location, int totalSlots, int campCommSlots, String description,
                String staffIC, boolean visibility) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regDeadLine = regDeadline;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommSlots = campCommSlots;
        this.description = description;
        this.staffIC = staffIC;
        this.attendees = new ArrayList<>();
        this.campCommitteeMembers = new ArrayList<>();
        this.visibility = visibility;
        this.withdrawalList = new ArrayList<>();
    }

    /**
     * Creates a new camp with the specified name, start date, end date, registration deadline, user group,
     * location, total slots, number of camp committee slots, description, staff in charge, list of attendees,
     * list of camp committee, visibility and list of students who withdraw from the camp.
     * @param name Name of the camp.
     * @param startDate Start date of the camp.
     * @param endDate End date of the camp.
     * @param regDeadline Registration deadline of the camp.
     * @param userGroup User group of the camp.
     * @param location Location of the camp.
     * @param totalSlots Total slots of the camp.
     * @param campCommSlots Number of camp committee slots of the camp.
     * @param description Description of the camp.
     * @param staffIC Staff in charge of the camp.
     * @param attendees List of attendees of the camp.
     * @param campCommitteeMembers List of camp committee of the camp.
     * @param visibility Visibility of the camp.
     * @param withdrawalList List of students who withdraw from the camp.
     */
    public Camp(String name, Date startDate, Date endDate, Date regDeadline, String userGroup,
                String location, int totalSlots, int campCommSlots, String description,
                String staffIC, ArrayList<String> attendees, ArrayList<String> campCommitteeMembers, boolean visibility,
                ArrayList<String> withdrawalList) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regDeadLine = regDeadline;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommSlots = campCommSlots;
        this.description = description;
        this.staffIC = staffIC;
        this.attendees = new ArrayList<String>();
        this.attendees.addAll(attendees);
        this.campCommitteeMembers = new ArrayList<String>();
        this.campCommitteeMembers.addAll(campCommitteeMembers);
        this.visibility = visibility;
        this.withdrawalList = new ArrayList<String>();
        this.withdrawalList.addAll(withdrawalList);
    }

    /**
     * Returns the name of the camp.
     * @return Name of the camp.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the camp.
     * @param name Name of the camp.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the start date of the camp.
     * @return StartDate start date of the camp.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the camp.
     * @param startDate Start date of the camp.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the camp.
     * @return End date of the camp.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the camp.
     * @param endDate End date of the camp.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the registration deadline of the camp.
     * @return Registration deadline of the camp.
     */
    public Date getRegDeadline(){
        return regDeadLine;
    }

    /**
     * Sets the registration deadline of the camp.
     * @param regDeadLine Registration deadline of the camp.
     */
    public void setRegDeadline(Date regDeadLine){
        this.regDeadLine = regDeadLine;
    }

    /**
     * Returns the target user group of the camp.
     * @return Group of students that the camp is open to.
     */
    public String getUserGroup(){
        return userGroup;
    }

    /**
     * Returns the location of the camp.
     * @return Location of the camp.
     */
    public String getLocation(){
        return location;
    }

    /**
     * Sets the location of the camp.
     * @param location Location of the camp.
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * Returns the total slots of the camp.
     * @return Total slots of the camp.
     */
    public int getTotalSlots(){
        return totalSlots;
    }

    /**
     * Returns the number of camp committee slots of the camp.
     * @return Number of camp committee slots of the camp.
     */
    public int getCampCommSlots(){
        return campCommSlots;
    }

    /**
     * Returns the description of the camp.
     * @return Description of the camp.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets the description of the camp.
     * @param description Description of the camp.
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Returns the staff in charge of the camp.
     * @return Staff in charge of the camp.
     */
    public String getStaffIC(){
        return staffIC;
    }

    /**
     * Sets the staff in charge of the camp.
     * @param staffIC Staff in charge of the camp.
     */
    public void setStaffIC(String staffIC){
        this.staffIC = staffIC;
    }

    /**
     * Returns the list of attendees of the camp.
     * @return List of attendees of the camp.
     */
    public ArrayList<String> getAttendees(){
        return attendees;
    }

    /**
     * Add attendee to the list of attendees of the camp.
     * @param attendee Name of the attendee to be added.
     */
    public void addAttendees(String attendee){
        attendees.add(attendee);
    }

    /**
     * Remove attendee from the list of attendees of the camp.
     * @param attendee Name of the attendee to be removed.
     */
    public void removeAttendees(String attendee){
        attendees.remove(attendee);
        addWithdrawalList(attendee);
    }

    /**
     * Returns the list of camp committee of the camp.
     * @return List of camp committee of the camp
     */
    public ArrayList<String> getCampCommitteeMembers(){
        return campCommitteeMembers;
    }

    /**
     * Add camp committee to the list of camp committee of the camp.
     * @param campComm Name of the camp committee to be added.
     */
    public void addCampCommitteeMember(String campComm){
        campCommitteeMembers.add(campComm);
    }

    /**
     * To get the visibility of the camp.
     * @return If the camp is open for students to sign up.
     */
    public boolean getVisibility(){
        return visibility;
    }

    /**
     * Returns the number of remaining slots of the camp.
     * @return Number of remaining slots of the camp.
     */
    public int getRemainingSlots(){
        int slotAvailable;
        if (attendees.isEmpty()){
            slotAvailable = totalSlots - getCampCommSlots();
            return slotAvailable;
        }
        slotAvailable = totalSlots - attendees.size()- getCampCommSlots();
        return slotAvailable;
    }

    /**
     * Returns the number of remaining slots of the camp committee of the camp.
     * @return Number of remaining slots of the camp committee of the camp.
     */
    public int getRemainingSlotsCampComm(){
        int campCommSlotAvailable;
        if(campCommitteeMembers.isEmpty()){
            campCommSlotAvailable = campCommSlots;
            return campCommSlotAvailable;
        }
        campCommSlotAvailable = campCommSlots - campCommitteeMembers.size();
        return campCommSlotAvailable;
    }

    /**
     * Sets the visibility of the camp.
     * @param visibility If the camp is open to students.
     */
    public void setVisibility(boolean visibility){
        this.visibility = visibility;
    }

    /**
     * Returns the list of withdrawal of the camp.
     * @return List of withdrawal of the camp.
     */
    public ArrayList<String> getWithdrawalList(){
        return withdrawalList;
    }

    /**
     * Add student to the list of withdrawal of the camp.
     * @param studentWithdrawName Name of the student to be added to the withdrawal list.
     */
    public void addWithdrawalList(String studentWithdrawName){
        withdrawalList.add(studentWithdrawName);
    }

    /**
     * To print the list of attendees.
     */
    public void printAttendees(){
        if (attendees.isEmpty()){
            System.out.println("[ There is currently no attendees for this camp ]");
        }
        else{
            for (String s : attendees)
            {
                System.out.println(s);
            }
        }
    }

    /**
     * Print the list of camp committee of the camp.
     */
    public void printCampComm(){
        if (campCommitteeMembers.isEmpty()){
            System.out.println("[ There are no camp committee members ]");
        }
        else{
            for (String campCommittee : campCommitteeMembers){
                System.out.println(campCommittee);
            }
        }
    }

    /**
     * Method for staff to edit camp details.
     * @param op Operation to be performed.
     */
    public void editCamp(EditCampOperation op) {
        op.perform(this);
    }

    /**
     * Implemented method from Comparable interface.
     * Used to compare 2 objects of Camp class by name to determine sorting order.
     * @param object Object to be compared.
     */
    public int compareTo(Object object) {
        if (!(object instanceof Camp)) {
            return 0;
        }
        Camp camp = (Camp) object;
        return name.toUpperCase().compareTo(camp.getName().toUpperCase()) <= 0 ? -1 : 1;
    }
}
