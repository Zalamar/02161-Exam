package project_management.app.exceptions;

public class workerNotOnProjectException extends Exception {
	public String getMessage() {
		return "Worker is not on project";
	}
}
