package top.nextnet.resource;

import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AdoptionTest {
    Tamagotchi tamagotchi;

    @BeforeEach
    @Transactional
    public void setup() throws Exception{
        tamagotchi = new Tamagotchi("Kiri", 1);
    }
    @Test
    public void testCreationTamagotchi(){
        // Send POST request and extract the response status code
        int statusCode = given()
                .when()
                .post("/adoption/" + tamagotchi.getOwner() + "/create/" + tamagotchi.getName())
                .then()
                .statusCode(201) // Assert that the status code should be 201
                .extract()
                .response()
                .getStatusCode(); // Get the status code from the response

        // Print the response status and body
        System.out.println("Response Status Code: " + statusCode);

        assertEquals(201, statusCode);
    }
}


