package cucmberRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
            features = "src/test/java/features/PlaceValidations.feature",
            plugin = "json:target/json-reports/cucumber--reports.json",
            glue = {"stepDefinitions"}
            //tags = "@DeletePlace"
        )

public class TestRunner {
}
