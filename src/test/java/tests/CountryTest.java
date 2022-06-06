package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trustedlist.models.DTO.*;
import trustedlist.models.exceptions.CountryNotValidException;
import trustedlist.models.exceptions.DuplicatedException;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    @DisplayName("Country code should be 2 char long")
    public void countryCodeShouldBe2CharLong(){
        assertThrows(CountryNotValidException.class, () -> new Country("", "italia"));
        assertThrows(CountryNotValidException.class, () -> new Country("i", "italia"));

        assertThrows(CountryNotValidException.class, () -> new Country("ita", "italia"));
    }

    @Test
    @DisplayName("Country code should not contain special chars or numbers")
    public void countryCodeShouldNotContainSpecialCharOrNumbers(){
        assertThrows(CountryNotValidException.class, () -> new Country("a1", "italia"));
        assertThrows(CountryNotValidException.class, () -> new Country("7a", "italia"));
        assertThrows(CountryNotValidException.class, () -> new Country("52", "italia"));
        assertThrows(CountryNotValidException.class, () -> new Country("a!", "italia"));
        assertThrows(CountryNotValidException.class, () -> new Country("#a", "italia"));
        assertThrows(CountryNotValidException.class, () -> new Country(",$", "italia"));
    }

    @Test
    @DisplayName("Country name should not be empty")
    public void countryNameShouldNotBeEmpty(){
        assertThrows(CountryNotValidException.class, () -> new Country("it", ""));
    }

    @Test
    @DisplayName("Country name should not contain special chars or numbers")
    public void countryNameShouldNotContainsSpecialCharsOrNumbers(){
        assertThrows(CountryNotValidException.class, () -> new Country("it", "it3lia"));
        assertThrows(CountryNotValidException.class, () -> new Country("it", "it!alia"));
        assertThrows(CountryNotValidException.class, () -> new Country("it", "215"));
        assertThrows(CountryNotValidException.class, () -> new Country("it", ")£/"));
    }

    @Test
    @DisplayName("Correct country creation")
    public void correctCountryCreation(){
        Country c = new Country("it", "itALIa");

        assertEquals("IT", c.getCode());
        assertEquals("Italia", c.getName());
    }

    @Test
    @DisplayName("Provider should not be duplicated")
    public void providerShouldNotBeDuplicated(){
        //Given
        Country c = new Country("IT", "Italia");
        Provider p = new Provider(1, "Test provider");

        //When
        c.addProvider(p);

        // Then
        assertThrows(DuplicatedException.class, () -> c.addProvider(p));
    }

    @Test
    @DisplayName("Correct provider addition")
    public void correctProviderAddition(){
        //Given
        Country c = new Country("IT", "Italia");
        Provider p = new Provider(1, "Test provider");
        Service s = new Service(1, "Test service", Status.DEPRECATEDATNATIONALLEVEL);

        //When
        p.addService(s);
        c.addProvider(p);
        p.addType(Type.CertESeal);

        // Then
        assertEquals(p, c.getProviders().get(0));
        assertEquals(Type.CertESeal, c.getTypes().get(0));
        assertEquals(s, c.getServices().get(0));
    }

    @Test
    @DisplayName("Correct toString call")
    public void correctToStringCall(){
        // Given
        Country c = new Country("it", "italia");

        // Then
        assertEquals("Italia (#IT)", c.toString());
    }

    @Test
    @DisplayName("Comparison should work")
    public void comparisonShouldWork(){
        // Given
        Country c1 = new Country("it", "italia");
        Country c2 = new Country("it", "italia");

        Provider p1 = new Provider(1, "Test provider 1");
        Provider p2 = new Provider(2, "Test provider 2");

        Service s1 = new Service(1, "Test service 1", Status.DEPRECATEDATNATIONALLEVEL);
        Service s2 = new Service(2, "Test service 2", Status.DEPRECATEDATNATIONALLEVEL);

        // When
        p1.addService(s1);
        p1.addService(s2);
        p2.addService(s1);
        c1.addProvider(p1);
        c2.addProvider(p2);

        // Then
        assertTrue(c1.compareTo(c2) < 0);
        assertEquals(c1, c2);
    }


}