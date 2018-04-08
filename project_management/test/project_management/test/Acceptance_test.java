package project_management.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "use_case",
		plugin = { "html:target/cucumber/wikipedia.html"},
		monochrome=true,
		snippets = SnippetType.CAMELCASE,
		glue = { "acceptance_test"})
public class Acceptance_test {
	
}

