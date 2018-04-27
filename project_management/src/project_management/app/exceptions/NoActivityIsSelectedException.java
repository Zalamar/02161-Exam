package project_management.app.exceptions;

public class NoActivityIsSelectedException extends Exception {
	
	public String getMessage() {
		return "No activity is selected";
	}
}
