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
        tamagotchi = new TamagotchiDTO("Kiri", 1);
    }

    @Test
    public void testCreationTamagotchi() {
        // Make sure the owner and name are valid values
        Integer owner = tamagotchi.getOwner();
        String name = tamagotchi.getName();

        // Debugging step: Print out the generated URL
        System.out.println("Testing with URL: /adoption/" + owner + "/create/" + name);

        // Send POST request and extract the response
        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/adoption/" + owner + "/create/" + name)
                .then();

        // Extract the status code from the response
        int statusCode = response.extract().response().getStatusCode();

        // Print the response status and body for debugging
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        // Assert the status code is 201 (created)
        assertEquals(201, statusCode);
    }

}


