package top.nextnet.resource;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import io.restassured.response.ValidatableResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;




@QuarkusTest
public class BoutiqueTest {
    ProductDTO product;
    TamagotchiDTO tamagotchi;

    @BeforeEach
    @Transactional
    public void setup() throws Exception{
        tamagotchi = new TamagotchiDTO("TamaBoutique", 1);
        product = new ProductDTO(1,1);
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
/*
    @Test
    public void testBuyProduct() {
        ValidatableResponse response = given()
                .when()
                .post("http://localhost:8082/boutique/" + product.getTamagotchiId() + "/achat/" + product.getProductId())
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(200, statusCode);
    }
*/
}
