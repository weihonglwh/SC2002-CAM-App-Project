public class ToggleVisibility implements EditOperationForStaff {
    public void perform(Camp camp) {
        if(camp.getAttendees() == null && camp.getCampComms()==null){
            camp.setVisibility(!camp.getVisibility());
            System.out.println("Visibility changed successfully");
        }
        else{
            System.out.println("Camp cannot be made invisible as it has attendees or camp committee members");
        }
    }
    
}
