package org.entain.tests.hooks;

import org.petstore.clients.PetClient;
import org.petstore.config.ConfigManager;
import org.petstore.utils.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

/**
 * This class runs setup and cleanup logic automatically before and after each test scenario.
 */
public class Hooks {

    /**
     * This method runs before each Cucumber scenario.
     */
    @Before
    public void setup() {
        RestAssured.baseURI = ConfigManager.getBaseUrl();
    }

    /**
     * This stores the created pets ID in a shared context for later cleanup.
     */
    public static void trackCreatedPetId(Long id) {
        ScenarioContext.setCreatedPetId(id);
    }

    /**
     * This method runs after each Cucumber scenario.
     * If a pet was created during the test, this method automatically deletes it.
     */
    @After
    public void cleanup() {
        Long petId = ScenarioContext.getCreatedPetId();
        if (petId != null) {
            new PetClient().deletePet(petId);
        }
    }
}
