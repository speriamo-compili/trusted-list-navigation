package trustedlist.models.DTO;

import org.json.JSONArray;
import org.json.JSONObject;
import trustedlist.models.exceptions.DuplicatedException;
import trustedlist.models.exceptions.ServiceNotValidException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to store information about a service
 */
public class Service implements Comparable<Service>{

    private final int id;
    private final String name;
    private final Status status;
    private final List<Type> types = new ArrayList<>();


    /**
     * Constructor of the class Service
     * @param id Id of the service
     * @param name Name of the service
     * @param status Status of the service
     * @throws ServiceNotValidException if argumnents are wrong
     */
    public Service(int id, String name, Status status){

        if(id < 0)
            throw new ServiceNotValidException("Service id cannot be negative!");

        if(name.trim().equalsIgnoreCase(""))
            throw new ServiceNotValidException("Service name cannot be empty!");

        this.id = id;
        this.name = name.trim();
        this.status = status;
    }

    /**
     * Constructor of the class Service
     * @param serializedService Serialized object as JSON
     */
    public Service(JSONObject serializedService){
        String currentStatusLink = serializedService.getString("currentStatus");
        id = serializedService.getInt("serviceId");
        name = serializedService.getString("serviceName");
        status = Status.valueOf(currentStatusLink.substring(currentStatusLink.lastIndexOf("/")+1).toUpperCase());

        // Initializing types
        JSONArray serviceTypes = serializedService.getJSONArray("qServiceTypes");
        for (int i = 0; i < serviceTypes.length(); i++)
            addType(Type.valueOf(serviceTypes.getString(i)));
    }


    /**
     * Used to get the id of the service
     * @return Id of the service
     */
    public int getId() {return id;}


    /**
     * Used to get service name
     * @return Service name
     */
    public String getName() {return name;}

    /**
     * Used to get service status
     * @return Service status
     */
    public Status getStatus(){return status;}

    /**
     * Used to get list of types
     * @return List of types of the service
     */
    public List<Type> getTypes(){return types;}


    /**
     * Used to add a service to the provider
     * @param type Type to add
     * @throws DuplicatedException if trying to add a type that is already contained
     * in the provider's service types list
     */
    public void addType(Type type){
        // Check if type is already in the list
        if(types.contains(type))
            throw new DuplicatedException("Tried to add a type to a service that already contains it!");

        // If not, add it
        types.add(type);
    }

    /**
     * Representation of the class as string
     * @return Service as string
     */
    public String toString(){
        return name + " ("+ status.getDescription() + ")";
    }

    /**
     * Used to compare services
     * @param o Object to compare to
     * @return true if same, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if(o == this)  return true;

        if(!(o instanceof Service s)) return false;

        return this.id == s.id && this.name.equalsIgnoreCase(s.name) && this.types == ((Service) o).types;
    }

    /**
     * Used to compare two services
     * @param s Service to compare with
     * @return 1 if this service name in alphabetically first, 0 if same, -1 if s name is first
     */
    public int compareTo(Service s){
        return this.name.compareTo(s.name);
    }

}
