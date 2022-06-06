package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trustedlist.models.DTO.*;
import trustedlist.models.engine.Selection;
import trustedlist.models.exceptions.NoSelectionException;

import static org.junit.jupiter.api.Assertions.*;

class SelectionTest {

    @Test
    @DisplayName("Toggle country should add and remove")
    public void toggleCountryShouldAddAndRemove(){
        Country c = new Country("it", "italia");
        Selection selection = new Selection();

        selection.toggle(c);
        assertEquals(1, selection.getSelectedCountries().size());
        assertEquals(c, selection.getSelectedCountries().get(0));

        selection.toggle(c);
        assertEquals(0, selection.getSelectedCountries().size());
    }

    @Test
    @DisplayName("Toggle provider should add and remove")
    public void toggleProviderShouldAddAndRemove(){
        Provider p = new Provider(1, "Test provider");
        Selection selection = new Selection();

        selection.toggle(p);
        assertEquals(1, selection.getSelectedProviders().size());
        assertEquals(p, selection.getSelectedProviders().get(0));

        selection.toggle(p);
        assertEquals(0, selection.getSelectedProviders().size());
    }

    @Test
    @DisplayName("Toggle type should add and remove")
    public void toggleTypeShouldAddAndRemove(){
        Selection selection = new Selection();

        selection.toggle(Type.CertESeal);
        assertEquals(1, selection.getSelectedTypes().size());
        assertEquals(Type.CertESeal, selection.getSelectedTypes().get(0));

        selection.toggle(Type.CertESeal);
        assertEquals(0, selection.getSelectedTypes().size());
    }

    @Test
    @DisplayName("Toggle Status should add and remove")
    public void toggleStatusShouldAddAndRemove(){
        Selection selection = new Selection();

        selection.toggle(Status.GRANTED);
        assertEquals(1, selection.getSelectedStatus().size());
        assertEquals(Status.GRANTED, selection.getSelectedStatus().get(0));

        selection.toggle(Status.GRANTED);
        assertEquals(0, selection.getSelectedStatus().size());
    }

    @Test
    @DisplayName("Lists should not work without countries selected")
    public void listsShouldNotWorkWithoutCountriesSelected(){
        Selection selection = new Selection();
        assertThrows(NoSelectionException.class, selection::getProvidersList);
        assertThrows(NoSelectionException.class, selection::getTypesList);
        assertThrows(NoSelectionException.class, selection::getStatusList);
    }

    @Test
    @DisplayName("Lists should not work without providers selected")
    public void listsShouldNotWorkWithoutProvidersSelected(){
        Selection selection = new Selection();
        selection.toggle(new Country("it", "italia"));

        assertThrows(NoSelectionException.class, selection::getTypesList);
        assertThrows(NoSelectionException.class, selection::getStatusList);
    }

    @Test
    @DisplayName("Lists should not work without types selected")
    public void listsShouldNotWorkWithoutTypesSelected(){
        Selection selection = new Selection();
        selection.toggle(new Country("it", "italia"));
        selection.toggle(new Provider(1, "Test provider"));

        assertThrows(NoSelectionException.class, selection::getStatusList);
    }

    @Test
    @DisplayName("Correct provider list call")
    public void correctProviderListCall(){
        // Given
        Selection selection = new Selection();
        Country c = new Country("it", "italia");
        Provider p = new Provider(1, "Test provider");

        // When
        c.addProvider(p);
        selection.toggle(c);
        selection.toggle(p);

        // Then
        assertEquals(p, selection.getProvidersList().get(0));
    }

    @Test
    @DisplayName("Correct types list call")
    public void correctTypesListCall(){
        // Given
        Selection selection = new Selection();
        Country c = new Country("it", "italia");
        Provider p = new Provider(1, "Test provider");

        // When
        c.addProvider(p);
        selection.toggle(c);
        selection.toggle(p);
        p.addType(Type.CertESeal);
        selection.toggle(Type.CertESeal);

        // Then
        assertEquals(Type.CertESeal, selection.getTypesList().get(0));
    }

    @Test
    @DisplayName("Correct status list call")
    public void correctStatusListCall(){
        // Given
        Selection selection = new Selection();
        Country c = new Country("it", "italia");
        Provider p = new Provider(1, "Test provider");
        Service s = new Service(1, "Test service", Status.GRANTED);

        // When
        c.addProvider(p);
        p.addService(s);
        p.addType(Type.CertESeal);
        s.addType(Type.CertESeal);
        selection.toggle(c);
        selection.toggle(p);
        selection.toggle(Type.CertESeal);
        selection.toggle(Status.GRANTED);

        // Then
        assertEquals(Status.GRANTED, selection.getStatusList().get(0));
    }
}