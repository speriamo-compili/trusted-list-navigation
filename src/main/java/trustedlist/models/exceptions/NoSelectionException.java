package trustedlist.models.exceptions;

/**
 * Custom exception thrown when trying to access a selection where the needed selection have not been made
 */
public class NoSelectionException extends RuntimeException{
    /**
     * Create a new exception
     * @param errorMsg Custom message
     */
    public NoSelectionException(String errorMsg) {super(errorMsg);}
}
