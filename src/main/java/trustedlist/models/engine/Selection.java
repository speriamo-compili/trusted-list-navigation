package trustedlist.models.engine;

import trustedlist.models.DTO.*;
import trustedlist.models.exceptions.NoSelectionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to handle and keep track of user selections to allow navigation and search
 */
public final class Selection {
    private final List<Country> selectedCountries = new ArrayList<>();
    private final List<Provider> selectedProviders = new ArrayList<>();
    private final List<Type> selectedTypes = new ArrayList<>();
    private final List<Status> selectedStatus = new ArrayList<>();

    /**
     * Get a list of the current selection selected countries
     * @return List of selected countries
     */
    public List<Country> getSelectedCountries() {return selectedCountries;}

    /**
     * Get a list of the current selection selected providers
     * @return List of selected providers
     */
    public List<Provider> getSelectedProviders() {return selectedProviders;}

    /**
     * Get a list of the current selection selected types
     * @return List of selected types
     */
    public List<Type> getSelectedTypes() {return selectedTypes;}

    /**
     * Get a list of the current selection selected status
     * @return List of selected status
     */
    public List<Status> getSelectedStatus() {return selectedStatus;}

    /**
     * Toggle the selection of a country
     * @param c Country to select/deselect
     */
    public void toggle(Country c){
        if(selectedCountries.contains(c))
            selectedCountries.remove(c);
        else
            selectedCountries.add(c);
    }

    /**
     * Toggle the selection of a provider
     * @param p Provider to select/deselect
     */
    public void toggle(Provider p){
        if(selectedProviders.contains(p))
            selectedProviders.remove(p);
        else
            selectedProviders.add(p);
    }

    /**
     * Toggle the selection of a Type
     * @param t Type to select/deselect
     */
    public void toggle(Type t){
        if(selectedTypes.contains(t))
            selectedTypes.remove(t);
        else
            selectedTypes.add(t);
    }

    /**
     * Toggle the selection of a status
     * @param s Status to select/deselect
     */
    public void toggle(Status s){
        if(selectedStatus.contains(s))
            selectedStatus.remove(s);
        else
            selectedStatus.add(s);
    }

    /**
     * Create a provider list based on current selection
     *
     * To work properly, you should handle the selection of countries before using this method.<br>
     *
     * If that filter is not set, it throws an error
     *
     * @return List of providers base on current selection made
     * @throws NoSelectionException If a needed selection is not been made
     */
    public List<Provider> getProvidersList(){

        if(getSelectedCountries().size() == 0)
            throw new NoSelectionException("You have to select a country first!");

        List<Provider> list = new ArrayList<>();
        for(Country c : getSelectedCountries())
            list.addAll(c.getProviders());
        return list;
    }

    /**
     * Create a types list based on current selection<br>
     *
     * To work properly, you should handle the selection of countries and providers<br>
     * before using this method.<br>
     *
     * If one of those filter is not set, it throws an error
     *
     * @return List of types base on current selection made
     * @throws NoSelectionException If a needed selection is not been made
     */
    public List<Type> getTypesList(){

        if(getSelectedCountries().size() == 0)
            throw new NoSelectionException("You have to select a country first!");

        if(getSelectedProviders().size() == 0)
            throw new NoSelectionException("You have to select a provider first!");

        List<Type> list = new ArrayList<>();
        for(Provider p : getSelectedProviders())
            for(Type t : p.getTypes())
                if(!list.contains(t))
                    list.add(t);
        return list;
    }

    /**
     * Create a status list based on current selection<br>
     *
     * To work properly, you should handle the selection of countries, providers and types<br>
     * before using this method.<br>
     *
     * If one of those filter is not set, it throws an error
     *
     * @return List of status base on current selection made
     * @throws NoSelectionException If a needed selection is not been made
     */
    public List<Status> getStatusList(){

        if(getSelectedCountries().size() == 0)
            throw new NoSelectionException("You have to select a country first!");

        if(getSelectedProviders().size() == 0)
            throw new NoSelectionException("You have to select a provider first!");

        if(getSelectedTypes().size() == 0)
            throw new NoSelectionException("You have to select a type first!");

        List<Status> list = new ArrayList<>();
        for(Provider p : getSelectedProviders())
            for(Service s : p.getServices())
                for(Type t : s.getTypes())
                    if(!list.contains(s.getStatus()) && getSelectedTypes().contains(t))
                        list.add(s.getStatus());
        return list;
    }

}
