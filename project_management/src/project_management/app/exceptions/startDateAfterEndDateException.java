package project_management.app.exceptions;

public class startDateAfterEndDateException extends Exception {
	public String getMessage() {
		return "The activity ends before it's starts";
	}
}
