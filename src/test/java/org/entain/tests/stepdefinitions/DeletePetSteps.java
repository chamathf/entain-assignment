package org.entain.tests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.awaitility.Awaitility;
import org.petstore.clients.PetClient;
import org.petstore.models.Pet;
import org.petstore.utils.TestDataGenerator;
import org.testng.Assert;

import java.time.Duration;

import static org.hamcrest.Matchers.*;

/**
 * This class is Step definitions for Delete Pet
 */
public class DeletePetSteps {

    private final PetClient petClient = new PetClient();
    private Pet createdPet;
    private Response response;

    @Given("a pet exists to delete")
    public void aPetExistsToDelete() {

        createdPet = TestDataGenerator.randomPet();
        Response create = petClient.createPet(createdPet);
        Assert.assertTrue(
                create.statusCode() == 200 || create.statusCode() == 201,
                "Precondition failed: could not create pet, status=" + create.statusCode()
        );

        long serverId = create.jsonPath().getLong("id");
        createdPet.setId(serverId);

        Awaitility.await("Pet should exist before deletion")
                .atMost(Duration.ofSeconds(5))
                .pollInterval(Duration.ofMillis(500))
                .until(() -> petClient.getPetById(serverId).statusCode() == 200);
    }

    @When("delete the pet")
    public void DeleteThePet() {
        response = petClient.deletePet(createdPet.getId());
    }

    @Then("the pet is deleted successfully")
    public void thePetIsDeletedSuccessfully() {

        response.then().statusCode(anyOf(is(200), is(204), is(404)));

        Awaitility.await("Pet should be deleted")
                .atMost(Duration.ofSeconds(5))
                .pollInterval(Duration.ofMillis(500))
                .until(() -> petClient.getPetById(createdPet.getId()).statusCode() == 404);
    }
}
