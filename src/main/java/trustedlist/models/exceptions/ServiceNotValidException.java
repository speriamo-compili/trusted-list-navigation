package trustedlist.models.exceptions;

/**
 * Custom exception thrown when trying to create a service with wrong arguments
 */
public class ServiceNotValidException extends RuntimeException{
    /**
     * Create a new exception
     * @param errorMsg Custom message
     */
    public ServiceNotValidException(String errorMsg){
        super(errorMsg);
    }
}
