package project_management.app.exceptions;

public class UserNotLoggedIn extends Exception {
	

	public String getMessage() {
		return "No user is logged in";
	}
}
