package trustedlist.models.engine;

import trustedlist.models.DTO.Country;
import trustedlist.models.DTO.Provider;
import trustedlist.models.DTO.Service;
import trustedlist.models.DTO.Type;
import trustedlist.models.exceptions.NoSelectionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Static class to handle the search of a service
 */

public class Search {


    private Search(){}

    /**
     * Filters services and create a list of services respecting the filters
     * @param selection Selection containing data about current selected filters
     * @return List of service respecting filters
     */
    public static List<Service> searchService(Selection selection){
        // Check if all selection fields have been set
        if(selection.getSelectedCountries().size() == 0)
            throw new NoSelectionException("You have to select countries first!");

        if(selection.getSelectedProviders().size() == 0)
            throw new NoSelectionException("You have to select providers first!");

        if(selection.getSelectedTypes().size() == 0)
            throw new NoSelectionException("You have to select types first!");

        if(selection.getSelectedStatus().size() == 0)
            throw new NoSelectionException("You have to select status first!");

        // Search the service
        List<Service> list = new ArrayList<>();
        for(Country c : selection.getSelectedCountries())
            for (Provider p : c.getProviders())
                if(selection.getSelectedProviders().contains(p))
                    for (Service s : p.getServices())
                        if(selection.getSelectedStatus().contains(s.getStatus()))
                            for(Type t : s.getTypes())
                                if(selection.getSelectedTypes().contains(t))
                                    list.add(s);

        return list;
    }




}
