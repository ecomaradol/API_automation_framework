package tests;

import io.restassured.response.Response;
import models.lombok.DataModel;
import models.Pet;

import org.junit.jupiter.api.*;


import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static specs.Specs.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetStoreTest extends TestBase {

    @Order(1)
    @Test
    @DisplayName("Add a new pet to the store")
    void addNewPet() {
        Pet pet = new Pet();
        pet.setId(99);
        pet.setName("Kitten");
        pet.setStatus("sold");

        Pet responseNewPet =
                given()
                        .spec(request)
                        .body(pet)
                        .when()
                        .post("/pet")
                        .then()
                        .spec(response200)
                        .log().body()
                        .extract().as(Pet.class);

        assertEquals(pet.getName(), responseNewPet.getName());
        assertEquals(pet.getStatus(), responseNewPet.getStatus());
    }

    @Order(2)
    @Test
    @DisplayName("Find pet by ID")
    void findPetById() {
        DataModel response =
                given()
                        .spec(request)
                        .when()
                        .get("/pet/99")
                        .then()
                        .spec(response200)
                        .log().body()
                        .extract().as(DataModel.class);

        Integer Id = 99;
        String name = "Kitten";
        String status = "sold";

       assertEquals(Id, response.getId());
       assertEquals(name, response.getName());
       assertEquals(status, response.getStatus());
    }

    @Order(3)
    @Test
    @DisplayName("Update a pet in the store with form data")
    void updatePet() {

        Response responseUpdatePet =
                given()
                        .spec(requestUrlencoded)
                        .contentType("application/x-www-form-urlencoded")
                        .param("id", "99")
                        .param("name", "Kitten")
                        .param("status", "pending")
                        .when()
                        .post("/pet/99")
                        .then()
                        .spec(response200)
                        .log().body()
                        .extract().response();

        Integer code = 200;
        String type = "unknown";
        String message = "99";

        assertEquals(code, responseUpdatePet.path("code"));
        assertEquals(type, responseUpdatePet.path("type"));
        assertEquals(message, responseUpdatePet.path("message"));
    }

    @Order(4)
    @Test
    @DisplayName("Delete a pet")
    void deletePet() {

        Response response =
                given()
                        .spec(request)
                        .when()
                        .delete("/pet/99")
                        .then()
                        .spec(response200)
                        .log().body()
                        .extract().response();

        Integer code = 200;
        String type = "unknown";
        String message = "99";

        assertEquals(code, response.path("code"));
        assertEquals(type, response.path("type"));
        assertEquals(message, response.path("message"));
    }

    @Order(5)
    @Test
    @DisplayName("Find non-existing pet by ID")
    void findNonExistingPetById() {
        Response response =
                given()
                        .spec(request)
                        .when()
                        .get("/pet/99")
                        .then()
                        .spec(response404)
                        .log().body()
                        .extract().response();

        Integer code = 1;
        String type = "error";
        String message = "Pet not found";

        assertEquals(code, response.path("code"));
        assertEquals(type, response.path("type"));
        assertEquals(message, response.path("message"));

    }

    @Order(6)
    @Test
    @DisplayName("Update non-existing pet in the store with form data")
    void updateNonExistingPet() {

        Response responseNonExistingPet =
                given()
                        .spec(requestUrlencoded)
                        .contentType("application/x-www-form-urlencoded")
                        .param("id", "99")
                        .param("name", "Kitten")
                        .param("status", "pending")
                        .when()
                        .post("/pet/99")
                        .then()
                        .spec(response404)
                        .log().body()
                        .extract().response();

        Integer code = 404;
        String type = "unknown";
        String message = "not found";

        assertEquals(code, responseNonExistingPet.path("code"));
        assertEquals(type, responseNonExistingPet.path("type"));
        assertEquals(message, responseNonExistingPet.path("message"));
    }
}
