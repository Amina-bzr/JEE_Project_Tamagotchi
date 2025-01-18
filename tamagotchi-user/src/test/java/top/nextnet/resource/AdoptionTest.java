package top.nextnet.resource;

import io.restassured.response.ValidatableResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;




@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdoptionTest {
    TamagotchiDTO tamagotchi;
    TamagotchiDTO tamagotchiOwnerNotFound;
    Integer ownerId1 = 1;
    Integer ownerId2 = 2;
    Integer notFoundTamagotchi = 1000;
    Integer hasOwnerTamagotchi = 5;
    Integer tamWithoutOwner = 8;

  @BeforeEach
    @Transactional
    public void setup() throws Exception{
        tamagotchi = new TamagotchiDTO("Kiri", ownerId1, 1);
        tamagotchiOwnerNotFound = new TamagotchiDTO("Kiri", 1000, 100);
    }

    @Test
    @Order(1)
    public void testCreationTamagotchi() {
        Integer owner = tamagotchi.getOwner();
        String name = tamagotchi.getName();

        System.out.println("Testing with URL: /adoption/" + owner + "/create/" + name);

        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/adoption/" + owner + "/create/" + name)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(201, statusCode);
    }

    @Test
    @Order(2)
    public void testGetTamagotchis() {
        //PUT request + response extraction
        ValidatableResponse response = given()
                .when()
                .get("http://localhost:8082/adoption/tamagotchis?hasOwner=true")
                .then();

        //extract status
        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }

    @Test
    public void testCreationTamagotchiWithOwnerNotFound() {
        Integer owner = tamagotchiOwnerNotFound.getOwner();
        String name = tamagotchiOwnerNotFound.getName();

        System.out.println("Testing with URL: /adoption/" + owner + "/create/" + name);

        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/adoption/" + owner + "/create/" + name)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(404, statusCode);
    }



    @Test
    public void testAdoptTamagotchi() {
        Integer owner = tamagotchi.getOwner();
        Integer id = tamagotchi.getId();
        System.out.println("Testing with URL: /adoption/" + owner + "/adopt/" + tamWithoutOwner);
        ValidatableResponse response = given()
                .when()
                .put("http://localhost:8082/adoption/" + owner + "/adopt/" + tamWithoutOwner)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }

    @Test
    public void testAdoptTamagotchiHasOwner() {
        System.out.println("Testing with URL: /adoption/" + ownerId2 + "/adopt/" + hasOwnerTamagotchi);

        ValidatableResponse response = given()
                .when()
                .put("http://localhost:8082/adoption/" + ownerId2 + "/adopt/" + hasOwnerTamagotchi)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(409, statusCode); //conflict
    }

    @Test
    public void testAdoptTamagotchiNotFound() {
        System.out.println("Testing with URL: /adoption/" + ownerId1 + "/adopt/" + notFoundTamagotchi);

        ValidatableResponse response = given()
                .when()
                .put("http://localhost:8082/adoption/" + ownerId1 + "/adopt/" + notFoundTamagotchi)
                .then();

        //extract status
        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(404, statusCode);
    }


}



