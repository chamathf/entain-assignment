package org.petstore.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.petstore.models.Pet;

import static io.restassured.RestAssured.given;

/**
 * This Client class provides helper methods to call the PetStore API.
 */
public class PetClient {

    private static final String PET = "/pet";
    private static final String PET_BY_ID = "/pet/{petId}";
    private static final String FIND_BY_STATUS = "/pet/findByStatus";

    /**
     * Create a new pet in the store.
     */
    public Response createPet(Pet pet) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(pet)
                .log().ifValidationFails()
                .when()
                .post(PET)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }

    /**
     * Update an existing pet in the store.
     */
    public Response updatePet(Pet pet) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(pet)
                .log().ifValidationFails()
                .when()
                .put(PET)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }

    /**
     * Retrieve a pet by its unique ID.
     */
    public Response getPetById(long id) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("petId", id)
                .log().ifValidationFails()
                .when()
                .get(PET_BY_ID)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }

    /**
     * Delete a pet from the store by ID.
     */
    public Response deletePet(long id) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("petId", id)
                .log().ifValidationFails()
                .when()
                .delete(PET_BY_ID)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }

    /**
     * Find all pets that match a given status.
     */
    public Response findByStatus(String status) {
        return given()
                .accept(ContentType.JSON)
                .queryParam("status", status)
                .log().ifValidationFails()
                .when()
                .get(FIND_BY_STATUS)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }
}
