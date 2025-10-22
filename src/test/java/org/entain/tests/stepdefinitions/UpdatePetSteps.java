package org.entain.tests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.awaitility.Awaitility;
import org.entain.tests.hooks.Hooks;
import org.petstore.clients.PetClient;
import org.petstore.models.Pet;
import org.petstore.utils.TestDataGenerator;
import org.testng.Assert;

import java.time.Duration;

import static org.hamcrest.Matchers.*;

/**
 * This class is Step definitions for update pet
 */
public class UpdatePetSteps {

    private final PetClient petClient = new PetClient();
    private Pet createdPet;
    private Response updateResponse;

    @Given("an existing pet")
    public void anExistingPet() {

        createdPet = TestDataGenerator.randomPet();

        Response createResponse = petClient.createPet(createdPet);
        Assert.assertTrue(
                createResponse.statusCode() == 200 || createResponse.statusCode() == 201,
                "Failed to create pet before update. Status: " + createResponse.statusCode()
        );

        long serverId = createResponse.jsonPath().getLong("id");
        createdPet.setId(serverId);

        Hooks.trackCreatedPetId(createdPet.getId());

        Awaitility.await("Pet should exist before update")
                .atMost(Duration.ofSeconds(5))
                .pollInterval(Duration.ofMillis(500))
                .until(() -> petClient.getPetById(createdPet.getId()).statusCode() == 200);
    }

    @When("change its name to {string} and status to {string}")
    public void changeItsNameToAndStatusTo(String newName, String newStatus) {

        createdPet.setName(newName);
        createdPet.setStatus(newStatus);

        updateResponse = petClient.updatePet(createdPet);
    }

    @Then("verify the update is persisted")
    public void verifyTheUpdateIsPersisted() {

        updateResponse.then().statusCode(200);

        long idInUpdate = updateResponse.jsonPath().getLong("id");
        Assert.assertEquals(idInUpdate, createdPet.getId().longValue(), "Update response id != expected");

        updateResponse.then()
                .body("name", equalTo(createdPet.getName()))
                .body("status", equalTo(createdPet.getStatus()));

        Awaitility.await("Pet update should be visible in GET")
                .atMost(Duration.ofSeconds(5))
                .pollInterval(Duration.ofMillis(500))
                .until(() -> petClient.getPetById(createdPet.getId()).statusCode() == 200);

        Response getResponse = petClient.getPetById(createdPet.getId());
        getResponse.then().statusCode(200);

        long idInGet = getResponse.jsonPath().getLong("id");
        Assert.assertEquals(idInGet, createdPet.getId().longValue(), "GET id != expected");

        getResponse.then()
                .body("name", equalTo(createdPet.getName()))
                .body("status", equalTo(createdPet.getStatus()));
    }
}
