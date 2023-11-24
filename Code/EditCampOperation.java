/**
 * Interface for edit operations when editing a camp.
 * @version 1.0
 * @since 2023-11-24
 */
public interface EditCampOperation {
    /**
     * Abstract method for performing edit operation.
     * To be implemented by classes that realize this interface.
     * @param camp The camp object to be edited.
     */
    public abstract void perform(Camp camp);
}
