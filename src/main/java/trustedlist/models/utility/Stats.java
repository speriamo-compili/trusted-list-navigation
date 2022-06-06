package trustedlist.models.utility;

import trustedlist.models.DTO.Country;
import trustedlist.models.DTO.Service;
import trustedlist.models.DTO.Status;
import trustedlist.models.DTO.Type;
import trustedlist.models.engine.TrustedListNavigation;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to count services, types and status
 */
public class Stats {
    private Stats(){}

    /**
     * Used to get a map that associates a type to the number of services of that
     * type in the given country
     * @param country Country where to count types
     * @return Map Type &#8594; Service count
     */
    public static Map<Type, Integer> getServicesPerType(Country country){
        Map<Type, Integer> map = new HashMap<>();
        for(Service s : country.getServices())
            for(Type t : s.getTypes())
                map.merge(t, 1, Integer::sum); // Put 1 if key does not exist, increment value otherwise
        return map;
    }


    /**
     * Used to get a map that associates a status to the number of services with that status
     * in the given country
     * @param country Country where to count statuses
     * @return Map Type &#8594; Service count
     */
    public static Map<Status, Integer> getServicesPerStatus(Country country) {
        Map<Status, Integer> map = new HashMap<>();
        for(Service s : country.getServices())
            map.merge(s.getStatus(), 1, Integer::sum); // Put 1 if key does not exist, increment value otherwise
        return map;
    }

    /**
     * Used to count services in the give country
     * @param country Country where to count services
     * @return Number of service in the country
     */
    public static int getCountryServicesCount(Country country){
        return country.getServices().size();
    }

    /**
     * Used to count providers in the give country
     * @param country Country where to count providers
     * @return Number of providers in the country
     */
    public static int getCountryProvidersCount(Country country){
        return country.getProviders().size();
    }

    /**
     * Used to count the total of all services
     * @return Number of total services
     */
    public static int getServicesCount(){
        return TrustedListNavigation.getInstance().getAllServices().size();
    }

    /**
     * Used to count the total of all providers
     * @return Number of total providers
     */
    public static int getProvidersCount(){
        return TrustedListNavigation.getInstance().getAllProviders().size();
    }

    /**
     * Used to count the total of all countries
     * @return Number of total countries
     */
    public static int getCountriesCount(){
        return TrustedListNavigation.getInstance().getAllCountries().size();
    }

}
