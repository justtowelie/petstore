package steps.definitions;

import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import steps.libraries.StoreSteps;

public class StoreStepDefinitions {

    @Steps
    StoreSteps storeSteps;

    @Given ( "the Admin performs a get inventory request" )
    public void getInventoryRequest()
    {
        storeSteps.getInventory();
    }

}
