package steps.definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.libraries.StoreSteps;

public class StoreStepDefinitions {


    @Given( "the Admin performs a get inventory request" )
    public void getInventoryRequest()
    {
        StoreSteps.getInventory();
    }

    @Given( "the Admin performs a get inventory request and it fails" )
    public void getInventoryRequestFailure()
    {
        StoreSteps.getInventoryFailure();
    }

    @Given("the Admin sends a request to place an order")
    public void sendOrderRequest()
    {
        StoreSteps.sendOrderRequest();
    }

    @Given("the Admin sends a request to place an order and it fails")
    public void sendOrderRequestFailure()
    {
        StoreSteps.sendOrderRequestFailure();
    }

    @Given("the Admin sends a request to get an order by orderId")
    public void sendGetOrderRequest()
    {
        StoreSteps.sendGetOrderRequest();
    }

    @Given("the Admin gets order of invalid orderId")
    public void sendGetOrderRequestFailure()
    {
        StoreSteps.sendGetOrderRequestFailure();
    }

    @Then("the Admin sends a request to delete an order by orderId")
    public void sendDeleteOrderRequest()
    {
        StoreSteps.sendDeleteOrderRequest();
    }

    @Then("the Admin attempts to delete an non-existing orderId")
    public void sendDeleteOrderRequestWithInvalidId()
    {
        StoreSteps.sendDeleteOrderRequestWithInvalidId();
    }

    @Then( "confirm result code is {int}" )
    public void getStatusCode(int code)
    {
        StoreSteps.checkStatusCode(code);
    }

    @Then( "confirm message returned is {string}" )
    public void getResponseMessage(String message)
    {
        StoreSteps.checkResponseMessage(message);
    }

}
