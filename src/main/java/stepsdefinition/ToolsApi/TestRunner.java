package stepsdefinition.ToolsApi;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		 features = "src/main/java/feature",
		   glue = {"stepsdefinition.ToolsApi"},
		   plugin = {"pretty",
			        "html:target/cucumber-report.html",
			        "json:target/cucumber.json"})
public class TestRunner {

}
