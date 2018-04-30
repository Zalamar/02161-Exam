package project_management.test;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Then;

public class ErrorMessage {
	public static String errorMessage = "";
	
	@Then("^I get the error message \"([^\"]*)\"$")
	public void iGetTheErrorMessage(String errorMessage_in) throws Exception {
	    assertEquals(errorMessage_in, errorMessage);
	    errorMessage = "";
	}

	@Then("^I get no errors$")
	public void iGetNoErrors() throws Exception {
	    assertEquals(errorMessage, "");
	}
	
}
