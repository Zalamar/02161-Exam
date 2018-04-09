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

public class CreatAProjectSteps {

	private ManagementTool managementTool;
	
	public CreatAProjectSteps(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}
	
	@Given("^a worker is register in the company$")
	public void aWorkerIsRegisterInTheCompany() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a worker is logged in$")
	public void aWorkerIsLoggedIn() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^the worker creat a project (\\d+)$")
	public void theWorkerCreatAProject(int name) throws Exception {
	    managementTool.creatProject(name);
	}

	@Then("^the project is created$")
	public void theProjectIsCreated() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^that no worker is logged in$")
	public void thatNoWorkerIsLoggedIn() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^they try to creat a project (\\d+)$")
	public void theyTryToCreatAProject(int arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I get the error message \"([^\"]*)\"$")
	public void iGetTheErrorMessage(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
