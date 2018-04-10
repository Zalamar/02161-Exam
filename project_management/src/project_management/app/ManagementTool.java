package project_management.app;

import java.util.ArrayList;
import java.util.List;

import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.UserNotLoggedIn;

public class ManagementTool {

	private List<Project> projectList = new ArrayList<Project>();
	private List<Employee> employeeList = new ArrayList<Employee>();
	private Employee employeeLoggedIn = null;
	private Project selectedProject = null;

	public void creatProject(String string) throws UserNotLoggedIn { // Alex
		if (employeeLoggedIn != null) {
			projectList.add(new Project(string));
		} else {
			throw new UserNotLoggedIn();
		}
	}

	public void login(String string) { // Alex
		for (Employee e : employeeList) {
			if (e.getUsername().equals(string)) {
				employeeLoggedIn = e;
			}
		}
	}

	public void addWorker(String username, String name) { // Alex
		employeeList.add(new Employee(username, name));
	}

	public boolean searchProjects(String name) { // Alex
		for (Project p : projectList) {
			if (p.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void logout() { // Alex
		employeeLoggedIn = null;
	}

	public void selectProject(String name) { // Alex
		if (searchProjects(name)) {
			for (Project p : projectList) {
				if (p.getName() == name) {
					selectedProject = p;
				}
			}
		}
	}

	public void addAnActivity(String name) throws UserNotLoggedIn, NoProjectIsSelected { // Alex
		if (employeeLoggedIn != null && selectedProject != null) {
			selectedProject.addActivity(new Activity(name));
		} else if (employeeLoggedIn == null) {
			throw new UserNotLoggedIn();
		} else {
			throw new NoProjectIsSelected();
		}
	}

	public boolean searchActivitys(String name) {
		if (selectedProject.searchActivitys(name)) {
			return true;
		} else {
			return false;
		}
	}

}