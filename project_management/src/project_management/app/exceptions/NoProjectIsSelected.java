package project_management.app.exceptions;

public class NoProjectIsSelected extends Exception {
	public String getMessage() {
		return "No project is selected";
	}
}
