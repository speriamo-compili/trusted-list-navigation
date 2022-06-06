package tests;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trustedlist.models.DTO.Provider;
import trustedlist.models.DTO.Service;
import trustedlist.models.DTO.Status;
import trustedlist.models.DTO.Type;
import trustedlist.models.exceptions.DuplicatedException;
import trustedlist.models.exceptions.ProviderNotValidException;

import java.io.*;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {

    @Test
    @DisplayName("Provider id should not be negative")
    public void providerIdShouldNotBeNegative(){
        assertThrows(ProviderNotValidException.class, () -> new Provider(-1, "Test provider"));
    }

    @Test
    @DisplayName("Provider name should not be empty")
    public void providerNameShouldNotBeEmpty(){
        assertThrows(ProviderNotValidException.class, () -> new Provider(1, ""));
    }

    @Test
    @DisplayName("Services should not be duplicated")
    public void servicesShouldNotBeDuplicated(){
        // Given
        Provider p = new Provider(1, "Test provider");
        Service s = new Service(1, "Test service", Status.DEPRECATEDATNATIONALLEVEL);

        // When
        p.addService(s);

        // Then
        assertThrows(DuplicatedException.class, () -> p.addService(s));
    }

    @Test
    @DisplayName("Types should not be duplicated")
    public void typesShouldNotBeDuplicated(){
        // Given
        Provider p = new Provider(1, "Test provider");

        // When
        p.addType(Type.CertESeal);

        // Then
        assertThrows(DuplicatedException.class, () -> p.addType(Type.CertESeal));
    }

    @Test
    @DisplayName("Correct provider creation step by step")
    public void correctProviderCreationStepByStep(){
        // Given
        Provider p = new Provider(1, "Test provider");
        Service s = new Service(1, "Test service", Status.DEPRECATEDATNATIONALLEVEL);

        // When
        p.addService(s);

        // Then
        assertEquals(1, p.getId());
        assertEquals("Test provider", p.getName());
    }

    @Test
    @DisplayName("Correct provider creation json")
    public void correctProviderCreationJSON() throws IOException {
        // Given
        URL url = this.getClass().getResource("Provider.json");
        File file = new File(url.getFile());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());

        // When
        Provider p = new Provider(json);

        // Then
        assertEquals(1, p.getId());
        assertEquals("Test provider", p.getName());
        assertEquals("Test service", p.getServices().get(0).getName());
        assertEquals(Type.QCertESig, p.getTypes().get(0));
    }

    @Test
    @DisplayName("Correct toString call")
    public void correctToStringCall(){
        // Given
        Provider p = new Provider(1, "Test provider");

        // Then
        assertEquals("Test provider (0 type(s))", p.toString());
    }


}