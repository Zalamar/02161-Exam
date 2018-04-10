package project_management.app.exceptions;

public class UserNotLoggedIn extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "No user is logged in";
	}
}
