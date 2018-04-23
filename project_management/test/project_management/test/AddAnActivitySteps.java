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
import project_management.app.exceptions.*;

public class AddAnActivitySteps {

	private ManagementTool managementTool;
	
	public AddAnActivitySteps(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}
	
	@Given("^a project is created named \"([^\"]*)\"$")
	public void aProjectIsCreatedNamed(String name) throws Exception {
		 managementTool.createProject(name);
	}

	@When("^a worker selecets the project \"([^\"]*)\"$")
	public void aWorkerSelecetsTheProject(String name) throws Exception {
		try {
			managementTool.selectProject(name);
		} catch (NoProjectWithThatName e) {
			ErrorMessage.errorMessage = e.getMessage();
	    }
	}

	@When("^a worker add an activity \"([^\"]*)\" to the project$")
	public void aWorkerAddAnActivityToTheProject(String name) throws Exception {
	    try {
	    	managementTool.addAnActivity(name);
	    } catch (UserNotLoggedIn e) {
	    	ErrorMessage.errorMessage = e.getMessage();
	    } catch (NoProjectIsSelected e) {
	    	ErrorMessage.errorMessage = e.getMessage();
	    }
	}

	@Then("^the activity \"([^\"]*)\" is now in the project$")
	public void theActivityIsNowInTheProject(String name) throws Exception {
	    assertTrue(managementTool.searchActivitys(name));
	}

	
}
