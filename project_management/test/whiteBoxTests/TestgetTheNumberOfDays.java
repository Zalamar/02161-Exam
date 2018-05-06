package whiteBoxTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import project_management.app.Activity;
import project_management.app.Employee;
import project_management.app.ManagementTool;
import project_management.app.exceptions.ActivityNotFoundException;
import project_management.app.exceptions.NoActivityIsSelectedException;
import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.NoProjectWithThatName;
import project_management.app.exceptions.UserNotLoggedIn;
import project_management.app.exceptions.startDateAfterEndDateException;

public class TestgetTheNumberOfDays {

	Activity activity = new Activity("TestA2");
	ManagementTool managementTool = new ManagementTool();

	private void start() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected, ActivityNotFoundException,
			NoActivityIsSelectedException, startDateAfterEndDateException {
		managementTool.addWorker("AAAA", "Alex");
		managementTool.login("AAAA");
		managementTool.createProject("test");
		managementTool.selectProject("test");
		managementTool.addAnActivity("testA");
		managementTool.selectActivity("testA");

		GregorianCalendar date = new GregorianCalendar();
		date.set(2018, 6, 10, 0, 0, 0);
		date.clear(Calendar.MILLISECOND);
		managementTool.addActivityStartDate(date);

		GregorianCalendar date2 = new GregorianCalendar();
		date2.set(2018, 6, 20, 0, 0, 0);
		date2.clear(Calendar.MILLISECOND);
		managementTool.addActivityEndDate(date2);
	}

	private void testCases(int days, int startDate, int endDate) throws startDateAfterEndDateException {
		GregorianCalendar date = new GregorianCalendar();
		date.set(2018, 6, startDate, 0, 0, 0);
		date.clear(Calendar.MILLISECOND);
		activity.setStartDate(date);
		GregorianCalendar date2 = new GregorianCalendar();
		date2.set(2018, 6, endDate, 0, 0, 0);
		date2.clear(Calendar.MILLISECOND);
		activity.setEndDate(date2);
		assertEquals(days, managementTool.getTheNumberOfDays(10, activity));
	}

	private void testCase2(int days, int startDate) {
		Activity activity2 = new Activity("Test3");
		GregorianCalendar date = new GregorianCalendar();
		date.set(2018, 6, startDate, 0, 0, 0);
		date.clear(Calendar.MILLISECOND);
		activity2.setStartDate(date);
		assertEquals(days, managementTool.getTheNumberOfDays(10, activity2));
	}

	@Test
	public void testInput1() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			ActivityNotFoundException, NoActivityIsSelectedException, startDateAfterEndDateException {
		start();
		int days = 4;
		int startDate = 12;
		int endDate = 15;
		testCases(days, startDate, endDate);
	}

	@Test
	public void testInput2() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			ActivityNotFoundException, NoActivityIsSelectedException, startDateAfterEndDateException {
		start();
		int days = 9;
		int startDate = 12;
		int endDate = 21;
		testCases(days, startDate, endDate);
	}

	@Test
	public void testInpu3() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			ActivityNotFoundException, NoActivityIsSelectedException, startDateAfterEndDateException {
		start();
		int days = 5;
		int startDate = 9;
		int endDate = 14;
		testCases(days, startDate, endDate);
	}

	@Test
	public void testInput4() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			ActivityNotFoundException, NoActivityIsSelectedException, startDateAfterEndDateException {
		start();
		int days = 0;
		int startDate = 6;
		int endDate = 9;
		testCases(days, startDate, endDate);
	}

	@Test
	public void testInput5() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			ActivityNotFoundException, NoActivityIsSelectedException, startDateAfterEndDateException {
		start();
		int days = 0;
		int startDate = 21;
		int endDate = 28;
		testCases(days, startDate, endDate);
	}

	@Test
	public void testInput6() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			ActivityNotFoundException, NoActivityIsSelectedException, startDateAfterEndDateException {
		start();
		int days = 10;
		int startDate = 9;
		int endDate = 30;
		testCases(days, startDate, endDate);
	}

	@Test
	public void testInput7() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			ActivityNotFoundException, NoActivityIsSelectedException, startDateAfterEndDateException {
		start();
		int days = 1;
		int startDate = 12;
		testCase2(days, startDate);
	}

	@Test
	public void testInput8() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			ActivityNotFoundException, NoActivityIsSelectedException, startDateAfterEndDateException {
		start();
		int days = 0;
		int startDate = 9;
		testCase2(days, startDate);
	}

}
