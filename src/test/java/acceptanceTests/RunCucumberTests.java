package acceptanceTests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".", tags = { "~@Ignore" }, monochrome = true, plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json" })
public class RunCucumberTests {
}
