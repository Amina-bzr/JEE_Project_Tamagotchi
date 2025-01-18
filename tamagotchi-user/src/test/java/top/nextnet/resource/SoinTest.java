package top.nextnet.resource;

//import fr.pantheonsorbonne.ufr27.miage.dto.NotificationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
//import fr.pantheonsorbonne.ufr27.miage.dto.TreatmentDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class SoinTest {
    TamagotchiDTO tamagotchi;
//    NotificationDTO Neednotification;
//    NotificationDTO Sicknotification;
//    NotificationDTO Deathnotification;
//    TreatmentDTO treatment;
    Integer id;
    Integer idOwner;

    @BeforeEach
    @Transactional
    public void setup() throws Exception{
        // id : 1 = mort / 2 = malnutrition / 3 = obesity / 4 = no owner
        id = 2;
        // owner : 1 => id 1 / 2 => id 2 et 3
        idOwner = 2;
    }

    @Test
    public void testFeedTamagotchi() {
        System.out.println("Testing with URL: /soin/feed/" + id);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/feed/" + id + "?points=10")
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(200, statusCode);
    }
    @Test
    public void testErrorFeedTamagotchi() {
        Integer id=1;
        System.out.println("Testing with URL: /soin/feed/" + id);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/feed/" + id + "?points=10")
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(400, statusCode);
    }


    @Test
    public void testHydrateTamagotchi() {
        System.out.println("Testing with URL: /soin/hydrate/"+id);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/hydrate/"+id+"?points=10")
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(200, statusCode);
    }


    @Test
    public void testErrorHydrateTamagotchi() {
        Integer id=1;
        System.out.println("Testing with URL: /soin/hydrate/"+id);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/hydrate/"+id+"?points=10")
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(400, statusCode);
    }

    @Test
    public void testEnergizeTamagotchi() {
        System.out.println("Testing with URL: /soin/energize/"+id);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/energize/"+id+"?points=10")
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(200, statusCode);
    }

    @Test
    public void testErrorEnergizeTamagotchi() {
        Integer id=1;
        System.out.println("Testing with URL: /soin/energize/"+id);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/energize/"+id+"?points=10")
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(400, statusCode);
    }

    @Test
    public void testGetNotificationByOwner() {
        //Integer owner = tamagotchi.getOwner();
        //PUT request + response extraction

        System.out.println("Testing with URL: /soin/notifications/"+idOwner);
        ValidatableResponse response = given()
                .when()
                .get("http://localhost:8082/soin/notifications/"+idOwner)
                .then();

        //extract status
        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());
        assertEquals(200, statusCode);
    }

    @Test
    public void testGetTreatmentsForTamagotchii() {
        Integer id = 3;

        System.out.println("Testing with URL: /soin/treatments/treat?tamagotchiId="+id);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/treatments/treat?tamagotchiId="+id)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(200, statusCode);

    }

    @Test
    public void testErrorGetTreatmentsForTamagotchii() {
        Integer id =4;
        System.out.println("Testing with URL: /soin/treatments/treat?tamagotchiId="+id);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/treatments/treat?tamagotchiId="+id)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(404, statusCode);

    }

    @Test
    public void testApplyTreatment() {
        //Integer id = tamagotchi.getId();
        Integer idTreatment = 1;

        System.out.println("Testing with URL: /soin/treatments/apply?tamagotchiId="+id+"&treatmentId="+idTreatment);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/treatments/apply?tamagotchiId="+id+"&treatmentId="+idTreatment)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(200, statusCode);
    }

    @Test
    public void testErrorTreatmentApplyTreatment() {
        //Integer id = tamagotchi.getId();
        Integer idTreatment = 7;

        System.out.println("Testing with URL: /soin/treatments/apply?tamagotchiId="+id+"&treatmentId="+idTreatment);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/treatments/apply?tamagotchiId="+id+"&treatmentId="+idTreatment)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(400, statusCode);
    }

    @Test
    public void testErrorTamagotchiApplyTreatment() {
        Integer id = 7;
        Integer idTreatment = 1;

        System.out.println("Testing with URL: /soin/treatments/apply?tamagotchiId="+id+"&treatmentId="+idTreatment);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json") // Ajoute le Content-Type
                .header("Accept", "application/json")       // Ajoute le Accept
                .when()
                .post("http://localhost:8082/soin/treatments/apply?tamagotchiId="+id+"&treatmentId="+idTreatment)
                .then();

        int statusCode = response.extract().response().getStatusCode();

        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.extract().response().getBody().asString());

        assertEquals(400, statusCode);
    }

}