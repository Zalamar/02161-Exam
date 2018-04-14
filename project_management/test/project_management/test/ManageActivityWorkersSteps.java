package project_management.test;

import static org.junit.Assert.assertEquals;

/**
 * @author Tobias
 *
 */

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Tobias
 *
 */

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import project_management.app.ManagementTool;
import project_management.app.exceptions.UserNotLoggedIn;

public class ManageActivityWorkersSteps {
	
	private ManagementTool managementTool;
	private String errorMessage;
	
	public ManageActivityWorkersSteps(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}
	
	@Given("^a worker is added to the project$")
	public void aWorkerIsAddedToTheProject() throws Exception {
	    managementTool.addWorkerToProject("AAAA");
	}
	
	@When("^a worker adds an worker to the activity$")
	public void aWorkerAddsAnWorkerToTheActivity() throws Exception {
		try {
			managementTool.addWorkerToActivity("AAAA");
		} catch (UserNotLoggedIn e) {
			errorMessage = e.getMessage();
		}
	}
	
	@Then("^the worker is added to the activity$")
	public void theWorkerIsAddedToTheActivity() throws Exception {
	    assertNotNull(managementTool.getSelectedActivity().searchWorkerList("AAAA"));
	}
	
	@Then("^I get the manageActivityWorkers error message \"([^\"]*)\"$")
	public void iGetTheManageActivityWorkersErrorMessage(String arg1) throws Exception {
	    assertEquals(errorMessage, arg1);
	}

	
}
