package trustedlist.models.DTO;

import org.json.JSONArray;
import org.json.JSONObject;
import trustedlist.models.exceptions.DuplicatedException;
import trustedlist.models.exceptions.ProviderNotValidException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to contain information about a provider
 */
public class Provider {

    private final int id;
    private final String name;
    private final List<Service> services;
    private final List<Type> types;


    /**
     * Create a new instance of a provider and initialize empty lists
     * @param id Id of the TSP
     * @param name Name of the TSP
     */
    public Provider(int id, String name){

        // Check if id is valid
        if(id < 0)
            throw new ProviderNotValidException("Provider id must be greater than 0");

        // Check if name contains somehting and its not empty
        if(name.trim().equalsIgnoreCase(""))
            throw new ProviderNotValidException("Provider name cannot be empty!");

        this.id = id;
        this.name = name.trim();
        this.services = new ArrayList<>();
        this.types = new ArrayList<>();
    }

    /**
     * Create a new instance of a provider and initialize empty lists
     * @param serializedObject Provider as json object
     */
    public Provider(JSONObject serializedObject){
        // Build empty provider using the other constructor
        this(serializedObject.getInt("tspId"), serializedObject.getString("name"));

        // From json we get types list supplied by the provider
        // We iterate through them and add them individually
        for(int i = 0; i < serializedObject.getJSONArray("qServiceTypes").length();i++)
            addType(Type.valueOf(serializedObject.getJSONArray("qServiceTypes").getString(i)));


        // From json we get service list supplied by the provider
        // We iterate through them and add them individually
        JSONArray servicesJSON = serializedObject.getJSONArray("services");
        for(int i = 0; i < servicesJSON.length(); i++)
            addService(new Service(servicesJSON.getJSONObject(i)));
    }

    /**
     * Used to get the id of the TSP
     * @return Id of provider
     */
    public int getId(){return id;}

    /**
     * Used to get the name of the TSP
     * @return Name of provider
     */
    public String getName() {return name;}

    /**
     * Used to get the services supplied by the TSP
     * @return List of services of the provider
     */
    public List<Service> getServices() {return services;}

    /**
     * Used to get the service types supplied by the TSP
     * @return List of service types of the provider
     */
    public List<Type> getTypes(){
        return types;
    }

    /**
     * Used to add a service to the provider
     * @param service Service to add
     * @throws DuplicatedException if trying to add a service that is already contained
     * in the provider's services list
     */
    public void addService(Service service){
        // If already contained, throw error
        if(services.contains(service))
            throw new DuplicatedException("Tried to add a service to a provider that already contains it!");

        // If not, add it
        services.add(service);
    }

    /**
     * Used to add a type to the provider
     * @param type Type to add
     * @throws DuplicatedException if trying to add a type that is already contained
     * in the provider's service types list
     */
    public void addType(Type type){
        // If already contained, throw error
        if(types.contains(type))
            throw new DuplicatedException("Tried to add a type to a provider that already contains it!");

        // If not, add it
        types.add(type);
    }

    /**
     * Compare two providers and determine if they are equal
     * @param other Object to be compared
     * @return true if same, false otherwise
     */
    @Override
    public boolean equals(Object other){
        // If other is self
        if(other == this)  return true;

        // Check if other is instace of same class
        // if not, they cannot be equal
        if(!(other instanceof Provider p)) return false;

        // Determine if countries are equal base on country code
        return this.id == ((Provider) other).id && this.name.equalsIgnoreCase(((Provider) other).name);
    }


    /**
     * Used to get the string representation of the provider
     * @return Provider as a string (name)
     */
    public String toString(){ return name + " ("+types.size() +" type(s))"; }
}
