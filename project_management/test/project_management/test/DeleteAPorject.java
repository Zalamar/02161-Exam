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

public class DeleteAPorject {

	private ManagementTool managementTool;

	public DeleteAPorject(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}

	@When("^the worker delete the project \"([^\"]*)\"$")
	public void theWorkerDeleteTheProject(String name) throws Exception {
		try {
			managementTool.deleteProject(name);
		} catch (UserNotLoggedIn e) {
			ErrorMessage.errorMessage = e.getMessage();
		}
	}

	@Then("^there are no project \"([^\"]*)\"$")
	public void thereAreNoProject(String name) throws Exception {
		assertFalse(managementTool.isthereAProjectWithThisName(name));
	}
}
