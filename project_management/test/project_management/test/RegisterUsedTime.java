package project_management.test;

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
	
	@Given("^a worker is register in the company$")
	
	@Given("^a worker is logged in$")
	
	@Given("^a project is created named \"011001\"$")
	
	@Given("^an activity \"001\" is added to the project$")

	@Given("^a project manager is added to the project$")
	
	@Given("^the worker is added to the activity$")
}
