import java.util.Date;
import java.util.ArrayList;

public class Camp {
    private String name;
    private Date startDate;
    private Date endDate;
    private Date regDeadline;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int campCommSlots;
    private String description;
    private String staffIC;
    private ArrayList<String> attendees;
    private ArrayList<String> campComms;
    private boolean visibility;
    private ArrayList<String> withdrawalList;

    public Camp(String name, Date startDate, Date endDate, Date regDeadline, String userGroup,
                String location, int totalSlots, int campCommSlots, String description,
                String staffIC, boolean visibility) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regDeadline = regDeadline;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommSlots = campCommSlots;
        this.description = description;
        this.staffIC = staffIC;
        this.attendees = new ArrayList<>();
        this.campComms = new ArrayList<>();
        this.visibility = visibility;
        this.withdrawalList = new ArrayList<>();
    }

    public Camp(String name, Date startDate, Date endDate, Date regDeadline, String userGroup,
                String location, int totalSlots, int campCommSlots, String description,
                String staffIC, ArrayList<String> attendees, ArrayList<String> campComms, boolean visibility,
                ArrayList<String> withdrawalList) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regDeadline = regDeadline;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommSlots = campCommSlots;
        this.description = description;
        this.staffIC = staffIC;
        this.attendees = new ArrayList<String>();
        this.attendees.addAll(attendees);
        this.campComms = new ArrayList<String>();
        this.campComms.addAll(campComms);
        this.visibility = visibility;
        this.withdrawalList = new ArrayList<String>();
        this.withdrawalList.addAll(withdrawalList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRegDeadline(){
        return regDeadline;
    }

    public void setRegDeadline(Date regDeadline){
        this.regDeadline = regDeadline;
    }

    public String getUserGroup(){
        return userGroup;
    }

    public void setUserGroup(String userGroup){
        this.userGroup = userGroup;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public int getTotalSlots(){
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots){
        this.totalSlots = totalSlots;
    }

    public int getCampCommSlots(){
        return campCommSlots;
    }

    public void setCampCommSlots(int campCommSlots){
        this.campCommSlots = campCommSlots;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getStaffIC(){
        return staffIC;
    }

    public void setStaffIC(String staffIC){
        this.staffIC = staffIC;
    }

    public ArrayList<String> getAttendees(){
        return attendees;
    }

    public void addAttendees(String attendee){
        attendees.add(attendee);
    }

    public void removeAttendees(String attendee){
        int index = attendees.indexOf(attendee);
        attendees.remove(index);
    }

    public ArrayList<String> getCampComms(){
        return campComms;
    }

    public void addCampComms(String campComm){
        campComms.add(campComm);
    }

    public boolean getVisibility(){
        return visibility;
    }

    public int getRemindingSlots(){
        if (attendees.isEmpty()){
            return totalSlots;
        }
        return totalSlots - attendees.size();
    }

    public void setVisibility(boolean visibility){
        this.visibility = visibility;
    }

    public ArrayList<String> getWithdrawalList(){
        return withdrawalList;
    }

    public void addWithdrawalList(String student_withdraw){
        withdrawalList.add(student_withdraw);
    }

}
