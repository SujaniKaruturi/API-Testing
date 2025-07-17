package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//Running with JUnit Framework
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Features" , glue={"StepDefnitions"} ,tags="@AddPlace" , plugin = "json:taget/jsonReports/cucumber-report.json")
public class TestRunner  
{

	
}
