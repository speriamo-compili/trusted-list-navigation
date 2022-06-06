package trustedlist.models.exceptions;

/**
 * Custom exception thrown when trying load data from a source and something fails
 */
public class CantLoadDataException extends RuntimeException{
    /**
     * Create a new exception
     * @param errorMsg Custom message
     */
    public CantLoadDataException(String errorMsg){
        super(errorMsg);
    }
}
