package project_management.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import project_management.app.exceptions.ActivityNotFoundException;
import project_management.app.exceptions.NoActivityIsSelectedException;
import project_management.app.exceptions.NoActivityWithNameException;
import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.NoProjectWithThatName;
import project_management.app.exceptions.TheProjectAlreadyHaveAManager;
import project_management.app.exceptions.UserNotLoggedIn;
import project_management.app.exceptions.startDateAfterEndDateException;
import project_management.app.exceptions.workerNotOnProjectException;

public class ManagementTool {

	private List<Project> projectList = new ArrayList<Project>();
	private List<Employee> employeeList = new ArrayList<Employee>();
	private Employee employeeLoggedIn = null;
	private Project selectedProject = null;
	private Activity selectedActivity = null;

	public void createProject(String string) throws UserNotLoggedIn { // Alex
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

	public boolean isthereAProjectWithThisName(String name) { // Alex
		for (Project p : projectList) {
			if (p.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void logout() { // Alex
		employeeLoggedIn = null;
		selectedActivity = null;
		selectedProject = null;
	}

	public void selectProject(String name) throws NoProjectWithThatName { // Alex
		if (isthereAProjectWithThisName(name)) {
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

	public void addProjectManager(String name)
			throws TheProjectAlreadyHaveAManager, UserNotLoggedIn, NoProjectIsSelected { // Alex
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

	public void deleteProject(String name) throws NoProjectWithThatName, UserNotLoggedIn { // Alex
		if (isEmployeeLoggedIn()) {
			projectList.remove(selectedProject);
			selectedProject = null;
		}
	}

	private boolean hasProjectBeenSelected() throws NoProjectIsSelected { // Tobias
		if (selectedProject != null) {
			return true;
		} else {
			throw new NoProjectIsSelected();
		}
	}

	public boolean isthereAnActivityWithThisName(String activityName)
			throws NoProjectIsSelected, NoActivityWithNameException { // Tobias
		if (hasProjectBeenSelected()) {
			for (Activity a : selectedProject.getActivityList()) {
				if (a.getName().equals(activityName)) {
					return true;
				}
			}
		}
		throw new NoActivityWithNameException();
	}

	public void selectActivity(String activityName)
			throws UserNotLoggedIn, NoProjectIsSelected, ActivityNotFoundException { // Tobias
		if (isEmployeeLoggedIn() && hasProjectBeenSelected()) {
			List<Activity> activityList = selectedProject.getActivityList();
			for (Activity a : activityList) {
				if (a.getName().equals(activityName)) {
					selectedActivity = a;
				}
			}
			if (selectedActivity == null) {
				throw new ActivityNotFoundException();
			}
		}
	}

	public void deleteActivity()
			throws UserNotLoggedIn, NoProjectIsSelected, NoActivityIsSelectedException, ActivityNotFoundException { // Tobias
		if (isEmployeeLoggedIn()) {
			if (hasProjectBeenSelected()) {
				if (hasActivityBeenSelected()) {
					if (!selectedProject.deleteActivity(selectedActivity)) {
						throw new ActivityNotFoundException();
					}
					selectedActivity = null;
				}
			}
		}
	}

	private boolean hasActivityBeenSelected() throws NoActivityIsSelectedException { // Tobias
		if (selectedActivity != null) {
			return true;
		} else {
			throw new NoActivityIsSelectedException();
		}
	}

	public Activity getSelectedActivity() { // Tobias
		return selectedActivity;
	}

	public void addTimeToActivity(int activityTime) throws UserNotLoggedIn { // Tobias
		if (isEmployeeLoggedIn()) {
			selectedActivity.addTime(activityTime);
		} else {
			throw new UserNotLoggedIn();
		}
	}

	public int getActivityTime() throws UserNotLoggedIn { // Tobias
		return selectedActivity.getEstimatedTime();
	}

	public void addActivityStartDate(GregorianCalendar date) throws UserNotLoggedIn { // Tobias
		if (isEmployeeLoggedIn()) {
			selectedActivity.setStartDate(date);
		}
	}

	public void addActivityEndDate(GregorianCalendar date) throws UserNotLoggedIn { // Tobias
		if (isEmployeeLoggedIn()) {
			selectedActivity.setEndDate(date);
		}
	}

	public GregorianCalendar getActivityStartDate() { // Tobias
		return selectedActivity.getStartDate();
	}

	public GregorianCalendar getActivityEndDate() { // Tobias
		return selectedActivity.getEndDate();
	}

	public void addWorkerToProject(String name) throws NoProjectIsSelected, UserNotLoggedIn { // Tobias
		if (isEmployeeLoggedIn()) {
			if (hasProjectBeenSelected()) {
				Employee worker = searchEmployee(name);
				selectedProject.addWorker(worker);
			}
		}
	}

	public void addWorkerToActivity(String name)
			throws NoProjectIsSelected, NoActivityIsSelectedException, UserNotLoggedIn, workerNotOnProjectException { // Tobias
		if (isEmployeeLoggedIn()) {
			if (hasProjectBeenSelected()) {
				if (hasActivityBeenSelected()) {
					if (workerIsOnProject(name)) {
						Employee worker = searchEmployee(name);
						selectedActivity.addWorker(worker);
					}
				}
			}
		}
	}

	private boolean workerIsOnProject(String name) throws workerNotOnProjectException { // Tobias
		List<Employee> workerList = selectedProject.getWorkerList();
		for (Employee e : workerList) {
			if (e.getUsername().equals(name)) {
				return true;
			}
		}
		throw new workerNotOnProjectException(); // Not part of use_case
	}

	public void addPersonalActivity(String name, GregorianCalendar startDate, GregorianCalendar endDate)
			throws UserNotLoggedIn, startDateAfterEndDateException { // Tobias
		if (isEmployeeLoggedIn()) {
			employeeLoggedIn.addPersonalActivity(name, startDate, endDate);
		}
	}

	public Employee getEmployeeLoggedIn() { // Tobias
		return employeeLoggedIn;
	}

	public void addPersonalActivity(String name, GregorianCalendar date) throws UserNotLoggedIn {
		if (isEmployeeLoggedIn()) {
			employeeLoggedIn.addPersonalActivity(name, date);
		}
	}

}