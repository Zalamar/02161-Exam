package project_management.test;
/**
 * @author Oliver
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
import project_management.app.exceptions.UserNotLoggedIn;

public class RegisterUsedTime {

	private ManagementTool managementTool;
	
	public RegisterUsedTime(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}
	
	@Given("^an activity \"([^\"]*)\" is added to the project$")
	public void anActivityIsAddedToTheProject(String name) throws Exception {
		 managementTool.addAnActivity(name);
	    throw new PendingException();
	}

	@Given("^a project manager is added to the project$")
	public void aProjectManagerIsAddedToTheProject(String name) throws Exception {
	    managementTool.addProjectManager(name);
	    throw new PendingException();
	}

	@Given("^the worker is added to the acitivity$")
	public void theWorkerIsAddedToTheAcitivity() throws Exception {
	    
	    throw new PendingException();
	}

	@When("^a worker selecets the activity \"([^\"]*)\"$")
	public void aWorkerSelecetsTheActivity(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^a worker adds his used time (\\d+) the activity$")
	public void aWorkerAddsHisUsedTimeTheActivity(int arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^there are time register$")
	public void thereAreTimeRegister() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^a worker adds his used time the activity$")
	public void aWorkerAddsHisUsedTimeTheActivity() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^deselects the activity and project$")
	public void deselectsTheActivityAndProject() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^a worker edits a register time, adds (\\d+)$")
	public void aWorkerEditsARegisterTimeAdds(int arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the time is (\\d+)$")
	public void theTimeIs(int arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^a worker edits a register time$")
	public void aWorkerEditsARegisterTime() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^another project is created named \"([^\"]*)\"$")
	public void anotherProjectIsCreatedNamed(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^an activity (\\d+) is added to the new project$")
	public void anActivityIsAddedToTheNewProject(int arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^there are time register on the new activity$")
	public void thereAreTimeRegisterOnTheNewActivity() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
