
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"api-tests/features/"},
        glue = {"definitions"},
        tags = { "@API"}
        )
public class PetStoreAPITestRunner
{

}
