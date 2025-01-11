package top.nextnet.resource;

import io.restassured.response.ValidatableResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;




@QuarkusTest
public class AdoptionTest {
    TamagotchiDTO tamagotchi;

    @BeforeEach
    @Transactional
    public void setup() throws Exception{
        tamagotchi = new TamagotchiDTO("Kiri", 1, 1);
    }

    @Test
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
    public void testAdoptTamagotchi() {
        Integer owner = tamagotchi.getOwner();
        Integer id = tamagotchi.getId();
        System.out.println("Testing with URL: /adoption/" + owner + "/adopt/" + id);
        //PUT request + response extraction
        ValidatableResponse response = given()
                .when()
                .put("http://localhost:8082/adoption/" + owner + "/adopt/" + id)
                .then();

        //extract status
        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }

    @Test
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

}



