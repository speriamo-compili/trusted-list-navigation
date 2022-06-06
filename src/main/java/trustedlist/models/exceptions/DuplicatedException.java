package trustedlist.models.exceptions;

/**
 * Custom exception thrown when trying to add an object to a list that already contains it
 */
public class DuplicatedException extends RuntimeException{
    /**
     * Create a new exception
     * @param errorMsg Custom message
     */
    public DuplicatedException(String errorMsg) {super(errorMsg);}
}
