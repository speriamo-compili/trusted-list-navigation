package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trustedlist.models.DTO.*;
import trustedlist.models.exceptions.DuplicatedException;
import trustedlist.models.exceptions.ServiceNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    @DisplayName("Service id should not be negative")
    public void serviceIdShouldNotBeNegative(){
        assertThrows(ServiceNotValidException.class, () -> new Service(-1, "Test provider", Status.DEPRECATEDATNATIONALLEVEL));
    }

    @Test
    @DisplayName("Service name should not be empty")
    public void serviceNameShouldNotBeEmpty(){
        assertThrows(ServiceNotValidException.class, () -> new Service(1, "", Status.DEPRECATEDATNATIONALLEVEL));
    }

    @Test
    @DisplayName("Types should not be duplicated")
    public void typesShouldNotBeDuplicated(){
        // Given
        Service s = new Service(1, "Test service", Status.GRANTED);

        // When
        s.addType(Type.CertESeal);

        // Then
        assertThrows(DuplicatedException.class, () -> s.addType(Type.CertESeal));
    }

    @Test
    @DisplayName("Correct service creation step by step")
    public void correctProviderCreationStepByStep(){
        // Given
        Service s = new Service(1, "Test service", Status.GRANTED);

        // When
        s.addType(Type.CertESeal);

        // Then
        assertEquals(1, s.getId());
        assertEquals("Test service", s.getName());
        assertEquals(Status.GRANTED, s.getStatus());
        assertEquals(Type.CertESeal, s.getTypes().get(0));
    }

    @Test
    @DisplayName("Correct toString call")
    public void correctToStringCall(){
        // Given
        Service s = new Service(1, "Test service", Status.GRANTED);

        // Then
        assertEquals("Test service (Granted)", s.toString());
    }

    @Test
    @DisplayName("Comparison should work")
    public void comparisonShouldWork(){
        // Given
        Service s1 = new Service(1, "Test service 1", Status.DEPRECATEDATNATIONALLEVEL);
        Service s2 = new Service(2, "Test service 2", Status.DEPRECATEDATNATIONALLEVEL);


        // Then
        assertTrue(s1.compareTo(s2) < 0);
    }

}