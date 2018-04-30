package project_management.test;

/**
 * @author 
 *
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import project_management.app.ManagementTool;
import project_management.app.exceptions.*;

public class SeeRemaingActivitys {

	private ManagementTool managementTool;
	private List<String> unregisterActivitys;

	public SeeRemaingActivitys(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}

	@Given("^a worker deselect the activity$")
	public void aWorkerDeselectTheActivity() throws Exception {
		managementTool.deselectActivity();
	}

	@When("^a worker clicks See unregister time used on activitys$")
	public void aWorkerClicksSeeUnregisterTimeUsedOnActivitys() throws Exception {
		unregisterActivitys = managementTool.unregisterActivitys();
	}

	@Then("^a worker sees$")
	public void aWorkerSees(List<String> arg1) throws Exception {
		assertEquals(arg1, unregisterActivitys);
	}

	@When("^a worker try to click see unregister time used on activitys$")
	public void aWorkerTryToClickSeeUnregisterTimeUsedOnActivitys() throws Exception {
		try {
		unregisterActivitys = managementTool.unregisterActivitys();
		} catch (UserNotLoggedIn e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoProjectIsSelected e) {
			ErrorMessage.errorMessage = e.getMessage();
		}
	}

	@Given("^a worker makes (\\d+) more activitys in project \"([^\"]*)\"$")
	public void aWorkerMakesMoreActivitysInProject(int number, String projectName) throws Exception {
		managementTool.selectProject(projectName);
		for (int i = 3; i < number + 3; i++) {
			String name = i < 10 ? "00" + i : "0" + i;
			System.out.println(name);
			managementTool.addAnActivity(name);
		}
	}
}
