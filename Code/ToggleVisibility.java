public class ToggleVisibility implements EditOperation {
    public void perform(Camp camp) {
        if(camp.getAttendees() == null && camp.getCampComms()==null){
            camp.setVisibility(!camp.getVisibility());
        }
    }

}
