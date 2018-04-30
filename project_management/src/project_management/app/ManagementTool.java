package project_management.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import project_management.app.exceptions.ActivityNotFoundException;
import project_management.app.exceptions.NoActivityIsSelectedException;
import project_management.app.exceptions.NoActivityWithNameException;
import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.NoProjectWithThatName;
import project_management.app.exceptions.NoWorkerAvailble;
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

	public void login(String username) { // Alex
		employeeLoggedIn = searchEmployee(username);
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
		deselectProject();
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
		if (isEmployeeLoggedIn() && hasProjectBeenSelected()) {
			selectedProject.addActivity(new Activity(name));
		}
	}

	public boolean searchActivitys(String name) { // Alex
		if (selectedProject.searchActivitys(name)) {
			return true;
		} else {
			return false;
		}
	}

	public void addProjectManager(String username)
			throws TheProjectAlreadyHaveAManager, UserNotLoggedIn, NoProjectIsSelected { // Alex
		if (hasProjectBeenSelected() && !selectedProject.hasAManager() && isEmployeeLoggedIn()) {
			selectedProject.addManager(searchEmployee(username));
		} else {
			throw new TheProjectAlreadyHaveAManager();
		}
	}

	public boolean isProjectManager() {
		String username = employeeLoggedIn.getUsername();
		if (selectedProject.hasAManager() && (selectedProject.getManager()).getUsername().equals(username)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isEmployeeLoggedIn() throws UserNotLoggedIn { // Alex
		if (employeeLoggedIn != null) {
			return true;
		} else {
			throw new UserNotLoggedIn();
		}
	}

	private Employee searchEmployee(String username) { // Alex
		for (Employee e : employeeList) {
			if (e.getUsername().equals(username)) {
				return e;
			}
		}
		return null;
	}

	public Project getSelectedProject() { // Alex
		return selectedProject;
	}

	public void removeProjectManager() throws UserNotLoggedIn, NoProjectIsSelected { // Alex
		if (isEmployeeLoggedIn() && hasProjectBeenSelected()) {
			selectedProject.removeManager();
		}
	}

	public void deleteProject(String name) throws UserNotLoggedIn, NoProjectIsSelected { // Alex
		if (isEmployeeLoggedIn() && hasProjectBeenSelected()) {
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
		}
	}

	public int getActivityTime() throws UserNotLoggedIn { // Tobias
		return selectedActivity.getEstimatedTime();
	}

	public void addActivityStartDate(GregorianCalendar date) throws UserNotLoggedIn, NoActivityIsSelectedException { // Tobias
		if (isEmployeeLoggedIn() && hasActivityBeenSelected()) {
			selectedActivity.setStartDate(date);
		}
	}

	public void addActivityEndDate(GregorianCalendar date)
			throws UserNotLoggedIn, NoActivityIsSelectedException, startDateAfterEndDateException { // Tobias
		if (isEmployeeLoggedIn() && hasActivityBeenSelected()) {
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
		if (isEmployeeLoggedIn() && hasProjectBeenSelected()) {
			Employee worker = searchEmployee(name);
			selectedProject.addWorker(worker);
		}
	}

	public void addWorkerToActivity(String username)
			throws NoProjectIsSelected, NoActivityIsSelectedException, UserNotLoggedIn, workerNotOnProjectException { // Tobias
		if (isEmployeeLoggedIn() && hasProjectBeenSelected() && hasActivityBeenSelected()
				&& workerIsOnProject(username)) {
			Employee worker = searchEmployee(username);
			selectedActivity.addWorker(worker);
		}
	}

	private boolean workerIsOnProject(String username) throws workerNotOnProjectException { // Tobias
		List<Employee> workerList = selectedProject.getWorkerList();
		for (Employee e : workerList) {
			if (e.getUsername().equals(username)) {
				return true;
			}
		}
		throw new workerNotOnProjectException();
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
	
	public List<List<String>> getWhosAvailable(double procent) throws UserNotLoggedIn, NoProjectIsSelected, NoActivityIsSelectedException, NoWorkerAvailble {
		isEmployeeLoggedIn();
		hasProjectBeenSelected();
		hasActivityBeenSelected();
		procent /= 100;
		List<List<String>> availableWorkers = new ArrayList<List<String>>();
		int lenghtOffSelectedActivity = daysBetween(selectedActivity);
		for (Employee employee : employeeList) {
			int daysOff = 0;
			for (Activity activity : employee.getPersonalActivityList()) {
				daysOff += getTheNumberOfDays(lenghtOffSelectedActivity, activity);
			}
			if (daysOff <= (double) lenghtOffSelectedActivity * procent) {
				List<String> line = new ArrayList<String>();
				line.add(employee.getUsername());
				line.add("" + daysOff);
				availableWorkers.add(line);
			}
		}
		if (availableWorkers.size() > 0) {
			return availableWorkers;
		} else {
			throw new NoWorkerAvailble();
		}
	}

	public List<List<String>> getWhosAvailable() throws NoWorkerAvailble, UserNotLoggedIn, NoProjectIsSelected, NoActivityIsSelectedException { // Alex
		return getWhosAvailable(75);
	}

	private int getTheNumberOfDays(int lenghtOffSelectedActivity, Activity activity) {
		if (startsBeforeSelected(activity) && endsAfterSelected(activity)) {
			return lenghtOffSelectedActivity;
		} else if (startsAfterSelected(activity)) {
			return 0;
		} else if (endsBeforeSelected(activity)) {
			return 0;
		} else if (startsBeforeSelected(activity)) {
			return daysBetween(selectedActivity.getStartDate(), activity.getEndDate());
		} else if (endsAfterSelected(activity)) {
			return daysBetween(activity.getStartDate(), selectedActivity.getEndDate());
		} else {
			return (isThereAEndDate(activity)) ? daysBetween(activity) : 1;
		}
	}

	private boolean endsBeforeSelected(Activity activity) {
		return isThereAEndDate(activity) && activity.getEndDate().before(selectedActivity.getStartDate());
	}

	private boolean startsAfterSelected(Activity activity) {
		return activity.getStartDate().after(selectedActivity.getEndDate());
	}

	private boolean isThereAEndDate(Activity activity) { // Alex
		return activity.getEndDate() != null;
	}

	private boolean endsAfterSelected(Activity activity) {// Alex
		return isThereAEndDate(activity) && activity.getEndDate().after(selectedActivity.getEndDate());
	}

	private boolean startsBeforeSelected(Activity activity) {// Alex
		return activity.getStartDate().before(selectedActivity.getStartDate());
	}

	private int daysBetween(Activity activity) {// Alex
		return daysBetween(activity.getStartDate(), activity.getEndDate());
	}

	private int daysBetween(GregorianCalendar gregorianCalendar, GregorianCalendar gregorianCalendar2) { // Alex
		return (int) ((gregorianCalendar2.getTime().getTime() - gregorianCalendar.getTime().getTime())
				/ (1000 * 60 * 60 * 24)) + 1;
	}

	public void deselectProject() { // Alex
		deselectActivity();
		selectedProject = null;
	}

	public void deselectActivity() { // Alex
		selectedActivity = null;
	}

	public List<String> seeUnregisterActivitys() throws UserNotLoggedIn, NoProjectIsSelected { 
		isEmployeeLoggedIn();
		hasProjectBeenSelected();
		return null;
	}

	public void removeWorker(String username) { // Tobias
		selectedProject.removeWorker(username);
	}

}