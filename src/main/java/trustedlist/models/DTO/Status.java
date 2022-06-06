package trustedlist.models.DTO;

/**
 * Enum of possible service's status
 */
public enum Status {
    /**
     * The service has been interrupted and can't be used anymore
     */
    WITHDRAWN,

    /**
     * The service is available and usable
     */
    GRANTED,

    /**
     * Service recognised at a national level, available and usable
     */
    RECOGNISEDATNATIONALLEVEL,

    /**
     * Service deprecated at a national level, can't be used anymore
     */
    DEPRECATEDATNATIONALLEVEL;


    /**
     * Get a textual description of the current status
     * @return Textual description of status
     */
    public String getDescription(){
        switch (this){
            case WITHDRAWN -> {
                return "Withdrawn";
            }
            case GRANTED -> {
                return "Granted";
            }
            case RECOGNISEDATNATIONALLEVEL -> {
                return "Recognised at a national level";
            }
            case DEPRECATEDATNATIONALLEVEL -> {
                return "Deprecated at a national level";
            }
            default -> throw new RuntimeException("Unexpected status of service.");
        }
    }

}
