package org.entain.tests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.awaitility.Awaitility;
import org.petstore.clients.PetClient;
import org.petstore.models.Pet;
import org.petstore.utils.ScenarioContext;
import org.petstore.utils.TestDataGenerator;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * This class is Step definitions for Create Pet
 */
public class CreatePetSteps {

    private final PetClient petClient = new PetClient();
    private Pet createdPet;
    private Response response;

    @Given("Create a new pet with random details")
    public void iCreateANewPetWithRandomDetails() {

        createdPet = TestDataGenerator.randomPet();
        response = petClient.createPet(createdPet);
        response.then().statusCode(anyOf(is(200), is(201)));


        long petId = response.jsonPath().getLong("id");
        createdPet.setId(petId);
        ScenarioContext.setCreatedPetId(petId);

        Awaitility.await("Pet should exist before retrieval")
                .atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofMillis(500))
                .until(() -> petClient.getPetById(petId).statusCode() == 200);
    }

    @When("Retrieve the created pet by ID")
    public void iRetrieveTheCreatedPetByID() {
        long id = ScenarioContext.getCreatedPetId();

        AtomicReference<Response> holder = new AtomicReference<>();
        Awaitility.await("Retrieve created pet should return 200")
                .atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofMillis(500))
                .until(() -> {
                    Response r = petClient.getPetById(id);
                    if (r.statusCode() == 200) {
                        holder.set(r);
                        return true;
                    }
                    return false;
                });

        response = holder.get();
    }

    @Then("The pet details should match the created data")
    public void thePetDetailsShouldMatchTheCreatedData() {

        response.then().statusCode(200);

        Long id = response.jsonPath().getLong("id");
        String name = response.jsonPath().getString("name");
        String status = response.jsonPath().getString("status");

        assertNotNull(id, "GET: id is null");
        assertEquals(id.longValue(), createdPet.getId().longValue(), "Pet id mismatch");
        assertEquals(name, createdPet.getName(), "Pet name mismatch");
        assertEquals(status, createdPet.getStatus(), "Pet status mismatch");
    }
}
