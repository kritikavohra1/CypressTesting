package cucumber.Options;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/placeValidations.feature",
        plugin = "json:target/jsonReports/cucumber-report.json",
        glue = {"stepDefinations"}

)
public class TestRunner {

    //tags = {"@DeletePlace"}
}
