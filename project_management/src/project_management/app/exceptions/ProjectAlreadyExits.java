package project_management.app.exceptions;

public class ProjectAlreadyExits extends Exception {
	public String getMessage() {
		return "Project already exiting";
	}
}
