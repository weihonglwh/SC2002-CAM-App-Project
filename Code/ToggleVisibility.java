public class ToggleVisibility implements EditOperationForStaff {
    public void perform(Camp camp) {
        if(camp.getAttendees().isEmpty() && camp.getCampComms().isEmpty()) {
            camp.setVisibility(!camp.getVisibility());
            if (camp.getVisibility()) {
                System.out.println("Camp is now visible");
            }
            else {
                System.out.println("Camp is now invisible");
            }
        }
        else{
            System.out.println("Camp cannot be made invisible as it has attendees or camp committee members");
        }
    }
    
}
