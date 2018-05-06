package project_management.app.exceptions;

public class NoWorkerExitingEception extends Exception {
	public String getMessage() {
		return "No employee with this name";
	}
}
