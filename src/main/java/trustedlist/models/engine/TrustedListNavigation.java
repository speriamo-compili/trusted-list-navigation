package trustedlist.models.engine;

import org.json.JSONArray;
import trustedlist.models.DTO.Country;
import trustedlist.models.DTO.Provider;
import trustedlist.models.DTO.Service;
import trustedlist.models.exceptions.CantLoadDataException;
import trustedlist.models.exceptions.DuplicatedException;
import trustedlist.models.utility.RequestsHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Main trusted list navigation class, is a singleton so can be instanced only once
 */
public class TrustedListNavigation {

    /*----------------------------------------------------*
     *    To implements this class the design pattern     *
     *    SINGLETON is been used.                         *
     *                                                    *
     *    There can be only one instance of this class    *
     *    when exectued                                   *
     *----------------------------------------------------*/

    private static TrustedListNavigation instance;
    private final Map<String, Country> countries;

    private TrustedListNavigation() throws DuplicatedException {
        JSONArray data;
        JSONArray countries;
        try {
            countries = new JSONArray(RequestsHandler.get("https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/countries_list"));
            data = new JSONArray(RequestsHandler.get("https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/tsp_list"));
        } catch (Exception e) {
            throw new CantLoadDataException("Could not load the data!");
        }

        // Initialize countries
        this.countries = new TreeMap<>();
        for(int i = 0; i < countries.length(); i++){
            String countryCode = countries.getJSONObject(i).getString("countryCode");
            String countryName = countries.getJSONObject(i).getString("countryName");
            if(!countryCode.equalsIgnoreCase("eu")) // We want to skip EU
                this.countries.put(countryCode, new Country(countryCode, countryName));
        }

        // Initialize providers
        for (int i = 0; i < data.length(); i++){
            Provider current = new Provider(data.getJSONObject(i));
            this.countries.get(data.getJSONObject(i).getString("countryCode")).addProvider(current);
        }
    }

    /**
     * Create/Get the instance
     * @return Instance of the class
     */
    public static TrustedListNavigation getInstance(){
        if(instance == null)
            instance = new TrustedListNavigation();
        return instance;
    }

    /**
     * Used to get the full list of countries
     * @return List of all the countries
     */
    public List<Country> getAllCountries(){
        List<Country> list = new ArrayList<>();
        for(String code : countries.keySet())
            list.add(countries.get(code));
        return list;
    }

    /**
     * Used to get the full list of providers
     * @return List of all the providers
     */
    public List<Provider> getAllProviders(){
        List<Provider> list = new ArrayList<>();
        for(String code : countries.keySet())
            list.addAll(countries.get(code).getProviders());
        return list;
    }

    /**
     * Used to get the full list of services
     * @return List of all the services
     */
    public List<Service> getAllServices(){
        List<Service> list = new ArrayList<>();
        for(String code : countries.keySet())
            for(Provider p : countries.get(code).getProviders())
                list.addAll(p.getServices());
        return list;
    }

}
