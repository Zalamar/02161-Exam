package project_management.app.exceptions;

public class ActivityAlreadyExisting extends Exception {
	public String getMessage() {
		return "Activity already exiting";
	}
}
