package project_management.app.exceptions;

public class NoWorkerAvailble extends Exception {
	public String getMessage() {
		return "No worker is available";
	}
}
