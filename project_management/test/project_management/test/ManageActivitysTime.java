package project_management.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Tobias
 *
 */

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import project_management.app.ManagementTool;
import project_management.app.exceptions.*;

public class ManageActivitysTime {
	
	private ManagementTool managementTool;
	private String errorMessage;
	
	public ManageActivitysTime(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}
	
	@When("^a worker adds estimated time (\\d+) to the activity$")
	public void aWorkerAddsEstimatedTimeToTheActivity(int arg1) throws Exception {
		try {
			managementTool.addTimeToActivity(arg1);
		} catch (UserNotLoggedIn e) {
			errorMessage = e.getMessage();
		}
	}
	
	@Then("^there are time (\\d+) register to the activity$")
	public void thereAreTimeRegisterToTheActivity(int arg1) throws Exception {
	    assertEquals(managementTool.getActivityTime(), arg1);
	}
	
	@Then("^I get the manageActivityTime error message \"([^\"]*)\"$")
	public void iGetTheManageActivityTimeErrorMessage(String arg1) throws Exception {
	    assertEquals(arg1, errorMessage);
	}

	@When("^a worker added the start date (\\d+)$")
	public void aWorkerAddedTheStartDate(int arg1) throws Exception { // All of this will be handled client side
		String dateString = Integer.toString(arg1);
		
		String day = dateString.substring(0, 1);
		String month = dateString.substring(2, 3);
		String year = "20" + dateString.substring(4, 5);
		
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date.clear(Calendar.MILLISECOND);
		
	    managementTool.addActivityStartDate(date);
	}
	
	@When("^a worker added the end date (\\d+)$")
	public void aWorkerAddedTheEndDate(int arg1) throws Exception { // Again client side
		String dateString = Integer.toString(arg1);
		
		String day = dateString.substring(0, 1);
		String month = dateString.substring(2, 3);
		String year = "20" + dateString.substring(4, 5);
		
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date.clear(Calendar.MILLISECOND);
		
	    managementTool.addActivityEndDate(date);
	}
	
	@Then("^there are a start date (\\d+)$")
	public void thereAreAStartDate(int arg1) throws Exception {
		String dateString = Integer.toString(arg1);
		
		String day = dateString.substring(0, 1);
		String month = dateString.substring(2, 3);
		String year = "20" + dateString.substring(4, 5);
		
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date.clear(Calendar.MILLISECOND);
		
		assertEquals(date, managementTool.getActivityStartDate());
	}
	
	@Then("^there are a end date (\\d+)$")
	public void thereAreAEndDate(int arg1) throws Exception {
		String dateString = Integer.toString(arg1);
		
		String day = dateString.substring(0, 1);
		String month = dateString.substring(2, 3);
		String year = "20" + dateString.substring(4, 5);
		
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date.clear(Calendar.MILLISECOND);
		
		assertEquals(date, managementTool.getActivityEndDate());
	}
	
}
