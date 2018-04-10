package project_management.app.exceptions;

public class TheProjectAlreadyHaveAManager extends Exception {
	public String getMessage() {
		return "The project already have a project manager";
	}
}
