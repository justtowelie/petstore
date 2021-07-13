import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"api-tests/features/"},
        glue = {"definitions"},
        tags = { "@API"}
        )
public class PetStoreAPITestRunner
{

}
