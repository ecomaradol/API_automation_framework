package tests;

import io.restassured.response.Response;
import models.Pet;
import models.lombok.DataModel;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static specs.Specs.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetStoreTest extends TestBase {

    @Order(1)
    @Test
    @DisplayName("Add a new pet to the store")
    void addNewPet() {
        Pet pet = new Pet();
        pet.setId(123);
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
        Response response =
                given()
                        .spec(request)
                        .when()
                        .get("/pet/123")
                        .then()
                        .spec(response200)
                        .log().body()
                        .extract().response();

        Integer Id = 123;
        String name = "Kitten";
        String status = "sold";

        assertEquals(Id, response.path("id"));
        assertEquals(name, response.path("name"));
        assertEquals(status, response.path("status"));
    }


    //@Order(3)
    @Test
    @DisplayName("Find pet by status")
    void findPetByStatus() {
//        Pet pet = new Pet();
//       // pet.setStatus("sold");
//
        DataModel [] response =
                given()
                        .spec(request)
                        .when()
                        .get("/pet/findByStatus?status=sold")
                        .then()
                        .spec(response200)
                        .log().body()
                        .extract().as(DataModel[].class);

        Stream.of(response).filter(pet -> pet.getPet().getName().equals("Kitten"));

       // Integer id = 123;
//        String name = "Kitten";
//        String status = "sold";
        System.out.println(" !!!!!!" + Arrays.toString(response));
  //      assertThat(Arrays.toString(response)
      //  assertThat(response.toString()).contains("kuiuy") ;
    }
    @Order(3)
    @Test
    @DisplayName("Delete a pet")
    void deletePet() {

        Response response =
                given()
                        .spec(request)
                        .when()
                        .delete("/pet/123")
                        .then()
                        .spec(response200)
                        .log().body()
                        .extract().response();

        Integer code = 200;
        String type = "unknown";
        String message = "123";

        assertEquals(code, response.path("code"));
        assertEquals(type, response.path("type"));
        assertEquals(message, response.path("message"));
    }

    @Order(4)
    @Test
    @DisplayName("Find non-existing pet by ID")
    void findNonExistingPetById() {
        Response response =
                given()
                        .spec(request)
                        .when()
                        .get("/pet/123")
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
}
