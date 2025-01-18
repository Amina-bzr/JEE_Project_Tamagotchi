package top.nextnet.resource;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import io.restassured.response.ValidatableResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;




@QuarkusTest
public class BoutiqueTest {
    ProductDTO product;
    Integer tamagotchiId;
    Integer tamagotchiUnavailableId;
    Integer tamagotchiNotEnoughBalance;
    Integer ecpensiveProduct; //Skin Fighter Tamagotchi : id=3

    @BeforeEach
    @Transactional
    public void setup() throws Exception{
        tamagotchiId = 5;
        tamagotchiUnavailableId = 8;
        tamagotchiNotEnoughBalance = 7;
        ecpensiveProduct = 3;
        product = new ProductDTO(8,tamagotchiId); //product 8 has quantity of 1, only 1st tamagotchi will be able to buy it
    }

    @Test
    public void testGetProducts() {
        ValidatableResponse response = given()
                .when()
                .get("http://localhost:8082/boutique/products?category=Accessoires")
                .then();
        int statusCode = response.extract().response().getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }

    @Test
    public void testBuyProduct() {
        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/boutique/" + product.getTamagotchiId() + "/achat/" + product.getId())
                .then();
        int statusCode = response.extract().response().getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }

    @Test
    public void testAlreadyPurchased() {
        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/boutique/" + product.getTamagotchiId() + "/achat/" + product.getId())
                .then();
        int statusCode = response.extract().response().getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }

    @Test
    public void testProductUnavailable() {
        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/boutique/" + tamagotchiUnavailableId + "/achat/" + product.getId())
                .then();
        int statusCode = response.extract().response().getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }

    @Test
    public void testNotEnoughBalance() {
        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/boutique/" + tamagotchiNotEnoughBalance + "/achat/" + ecpensiveProduct)
                .then();
        int statusCode = response.extract().response().getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }
}
