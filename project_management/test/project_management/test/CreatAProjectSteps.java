package project_management.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import project_management.app.ManagementTool;
import project_management.app.Project;
import project_management.app.exceptions.UserIsNotLoggedIn;
import project_management.app.exceptions.UserNotLoggedIn;

public class CreatAProjectSteps {

	private ManagementTool managementTool;
	private String errorMessage;
	
	public CreatAProjectSteps(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}
	
	@Given("^a worker is register in the company$")
	public void aWorkerIsRegisterInTheCompany() throws Exception {
	    managementTool.addWorker("AAAA", "Person 1");
	}

	@Given("^a worker is logged in$")
	public void aWorkerIsLoggedIn() throws Exception {
	    managementTool.login("AAAA");
	}

	@When("^the worker creat a project (\\d+)$")
	public void theWorkerCreatAProject(int name) throws Exception {
	    managementTool.creatProject(name);
	}

	@Then("^the project (\\d+) is created$")
	public void theProjectIsCreated(int name) throws Exception {
	    assertTrue(managementTool.searchProjects(name));
	}

	@Given("^that no worker is logged in$")
	public void thatNoWorkerIsLoggedIn() throws Exception {
	    managementTool.logout();
	}

	@When("^they try to creat a project (\\d+)$")
	public void theyTryToCreatAProject(int name) throws Exception {
	    try {
	    	managementTool.creatProject(name);
	    } catch (UserNotLoggedIn e) {
	    	errorMessage = e.getMessage();
	    }
	}

	@Then("^I get the error message \"([^\"]*)\"$")
	public void iGetTheErrorMessage(String errorMessage) throws Exception {
	    assertEquals(errorMessage, this.errorMessage);
	}
}
