package trustedlist.models.DTO;

import trustedlist.models.exceptions.CountryNotValidException;
import trustedlist.models.exceptions.DuplicatedException;
import trustedlist.models.utility.Stats;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to store information about a country
 */
public class Country implements Comparable<Country>{

    private final String code;
    private final String name;
    private final List<Provider> providers = new ArrayList<>();


    /**
     * Create a new instance of a country initializing variables and provider list
     * @param code Country code (ISO 3166-1 alpha-2)
     * @param name Full name of the country
     */
    public Country(String code, String name){

        // Check if code is valid
        if (code.length() != 2)
            throw new CountryNotValidException("Code must be a 2 letter string!");

        if (!code.matches("[a-zA-Z][a-zA-Z]"))
            throw new CountryNotValidException("Code cannot contain a number or a special char!");

        // Check if country name is valid
        if(name.length() == 0)
            throw new CountryNotValidException("Country name cannot be empty!");

        if(!name.matches("[a-zA-Z ]+"))
            throw new CountryNotValidException("Country name cannot contain numbers and special chars!");

        // Initialize variables
        this.code = code.toUpperCase();
        this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase(); // Capitalize;
    }

    /**
     * Used to get the ISO 3166-1 alpha-2 code of the country
     * (More details <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">here</a>)
     * @return ISO 3166-1 alpha-2 code of the country
     */
    public String getCode(){return code;}

    /**
     * Used to get the full name of the country
     * @return Full name of the country
     */
    public String getName(){return name;}

    /**
     * Used to get the list of providers that are supplying services
     * to the country
     * @return List of country's providers
     */
    public List<Provider> getProviders(){
        return providers;
    }

    /**
     * Used to get the list of services that are supplied
     * to the country
     * @return List of country's services
     */
    public List<Service> getServices(){
        List<Service> list = new ArrayList<>();
        for(Provider p : providers)
            list.addAll(p.getServices());
        return list;
    }

    /**
     * Used to get the list of service types supplied to the country
     * @return List of country's service types
     */
    public List<Type> getTypes(){
        List<Type> list = new ArrayList<>();
        for(Provider p : getProviders())
            for(Type t : p.getTypes())
                if(!list.contains(t))
                    list.add(t);
        return list;
    }


    /**
     * Used to add a provider to the country's providers list
     * @param provider Provider to add
     * @throws DuplicatedException if trying to add a provider that is already contained
     * in the country's providers list
     */
    public void addProvider(Provider provider) {
        // Check if specified provider is already contained in the list
        if(providers.contains(provider))
            throw new DuplicatedException("Tried to add a provider to the list but is already in!");

        // If not, add it
        providers.add(provider);
    }

    /**
     * Used to get the string representation of the country
     * @return Country as a string (name)
     */
    public String toString(){
        return name + " (#" + code + ")";
    }

    /**
     * Compare two countries and determine which one comes first based on
     * the country's services number
     * @param other Object to be compared
     * @return Negative number if this has more services, 0 if
     */
    @Override
    public int compareTo(Country other) {
        return Stats.getCountryServicesCount(other) - Stats.getCountryServicesCount(this);
    }

    /**
     * Compare two countries and determine if they are equal
     * @param other Object to be compared
     * @return true if same, false otherwise
     */
    @Override
    public boolean equals(Object other){
        // If other is self
        if(other == this)  return true;

        // Check if other is instace of same class
        // if not, they cannot be equal
        if(!(other instanceof Country)) return false;

        // Determine if countries are equal base on country code
        return ((Country)other).getCode().equalsIgnoreCase(code);
    }
}

