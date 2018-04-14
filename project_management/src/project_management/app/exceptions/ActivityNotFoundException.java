package project_management.app.exceptions;

public class ActivityNotFoundException extends Exception {
	public String getMessage() {
		return "The activity dosnt exits";
	}
}
