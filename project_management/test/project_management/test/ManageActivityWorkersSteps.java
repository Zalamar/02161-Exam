package project_management.test;

/**
 * @author Tobias
 *
 */

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import project_management.app.ManagementTool;
import project_management.app.exceptions.NoActivityIsSelectedException;
import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.UserNotLoggedIn;
import project_management.app.exceptions.workerNotOnProjectException;

public class ManageActivityWorkersSteps {
	
	private ManagementTool managementTool;
	
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
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoProjectIsSelected e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (workerNotOnProjectException e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoActivityIsSelectedException e) {
			ErrorMessage.errorMessage = e.getMessage();
		}
	}
	
	@Then("^the worker is added to the activity$")
	public void theWorkerIsAddedToTheActivity() throws Exception {
	    assertNotNull(managementTool.getSelectedActivity().searchWorkerList("AAAA"));
	}
	
	@When("^a worker deselecets a project$")
	public void aWorkerDeselecetsAProject() throws Exception {
	    managementTool.deselectProject();
	}
	
	@When("^a worker is removed from project$")
	public void aWorkerIsRemovedFromProject() throws Exception {
	    managementTool.removeWorker("AAAA");
	}
	
}
