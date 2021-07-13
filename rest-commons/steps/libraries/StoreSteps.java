package steps.libraries;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;


public class StoreSteps {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/store";
    }

    @Step
    public void getInventory()
    {
        String endpoint = "/inventory";
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }
}
