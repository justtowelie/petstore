package steps.libraries;

import enums.SessionVariables;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.CoreMatchers.containsString;


import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import pojos.PetStoreOrderRequestPojo;

@Slf4j
public class StoreSteps {

    public static void getInventory() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory").then().extract().response();
        Serenity.setSessionVariable(SessionVariables.RESPONSE_CODE.getKey()).to(response);
    }

    public static void getInventoryFailure() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/inventor").then().extract().response();
        Serenity.setSessionVariable(SessionVariables.RESPONSE_CODE.getKey()).to(response);
    }

    // Wanted to use a ResponsePojo to store the response and validate everything was correct but was running into errors
    // so skipping for now, would be a future implementation and would be the better practice
    public static void sendOrderRequest() {
        PetStoreOrderRequestPojo petStoreOrderRequestPojo = new PetStoreOrderRequestPojo();
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.filters(new RequestLoggingFilter(),
                new RequestLoggingFilter());
        Response response = given()
               .contentType(ContentType.JSON)
                .body(petStoreOrderRequestPojo)
                .when()
                .post("v2/store/order")
                .then()
                .using()
                .extract()
                .response();

        Integer petId = response.jsonPath().getInt("petId");
        Integer orderId = response.jsonPath().getInt("id");


        Serenity.setSessionVariable(SessionVariables.RESPONSE_CODE.getKey()).to(response);
//        PetStoreOrderResponsePojo petStoreOrderResponsePojo = then().extract().body().as(PetStoreOrderResponsePojo.class);
        Serenity.setSessionVariable(SessionVariables.ORDER_RESPONSE.getKey()).to(petId);
        Serenity.setSessionVariable(SessionVariables.ORDER_ID.getKey()).to(orderId);

        Assertions.assertEquals(2,petId);

    }

    // Sends the request without a petId, however this endpoint will return the default integer value of 0 in the response
    public static void sendOrderRequestFailure() {
        PetStoreOrderRequestPojo petStoreOrderRequestPojo = new PetStoreOrderRequestPojo(1);
        RestAssured.baseURI = "https://petstore.swagger.io/";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(petStoreOrderRequestPojo)
                .when()
                .post("v2/store/order")
                .then()
                .using()
                .extract()
                .response();

        Integer petId = response.jsonPath().getInt("petId");

        Serenity.setSessionVariable(SessionVariables.RESPONSE_CODE.getKey()).to(response);
        Serenity.setSessionVariable(SessionVariables.ORDER_RESPONSE.getKey()).to(petId);
        Assertions.assertEquals(0,petId);
    }

    public static void sendGetOrderRequest() {
        Integer petId = Serenity.sessionVariableCalled(SessionVariables.ORDER_RESPONSE.getKey());

        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.filters(new RequestLoggingFilter(),
                new RequestLoggingFilter());
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("v2/store/order/" + petId)
                .then()
                .using()
                .extract()
                .response();

        Serenity.setSessionVariable(SessionVariables.RESPONSE_CODE.getKey()).to(response);
        Serenity.setSessionVariable(SessionVariables.ORDER_RESPONSE.getKey()).to(petId);
        Assertions.assertEquals(2,petId);

    }

    public static void sendGetOrderRequestFailure() {
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter());
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("v2/store/order/" + 55)
                .then()
                .using()
                .extract()
                .response();

        Serenity.setSessionVariable(SessionVariables.RESPONSE_CODE.getKey()).to(response);
//        Serenity.setSessionVariable(SessionVariables.ORDER_RESPONSE.getKey()).to(petId);
//        Assertions.assertEquals(2,petId);

    }

    public static void sendDeleteOrderRequest() {
        Integer orderId = Serenity.sessionVariableCalled(SessionVariables.ORDER_ID.getKey());
        RestAssured.baseURI = "https://petstore.swagger.io/";

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("v2/store/order/" + orderId)
                .then()
                .using()
                .extract()
                .response();

        Serenity.setSessionVariable(SessionVariables.RESPONSE_CODE.getKey()).to(response);
    }

    public static void sendDeleteOrderRequestWithInvalidId()
    {
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter());
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("v2/store/order/" + 55)
                .then()
                .using()
                .extract()
                .response();

        Serenity.setSessionVariable(SessionVariables.RESPONSE_CODE.getKey()).to(response);
    }

    // Generic status code verification which takes in an integer at feature level to validate response code
    public static void checkStatusCode(int code) {
        Response response = Serenity.sessionVariableCalled(SessionVariables.RESPONSE_CODE.getKey());
        Assertions.assertEquals(code, response.statusCode());

    }

    // Validates response message using hamcrest
    public static void checkResponseMessage(String message) {
        Response response = Serenity.sessionVariableCalled(SessionVariables.RESPONSE_CODE.getKey());
        response.then().contentType(ContentType.JSON).body("message", containsString(message));

    }
}
