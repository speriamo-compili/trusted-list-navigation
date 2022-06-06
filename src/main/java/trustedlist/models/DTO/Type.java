package trustedlist.models.DTO;

/**
 *  Enum of possible service's type
 */
public enum Type {

    CertESeal,
    CertESig,
    CertUndefined,
    GenESig,
    NonRegulatory,
    QCertESeal,
    QCertESig,
    QeRDS,
    QPresQESeal,
    QPresQESig,
    QTimestamp,
    QValQESeal,
    QValQESig,
    QWAC,
    Timestamp,
    WAC;

    /**
     * Get a textual description of the current type
     * @return Textual description of type
     */
    public String getDescription(){
        switch (this){
            case CertESeal -> {
                return "Certificate for electronic seal";
            }
            case CertESig -> {
                return "Certificate for electronic signature";
            }
            case CertUndefined -> {
                return "Undefined type";
            }
            case GenESig -> {
                return "Generation service for electronic signature";
            }
            case NonRegulatory -> {
                return "Non-regulatory, nationally defined trust service";
            }
            case QCertESeal -> {
                return "Qualified certificate for electronic seal";
            }
            case QCertESig -> {
                return "Qualified certificate for electronic signature";
            }
            case QeRDS -> {
                return "Qualified electronic registered delivery service";
            }
            case QPresQESeal -> {
                return "Qualified preservation service for qualified electronic seal";
            }
            case QPresQESig -> {
                return "Qualified preservation service for qualified electronic signature";
            }
            case QTimestamp -> {
                return "Qualified time stamp";
            }
            case QValQESeal -> {
                return "Qualified validation service for qualified electronic seal";
            }
            case QValQESig -> {
                return "Qualified validation service for qualified electronic signature";
            }
            case QWAC -> {
                return "Qualified certificate for website authentication";
            }
            case Timestamp -> {
                return "Time stamp service";
            }
            case WAC -> {
                return "Certificate for website authentication";
            }
            default -> throw new RuntimeException("Unexpected type of service.");
        }
    }

}
