package org.entain.tests.stepdefinitions;

import io.cucumber.java.en.Then;
import org.petstore.clients.PetClient;

import static org.hamcrest.Matchers.is;

/**
 * This class is Step definitions for Error Handling
 */
public class ErrorHandlingSteps {
    PetClient client = new PetClient();

    @Then("fetching pet id {long} should return 404")
    public void fetchingNonExistingShouldReturn404(long id) {
        client.getPetById(id)
                .then()
                .statusCode(is(404));
    }

    @Then("deleting pet id {long} should return 404")
    public void deletingNonExistingShouldReturn404(long id) {
        client.deletePet(id)
                .then()
                .statusCode(is(404));
    }
}

