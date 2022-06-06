package trustedlist.models.exceptions;

/**
 * Custom exception thrown when trying create a provider with wrong arguments
 */
public class ProviderNotValidException extends RuntimeException{
    /**
     * Create a new exception
     * @param errorMsg Custom message
     */
    public ProviderNotValidException(String errorMsg){
        super(errorMsg);
    }
}
