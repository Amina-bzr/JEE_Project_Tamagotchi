package top.nextnet.resource;

import io.restassured.response.ValidatableResponse;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BankingResourcesTest {

    @Test
    public void testTransfer() {
        Integer tamagotchiId = 10; // Exemple d'ID source
        Integer tamagotchiIdDest = 11; // Exemple d'ID destination
        double amount = 50.0;

        System.out.println("Testing transfer with URL: /bancaire/" + tamagotchiId + "/transfer/" + tamagotchiIdDest + "/" + amount);
        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/bancaire/" + tamagotchiId + "/transfer/" + tamagotchiIdDest + "/" + amount)
                .then();

        int statusCode = response.extract().statusCode();
        String responseBody = response.extract().response().getBody().asString();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertEquals(200, statusCode, "The transfer should succeed.");
    }

    @Test
    public void testGetBalance() {
        Integer tamagotchiId = 12; // Exemple d'ID


        System.out.println("Testing balance with URL: /bancaire/" + tamagotchiId + "/balance");

        ValidatableResponse response = given()
                .when()
                .get("http://localhost:8082/bancaire/" + tamagotchiId + "/balance")
                .then();

        int statusCode = response.extract().statusCode();
        String responseBody = response.extract().response().getBody().asString();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertEquals(200, statusCode, "Fetching balance should succeed.");
    }
}
