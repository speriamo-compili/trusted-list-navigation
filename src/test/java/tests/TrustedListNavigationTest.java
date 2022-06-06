package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trustedlist.models.engine.TrustedListNavigation;

import static org.junit.jupiter.api.Assertions.*;

class TrustedListNavigationTest {

    @Test
    @DisplayName("Init should initialize all")
    public void initShouldInitializeAll(){
        TrustedListNavigation tln = TrustedListNavigation.getInstance();

        assertTrue(tln.getAllCountries().size() > 0);
        assertTrue(tln.getAllProviders().size() > 0);
        assertTrue(tln.getAllServices().size() > 0);
    }
}