package project_management.app.exceptions;

public class NoProjectWithThatName extends Exception {
	public String getMessage() {
		return "No project with that name";
	}
}
