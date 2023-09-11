import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:cucumber.json"},
        features = "src/test/java/features",
        tags = "@mybooking",
        glue = {"pages","features.step_definitions"})

public class RunnerTest {
}
