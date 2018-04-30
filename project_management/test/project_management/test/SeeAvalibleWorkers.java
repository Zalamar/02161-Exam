package project_management.test;

//Alex

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
import project_management.app.exceptions.ActivityNotFoundException;
import project_management.app.exceptions.NoActivityIsSelectedException;
import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.NoProjectWithThatName;
import project_management.app.exceptions.NoWorkerAvailble;
import project_management.app.exceptions.TheProjectAlreadyHaveAManager;
import project_management.app.exceptions.UserNotLoggedIn;
import project_management.app.exceptions.startDateAfterEndDateException;

public class SeeAvalibleWorkers {

	private ManagementTool managementTool;
	private List<List<String>> availableWorkers;

	public SeeAvalibleWorkers(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}

	@Given("^(\\d+) worker is register in the company$")
	public void workerIsRegisterInTheCompany(int numberOfWorkers, List<List<String>> names) throws Exception {
		for (List<String> name : names) {
			managementTool.addWorker(name.get(0), name.get(1));
		}
	}

	@Given("^they all have an personal activity$")
	public void theyAllHaveAnPersonalActivity(List<List<String>> things) throws Exception {
		for (List<String> thing : things) {
			managementTool.login(thing.get(0));
			String activityName = thing.get(1);
			String date1String = thing.get(2);
			GregorianCalendar date1 = makeDate(date1String);
			if (thing.get(3).length() > 0) {
				String date2String = thing.get(3);
				GregorianCalendar date2 = makeDate(date2String);
				managementTool.addPersonalActivity(activityName, date1, date2);
			} else {
				managementTool.addPersonalActivity(activityName, date1);
			}
			managementTool.logout();
		}
	}

	private GregorianCalendar makeDate(String dateString) {
		String day = dateString.substring(0, 2);
		String month = dateString.substring(2, 4);
		String year = "20" + dateString.substring(4, 6);
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);

		date.clear(Calendar.MILLISECOND);
		return date;
	}

	@Given("^a project manager \"([^\"]*)\" is added to the project$")
	public void aProjectManagerIsAddedToTheProject(String username) throws Exception {
		managementTool.addProjectManager(username);

	}

	@Given("^the project manager is logged in$")
	public void theProjectManagerIsLoggedIn() throws Exception {
		managementTool.login("AAAA");
	}

	@Given("^the project manager selecets the project \"([^\"]*)\"$")
	public void theProjectManagerSelecetsTheProject(String name) throws Exception {
		managementTool.selectProject(name);
	}

	@Given("^the project manager adds the start time \"([^\"]*)\" and end time \"([^\"]*)\" to the activity \"([^\"]*)\"$")
	public void theProjectAddsTheStartTimeAndEndTimeToTheActivity(String start, String end, String name)
			throws Exception {
		managementTool.selectActivity(name);
		String dateString1 = start;
		GregorianCalendar date1 = makeDate(dateString1);
		managementTool.addActivityStartDate(date1);
		String dateString2 = end;
		GregorianCalendar date2 = makeDate(dateString2);
		managementTool.addActivityEndDate(date2);
	}

	@When("^the project manager selects the project \"([^\"]*)\" and acticity \"([^\"]*)\"$")
	public void theProjectManagerSelectsTheProjectAndActicity(String nameP, String nameA) throws Exception {
		managementTool.selectProject(nameP);
		managementTool.selectActivity(nameA);

	}

	@When("^the project manager selects see available workers$")
	public void theProjectManagerSelectsSeeAvailableWorkers() throws Exception {
		availableWorkers = managementTool.getWhosAvailable();
	}

	@Then("^he will se whos is avalible$")
	public void heWillSeWhosIsAvalible(List<List<String>> testLines) throws Exception {
		int i = 0;
		for (List<String> line : testLines) {
			assertEquals(line, availableWorkers.get(i));
			i++;
		}
	}

	@Given("^they all have more personal activity$")
	public void theyAllHaveMorePersonalActivity(List<List<String>> activitys) throws Exception {
		theyAllHaveAnPersonalActivity(activitys);
	}

	@When("^the trys project manager selects see available workers$")
	public void theTrysProjectManagerSelectsSeeAvailableWorkers() throws Exception {
		try {
			availableWorkers = managementTool.getWhosAvailable();
		} catch (NoWorkerAvailble e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (UserNotLoggedIn e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoProjectIsSelected e) {
			ErrorMessage.errorMessage = e.getMessage();
		} catch (NoActivityIsSelectedException e) {
			ErrorMessage.errorMessage = e.getMessage();
		}
	}

	@When("^that no project mangager is logged in$")
	public void thatNoProjectMangagerIsLoggedIn() throws Exception {
		managementTool.logout();
	}

	@When("^the project manager deselects the project$")
	public void theProjectManagerDeselectsThePorject() throws Exception {
		managementTool.deselectProject();
	}

	@When("^the project manager selects see available workers, with (\\d+) % off limit$")
	public void theProjectManagerSelectsSeeAvailableWorkersWithOffLimit(int number) throws Exception {
		availableWorkers = managementTool.getWhosAvailable(number);
	}

	@When("^the trys project manager selects see available workers, with (\\d+) % days off$")
	public void theTrysProjectManagerSelectsSeeAvailableWorkersWithDaysOff(int number) throws Exception {
		try {
			availableWorkers = managementTool.getWhosAvailable(number);
		} catch (NoWorkerAvailble e) {
			ErrorMessage.errorMessage = e.getMessage();
		}
	}

}
