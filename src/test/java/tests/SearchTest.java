package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trustedlist.models.DTO.*;
import trustedlist.models.engine.Search;
import trustedlist.models.engine.Selection;
import trustedlist.models.exceptions.NoSelectionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SearchTest {

    @Test
    @DisplayName("Should not search with no countries")
    public void shouldNotSearchWithNoCountries(){
        // Given
        Selection s = new Selection();

        // Then
        assertThrows(NoSelectionException.class, () -> Search.searchService(s));
    }

    @Test
    @DisplayName("Should not search with no providers")
    public void shouldNotSearchWithNoProviders(){
        // Given
        Selection s = new Selection();

        // When
        s.toggle(new Country("it", "Italia"));

        // Then
        assertThrows(NoSelectionException.class, () -> Search.searchService(s));
    }

    @Test
    @DisplayName("Should not search with no types")
    public void shouldNotSearchWithNoTypes(){
        // Given
        Selection s = new Selection();

        // When
        s.toggle(new Country("it", "Italia"));
        s.toggle(new Provider(1, "Test provider"));

        // Then
        assertThrows(NoSelectionException.class, () -> Search.searchService(s));
    }

    @Test
    @DisplayName("Should not search with no status")
    public void shouldNotSearchWithNoStatus(){
        // Given
        Selection s = new Selection();

        // When
        s.toggle(new Country("it", "Italia"));
        s.toggle(new Provider(1, "Test provider"));
        s.toggle(Type.QCertESig);

        // Then
        assertThrows(NoSelectionException.class, () -> Search.searchService(s));
    }

    @Test
    @DisplayName("Correct search call")
    public void correctSearchCall(){
        Selection selection = new Selection();
        Country c = new Country("it", "italia");

        Provider p = new Provider(41, "Test provider");
        c.addProvider(p);

        Service s = new Service(1, "Test service", Status.GRANTED);
        s.addType(Type.QCertESeal);
        p.addService(s);

        selection.toggle(c);
        selection.toggle(p);
        selection.toggle(Type.QCertESeal);
        selection.toggle(Status.GRANTED);

        assertEquals(s, Search.searchService(selection).get(0));
    }
}