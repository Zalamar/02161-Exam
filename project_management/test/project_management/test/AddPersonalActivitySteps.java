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
	
	@When("^a worker adds an activity \"([^\"]*)\" with start time (\\d+) and end time (\\d+)$")
	public void aWorkerAddsAnActivityWithStartTimeAndEndTime(String arg1, int arg2, int arg3) throws Exception {
		// Start date, client side handled
		String date1String = Integer.toString(arg2);
		
		String day = date1String.substring(0, 1);
		String month = date1String.substring(2, 3);
		String year = "20" + date1String.substring(4, 5);
		
		GregorianCalendar date1 = new GregorianCalendar();
		date1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date1.clear(Calendar.MILLISECOND);
		
		// End date, client side handled
		String date2String = Integer.toString(arg3);
		
		day = date2String.substring(0, 1);
		month = date2String.substring(2, 3);
		year = "20" + date2String.substring(4, 5);
		
		GregorianCalendar date2 = new GregorianCalendar();
		date2.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date2.clear(Calendar.MILLISECOND);
		
		try {
			managementTool.addPersonalActivity(arg1, date1, date2);
		} catch (UserNotLoggedIn e) {
			errorMessage = e.getMessage();
		} catch (startDateAfterEndDateException e) {
			errorMessage = e.getMessage();
		}
	}
	
	@Then("^he has an activity called \"([^\"]*)\" and time (\\d+) till (\\d+)$")
	public void heHasAnActivityCalledAndTimeTill(String arg1, int arg2, int arg3) throws Exception {
	    List<Activity> personalActivityList = managementTool.getEmployeeLoggedIn().getPersonalActivityList();
	    
	    String date1String = Integer.toString(arg2);
		
		String day = date1String.substring(0, 1);
		String month = date1String.substring(2, 3);
		String year = "20" + date1String.substring(4, 5);
		
		GregorianCalendar date1 = new GregorianCalendar();
		date1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date1.clear(Calendar.MILLISECOND);
		
		String date2String = Integer.toString(arg3);
		
		day = date2String.substring(0, 1);
		month = date2String.substring(2, 3);
		year = "20" + date2String.substring(4, 5);
		
		GregorianCalendar date2 = new GregorianCalendar();
		date2.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date2.clear(Calendar.MILLISECOND);
	    
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
	public void whenAWorkerAddsAnActivityWithStartTime(String arg1, int arg2) throws Exception {
		String dateString = Integer.toString(arg2);
		
		String day = dateString.substring(0, 1);
		String month = dateString.substring(2, 3);
		String year = "20" + dateString.substring(4, 5);
		
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date.clear(Calendar.MILLISECOND);
		
		try {
			managementTool.addPersonalActivity(arg1, date);
		} catch (UserNotLoggedIn e) {
			errorMessage = e.getMessage();
		}
	}
	
	@Then("^he has an activity called \"([^\"]*)\" and time (\\d+)$")
	public void heHasAnActivityCalledAndTime(String arg1, int arg2) throws Exception {
		List<Activity> personalActivityList = managementTool.getEmployeeLoggedIn().getPersonalActivityList();
	    
	    String dateString = Integer.toString(arg2);
		
		String day = dateString.substring(0, 1);
		String month = dateString.substring(2, 3);
		String year = "20" + dateString.substring(4, 5);
		
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
		
		date.clear(Calendar.MILLISECOND);
	    
	    for (Activity a : personalActivityList) {
	    	if (a.getName().equals(arg1)) {
	    		assertEquals(a.getStartDate(), date);
	    	}
	    }
	}


}
