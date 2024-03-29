package project_management.test;
/**
 * @author Alex
 *
 */
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
import project_management.app.exceptions.ProjectAlreadyExits;
import project_management.app.exceptions.UserNotLoggedIn;


public class CreatAProjectSteps {

	private ManagementTool managementTool;
	
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

	@When("^the worker creat a project \"([^\"]*)\"$")
	public void theWorkerCreatAProject(String name) throws Exception {
	    managementTool.createProject(name);
	}

	@Then("^the project \"([^\"]*)\" is created$")
	public void theProjectIsCreated(String name) throws Exception {
	    assertTrue(managementTool.isthereAProjectWithThisName(name));
	}

	@Given("^that no worker is logged in$")
	public void thatNoWorkerIsLoggedIn() throws Exception {
	    managementTool.logout();
	}

	@When("^they try to creat a project \"([^\"]*)\"$")
	public void theyTryToCreatAProject(String name) throws Exception {
	    try {
	    	managementTool.createProject(name);
	    } catch (UserNotLoggedIn e) {
	    	ErrorMessage.errorMessage = e.getMessage();
	    } catch (ProjectAlreadyExits e) {
	    	ErrorMessage.errorMessage = e.getMessage();
	    }
	}

	
}
