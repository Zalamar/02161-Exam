package project_management.test;

/**
 * @author Oliver
 *
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

public class RegisterUsedTime {

	private ManagementTool managementTool;
	private double usedTime;
	
	public RegisterUsedTime(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}

	@Given("^a worker adds an worker to the activity \"([^\"]*)\"$")
	public void aWorkerAddsAnWorkerToTheActivity(String activity) throws Exception {
		managementTool.selectActivity(activity);
		managementTool.addWorkerToActivity("AAAA");
	}

	@When("^a worker adds his used time (\\d+) h and (\\d+) min the activity$")
	public void aWorkerAddsHisUsedTimeHAndMinTheActivity(int hours, int min) throws Exception {
		double usedTime;
		if(min < 15 || min > 45) {
			usedTime = hours;
		} else {
			usedTime = hours + 0.5;
		}
		try {
			managementTool.addUsedTime(usedTime);
		} catch (UserNotLoggedIn e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoActivityIsSelectedException e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoProjectIsSelected e) {
			ErrorMessage.errorMessage = e.getMessage();
		}
	}

	@Then("^there are time register$")
	public void thereAreTimeRegister(List<List<String>> arg1) throws Exception {
		assertEquals(arg1, managementTool.getUsedTime());
	}

	@Given("^deselects the activity and project$")
	public void deselectsTheActivityAndProject() throws Exception {
		managementTool.deselectActivity();
		managementTool.deselectProject();
	}

	@When("^a worker edits a register time, adds (\\d+)$")
	public void aWorkerEditsARegisterTimeAdds(int arg1) throws Exception {
		managementTool.addUsedTime(arg1);
	}

	@Then("^the time is (\\d+)\\.(\\d+)$")
	public void theTimeIs(int time1, int time2) throws Exception {
		double time = time1 + time2/10;
		assertEquals("" + time, managementTool.getUsedTime().get(0).get(1));
	}

	@When("^a worker try to edits a register time, adds (\\d+)$")
	public void aWorkerTryToEditsARegisterTimeAdds(int usedTime) throws Exception {
		try {
			managementTool.addUsedTime(usedTime);
		} catch (UserNotLoggedIn e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoActivityIsSelectedException e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoProjectIsSelected e) {
			ErrorMessage.errorMessage = e.getMessage();
		}
	}

	@Given("^another project is created named \"([^\"]*)\"$")
	public void anotherProjectIsCreatedNamed(String name) throws Exception {
		managementTool.createProject(name);
	}

	@Then("^there are time register on the new activity$")
	public void thereAreTimeRegisterOnTheNewActivity(List<List<String>> arg1) throws Exception {
		assertEquals(arg1, managementTool.getUsedTime());
	}
	
	@When("^a worker wants to see workers \"([^\"]*)\" used time$")
	public void aWorkerWantsToSeeWorkersUsedTime(String username) throws Exception {
	    usedTime = managementTool.getUsedTime(username);
	}

	@Then("^he sees time (\\d+)\\.(\\d+)$")
	public void heSeesTime(int time1, int time2) throws Exception {
		double time = time1 + time2/10;
		assertEquals(time, usedTime, 0.01);
	}
}
