package project_management.app;

import java.util.ArrayList;
import java.util.List;

import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.NoProjectWithThatName;
import project_management.app.exceptions.TheProjectAlreadyHaveAManager;
import project_management.app.exceptions.UserNotLoggedIn;

public class ManagementTool {

	private List<Project> projectList = new ArrayList<Project>();
	private List<Employee> employeeList = new ArrayList<Employee>();
	private Employee employeeLoggedIn = null;
	private Project selectedProject = null;

	public void creatProject(String string) throws UserNotLoggedIn { // Alex
		if (isEmployeeLoggedIn()) {
			projectList.add(new Project(string));
		}
	}

	public void login(String name) { // Alex
		employeeLoggedIn = searchEmployee(name);
	}

	public void addWorker(String username, String name) { // Alex
		employeeList.add(new Employee(username, name));
	}

	public boolean isthereAProjectWithIsName(String name) { // Alex
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

	public void selectProject(String name) throws NoProjectWithThatName { // Alex
		if (isthereAProjectWithIsName(name)) {
			for (Project p : projectList) {
				if (p.getName().equals(name)) {
					selectedProject = p;
				}
			}
		} else {
			throw new NoProjectWithThatName();
		}
	}

	public void addAnActivity(String name) throws UserNotLoggedIn, NoProjectIsSelected { // Alex
		if (isEmployeeLoggedIn() && selectedProject != null) {
			selectedProject.addActivity(new Activity(name));
		} else {
			throw new NoProjectIsSelected();
		}
	}

	public boolean searchActivitys(String name) { // Alex
		if (selectedProject.searchActivitys(name)) {
			return true;
		} else {
			return false;
		}
	}

	public void addProjectManager(String name) throws TheProjectAlreadyHaveAManager, UserNotLoggedIn, NoProjectIsSelected { // Alex
		if (selectedProject != null) {
			if (!selectedProject.hasAManager() && isEmployeeLoggedIn()) {
				selectedProject.addManager(searchEmployee(name));
			} else {
				throw new TheProjectAlreadyHaveAManager();
			}
		} else {
			throw new NoProjectIsSelected();
		}
	}

	private boolean isEmployeeLoggedIn() throws UserNotLoggedIn { // Alex
		if (employeeLoggedIn != null) {
			return true;
		} else {
			throw new UserNotLoggedIn();
		}
	}

	private Employee searchEmployee(String name) { // Alex
		for (Employee e : employeeList) {
			if (e.getUsername().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public Project getSelectedProject() { // Alex
		return selectedProject;
	}

	public void removeProjectManager() throws UserNotLoggedIn, NoProjectIsSelected { // Alex
		if (isEmployeeLoggedIn() && selectedProject != null) {
			selectedProject.removeManager();
		} else {
			throw new NoProjectIsSelected();
		}
	}

	public void deleteProject(String name) throws NoProjectWithThatName { // Alex
		projectList.remove(selectedProject);
		selectedProject = null;
	}



}