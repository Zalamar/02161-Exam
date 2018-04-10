package project_management.test;

/**
 * @author Alex
 *
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import project_management.app.ManagementTool;
import project_management.app.exceptions.*;

public class AddAProjectManagerSteps {

	private ManagementTool managementTool;
	
	public AddAProjectManagerSteps(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}

	@Given("^another worker is register in the company$")
	public void anotherWorkerIsRegisterInTheCompany() throws Exception {
		managementTool.addWorker("AAAB", "Person 2");
	}

	@When("^a worker assign the project a project manager \"([^\"]*)\"$")
	public void aWorkerAssignTheProjectAProjectManager(String name) throws Exception {
		try {
			managementTool.addProjectManager(name);
		} catch (TheProjectAlreadyHaveAManager e) {
			CreatAProjectSteps.errorMessage = e.getMessage();
		} catch (UserNotLoggedIn e) {
			CreatAProjectSteps.errorMessage = e.getMessage();
		} catch (NoProjectIsSelected e) {
			CreatAProjectSteps.errorMessage = e.getMessage();
		}
	}

	@Then("^the project have a project manager \"([^\"]*)\"$")
	public void theProjectHaveAProjectManager(String name) throws Exception {
		assertEquals(name, managementTool.getSelectedProject().getManager().getUsername());
	}

	@Given("^a project manager \"([^\"]*)\" is assign to the project$")
	public void aProjectManagerIsAssignToTheProject(String name) throws Exception {
		managementTool.addProjectManager(name);
	}

	@When("^a worker remove a project manager$")
	public void aWorkerRemoveAProjectManager() throws Exception {
		try {
			managementTool.removeProjectManager();
		} catch (UserNotLoggedIn e) {
			CreatAProjectSteps.errorMessage = e.getMessage();
		} catch (NoProjectIsSelected e) {
			CreatAProjectSteps.errorMessage = e.getMessage();
		}
	}

	@Then("^the project manager is removed$")
	public void theProjectManagerIsRemoved() throws Exception {
		assertFalse(managementTool.getSelectedProject().hasAManager());
	}
}
