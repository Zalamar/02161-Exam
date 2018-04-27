package project_management.test;

import static org.junit.Assert.assertEquals;

/**
 * @author Tobias
 *
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import project_management.app.Activity;
import project_management.app.ManagementTool;
import project_management.app.exceptions.UserNotLoggedIn;
import project_management.app.exceptions.startDateAfterEndDateException;

public class AddPersonalActivitySteps {
	
	private ManagementTool managementTool;
	private String errorMessage;
	
	public AddPersonalActivitySteps(ManagementTool managementTool) {
		this.managementTool = managementTool;
	}
	
	private GregorianCalendar makeDate(String dateString) { //Alex
		String day = dateString.substring(0, 2);
		String month = dateString.substring(2, 4);
		String year = "20" + dateString.substring(4, 6);
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);

		date.clear(Calendar.MILLISECOND);
		return date;
	}
	
	@When("^a worker adds an activity \"([^\"]*)\" with start time (\\d+) and end time (\\d+)$")
	public void aWorkerAddsAnActivityWithStartTimeAndEndTime(String arg1, String arg2, String arg3) throws Exception {
		// Start date, client side handled
		String date1String = arg2;
		GregorianCalendar date1 = makeDate(date1String);
		
		// End date, client side handled
		String date2String = arg3;
		GregorianCalendar date2 = makeDate(date2String);
		
		try {
			managementTool.addPersonalActivity(arg1, date1, date2);
		} catch (UserNotLoggedIn e) {
			errorMessage = e.getMessage();
		} catch (startDateAfterEndDateException e) {
			errorMessage = e.getMessage();
		}
	}
	
	@Then("^he has an activity called \"([^\"]*)\" and time (\\d+) till (\\d+)$")
	public void heHasAnActivityCalledAndTimeTill(String arg1, String arg2, String arg3) throws Exception {
	    List<Activity> personalActivityList = managementTool.getEmployeeLoggedIn().getPersonalActivityList();
	    String date1String = arg2;
	    GregorianCalendar date1 = makeDate(date1String);
		String date2String = arg3;
		GregorianCalendar date2 = makeDate(date2String);
	    for (Activity a : personalActivityList) {
	    	if (a.getName().equals(arg1)) {
	    		assertEquals(a.getStartDate(), date1);
	    		assertEquals(a.getEndDate(), date2);
	    	}
	    }
	}
	
	@Then("^I get the addPersonalActivity error message \"([^\"]*)\"$")
	public void iGetTheAddPersonalActivityErrorMessage(String arg1) throws Exception {
	    assertEquals(arg1, errorMessage);
	}
	
	@When("^When a worker adds an activity \"([^\"]*)\" with start time (\\d+)$")
	public void whenAWorkerAddsAnActivityWithStartTime(String arg1, String arg2) throws Exception {
		String dateString = arg2;
		
		GregorianCalendar date = makeDate(dateString);
		
		try {
			managementTool.addPersonalActivity(arg1, date);
		} catch (UserNotLoggedIn e) {
			errorMessage = e.getMessage();
		}
	}
	
	@Then("^he has an activity called \"([^\"]*)\" and time (\\d+)$")
	public void heHasAnActivityCalledAndTime(String arg1, String arg2) throws Exception {
		List<Activity> personalActivityList = managementTool.getEmployeeLoggedIn().getPersonalActivityList();
	    
	    String dateString = arg2;
		
	    GregorianCalendar date = makeDate(dateString);
	    
	    for (Activity a : personalActivityList) {
	    	if (a.getName().equals(arg1)) {
	    		assertEquals(a.getStartDate(), date);
	    	}
	    }
	}


}
