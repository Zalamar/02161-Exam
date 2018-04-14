package project_management.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Tobias
 *
 */

import static org.junit.Assert.assertNull;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import project_management.app.ManagementTool;
import project_management.app.exceptions.*;

public class RemoveActivitySteps {

	private ManagementTool managementTool;
	private String errorMessage;
	
	public RemoveActivitySteps(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}
	
	@When("^a worker selecets an activity \"([^\"]*)\"$")
	public void aWorkerSelecetsAnActivity(String arg1) throws Exception {
	    try {
	    	managementTool.selectActivity(arg1);
	    } catch (ActivityNotFoundException e) {
	    	errorMessage = e.getMessage();
	    }
	}
	
	@When("^a worker deletes the activity$")
	public void aWorkerDeletesTheActivity() throws Exception {
	    try {
	    	managementTool.deleteActivity();
	    } catch (ActivityNotFoundException e) {
	    	errorMessage = e.getMessage();
	    }
	}
	
	@Then("^there aint no activity$")
	public void thereAintNoActivity() throws Exception {
	    assertFalse(managementTool.searchActivitys("001"));
	}
	
	@When("^a worker is logged out$")
	public void theWorkerIsLoggedOut() throws Exception {
	    managementTool.logout();
	}
	
	@When("^a worker tries to delete the activity$")
	public void aWorkerTriesToDeleteTheActivity() throws Exception {
	    try {
	    	managementTool.deleteActivity();
	    } catch (UserNotLoggedIn e) {
	    	errorMessage = e.getMessage();
	    }
	}
	
	@Then("^I get removeActivity error message \"([^\"]*)\"$")
	public void iGetRemoveActivityErrorMessage(String arg1) throws Exception {
	    assertEquals(arg1, errorMessage);
	}
	
	@When("^activity is removed from project$")
	public void activityIsRemovedFromProject() throws Exception {
	    managementTool.getSelectedProject().deleteActivity(managementTool.getSelectedActivity());
	}
	
}
