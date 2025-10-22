package org.entain.tests.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.petstore.clients.PetClient;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

/**
 * This class is Step definitions for Find pet
 */
public class FindPetSteps {
    PetClient petClient = new PetClient();
    Response response;

    @When("query pets by status {string}")
    public void queryPetsByStatus(String status) {
        response = petClient.findByStatus(status);
    }


    @Then("only pets with status {string} are returned")
    public void onlyPetsWithStatusAreReturned(String status) {
        response.then()
                .statusCode(200)
                .body("$", notNullValue())
                .body("status", everyItem(equalTo(status)));
    }

    @And("the response matches the findByStatus schema")
    public void theResponseMatchesTheFindByStatusSchema() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/findByStatus-schema.json"));
    }
}
