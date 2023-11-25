/**
 * This class is an implementation of the EditOperationForStaff interface.
 * It is used to toggle the visibility of the camp.
 * @version 1.0
 * @since 2023-11-24
 * @see EditCampOperation
 */
public class ToggleVisibility implements EditCampOperation {

    /**
     * This method is used to toggle the visibility of the camp.
     * @param camp The camp in which the visibility of will be toggled.
     */
    public void perform(Camp camp) {

        // Checking if the camp has attendees or camp committee members
        // If it has attendees or camp committee members, it cannot be made invisible
        if(camp.getAttendees().isEmpty() && camp.getCampCommitteeMembers().isEmpty()) {

            // Toggling the visibility of the camp
            camp.setVisibility(!camp.getVisibility());

            // Printing the appropriate message based on the visibility of the camp
            if (camp.getVisibility()) {
                System.out.println("[ Camp is now visible ]");
            }
            else {
                System.out.println("[ Camp is now invisible ]");
            }
        }
        else{
            System.out.println("[ Camp cannot be made invisible as it has attendees or camp committee members ]");
        }
    }
    
}
