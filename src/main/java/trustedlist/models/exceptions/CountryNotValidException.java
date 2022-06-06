package trustedlist.models.exceptions;

/**
 * Custom exception thrown when trying to create a country with an invalid code
 */
public class CountryNotValidException extends RuntimeException{

    /**
     * Create a new exception
     * @param errorMsg Custom message
     */
    public CountryNotValidException(String errorMsg) {super(errorMsg);}
}
