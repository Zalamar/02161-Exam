package project_management.app;

import java.util.ArrayList;
import java.util.List;

import project_management.app.exceptions.UserNotLoggedIn;

public class ManagementTool {

	private List<Project> projectList = new ArrayList<Project>();
	private List<Employee> employeeList = new ArrayList<Employee>();
	private Employee employeeLoggedIn = null;

	public void creatProject(int name) throws UserNotLoggedIn {
		if (employeeLoggedIn != null) {
			projectList.add(new Project(name));
		} else {
			throw new UserNotLoggedIn();
		}
	}

	public void login(String string) {
		for (Employee e : employeeList) {
			if (e.getUsername().equals(string)) {
				employeeLoggedIn = e;
			}
		}
	}

	public void addWorker(String username, String name) {
		employeeList.add(new Employee(username, name));
	}

	public boolean searchProjects(int name) {
		for (Project p : projectList) {
			if (p.getName() == name) {
				return true;
			}
		}
		return false;
	}

	public void logout() {
		employeeLoggedIn = null;
	}

}