package whiteBoxTests;

import org.junit.Test;
import project_management.app.Activity;
import project_management.app.Employee;
import project_management.app.ManagementTool;
import project_management.app.exceptions.ActivityAlreadyExisting;
import project_management.app.exceptions.ActivityNotFoundException;
import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.NoProjectWithThatName;
import project_management.app.exceptions.ProjectAlreadyExits;
import project_management.app.exceptions.UserNotLoggedIn;

import static org.junit.Assert.assertEquals;

public class TestselectActivity {

	ManagementTool managementTool = new ManagementTool();
	private String errorMessage;

	@Test
	public void testInput1() throws NoProjectWithThatName {
		try {
			managementTool.selectActivity("001");
		} catch (UserNotLoggedIn e) {
			errorMessage = e.getMessage();
		} catch (NoProjectIsSelected e) {
			errorMessage = e.getMessage();
		} catch (ActivityNotFoundException e) {
			errorMessage = e.getMessage();
		}
		assertEquals("No user is logged in", errorMessage);
	}

	@Test
	public void testInput2() throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected, ProjectAlreadyExits {
		managementTool.addWorker("AAAA", "Tobias");
		managementTool.login("AAAA");
		managementTool.createProject("011001");
		managementTool.selectProject("011001");
		try {
			managementTool.selectActivity("001");
		} catch (ActivityNotFoundException e) {
			errorMessage = e.getMessage();
		}
		assertEquals("The activity dosnt exits", errorMessage);
	}

	@Test
	public void testInput3() throws NoProjectWithThatName, UserNotLoggedIn, NoProjectIsSelected, ProjectAlreadyExits,
			ActivityAlreadyExisting {
		managementTool.addWorker("AAAA", "Tobias");
		managementTool.login("AAAA");
		managementTool.createProject("011001");
		managementTool.selectProject("011001");
		managementTool.addAnActivity("111");
		try {
			managementTool.selectActivity("001");
		} catch (ActivityNotFoundException e) {
			errorMessage = e.getMessage();
		}
		assertEquals("The activity dosnt exits", errorMessage);
	}

	@Test
	public void testInput4() throws NoProjectWithThatName, UserNotLoggedIn, NoProjectIsSelected,
			ActivityNotFoundException, ProjectAlreadyExits, ActivityAlreadyExisting {
		managementTool.addWorker("AAAA", "Tobias");
		managementTool.login("AAAA");
		managementTool.createProject("011001");
		managementTool.selectProject("011001");
		managementTool.addAnActivity("001");
		managementTool.selectActivity("001");

		Activity desiredActivity = new Activity("001");
		assertEquals(desiredActivity.getName(), managementTool.getSelectedActivity().getName());
	}
}
