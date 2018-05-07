package whiteBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;

import project_management.app.ManagementTool;
import project_management.app.Project;
import project_management.app.exceptions.NoProjectWithThatName;

public class TestSelectedProject {

	ManagementTool managementTool = new ManagementTool();
	Project project = new Project("011001");
	private String errorMessage;
	
	@Test
	public void testInput1() throws NoProjectWithThatName {
		try {
			managementTool.selectProject("011002");
		} catch (NoProjectWithThatName e) {
			errorMessage = e.getMessage();
		}
	}
	
	@Test
	public void testInput2() throws NoProjectWithThatName {
		try {
			managementTool.selectProject("011001");
		} catch (NoProjectWithThatName e) {
			errorMessage = e.getMessage();
		}
	}
}
