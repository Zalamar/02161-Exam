package project_management.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import project_management.app.exceptions.ActivityNotFoundException;
import project_management.app.exceptions.NoActivityIsSelectedException;
import project_management.app.exceptions.NoProjectIsSelected;
import project_management.app.exceptions.NoProjectWithThatName;
import project_management.app.exceptions.NoWorkerAvailble;
import project_management.app.exceptions.NoWorkerExitingEception;
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
		if (isthereAProjectWithThisName(name)) { //1
			for (Project p : projectList) { //2
				if (p.getName().equals(name)) { //3
					selectedProject = p;
				}
			}
		} else { //4
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
			throws TheProjectAlreadyHaveAManager, UserNotLoggedIn, NoProjectIsSelected, NoWorkerExitingEception { // Alex
		if (hasProjectBeenSelected() && !selectedProject.hasAManager() && isEmployeeLoggedIn()) {
			Employee e = searchEmployee(username);
			if(e != null) {
				selectedProject.addManager(e);
			} else {
				throw new NoWorkerExitingEception();
			}
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

	public boolean isEmployeeLoggedIn() throws UserNotLoggedIn { // Alex
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

	public void deleteProject() throws UserNotLoggedIn, NoProjectIsSelected { // Alex
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
	
	public List<String> returnListOfProjects() { // Oliver
		List<String> ListOfProjects = new ArrayList<String>();
		for (Project p : projectList) {
			ListOfProjects.add(p.getName());
		}
		return ListOfProjects;
	}

	public void selectActivity(String activityName)
			throws UserNotLoggedIn, NoProjectIsSelected, ActivityNotFoundException { // Tobias
	    assert activityName != null;
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
		assert selectedActivity != null;
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
		//precondition
		assert(0<=procent&& procent<=100);
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
                addAvailableWorkerToList(availableWorkers, daysOff, employee);
			}
		}

		int daysOff;
		for (Project p : projectList) { // Tobias
		    for (Activity a : p.getActivityList()) {
		        daysOff = daysBetween(a);
		        for (Employee e : a.getWorkerList()) {
		            boolean workerAlreadyInList = false;
                    List<String> lineToRemove = new ArrayList<String>();
                    boolean removeLine = false;
		            for (List<String> line : availableWorkers) {
		                if (line.contains(e.getUsername())) {
		                    if (Integer.parseInt(line.get(1)) + daysOff <= (double) lenghtOffSelectedActivity * procent) {
                                line.set(1, Integer.parseInt(line.get(1)) + daysOff + "");
                            } else {
		                        lineToRemove = line;
		                        removeLine = true;
                            }
		                    workerAlreadyInList = true;
                        }
                    }
                    if (!workerAlreadyInList && daysOff <= (double) lenghtOffSelectedActivity * procent) {
                        addAvailableWorkerToList(availableWorkers, daysOff, e);
                    }
                    if (removeLine) {
                        availableWorkers.remove(lineToRemove);
                    }
                }
            }
        }

		if (availableWorkers.size() > 0) {
			return availableWorkers;
		} else {
			throw new NoWorkerAvailble();
		}
	}

    private void addAvailableWorkerToList(List<List<String>> availableWorkers, int daysOff, Employee e) {
        List<String> line = new ArrayList<String>();
        line.add(e.getUsername());
        line.add("" + daysOff);
        availableWorkers.add(line);
    }

    public List<List<String>> getWhosAvailable() throws NoWorkerAvailble, UserNotLoggedIn, NoProjectIsSelected, NoActivityIsSelectedException { // Alex
		return getWhosAvailable(75);
	}

	public int getTheNumberOfDays(int lenghtOffSelectedActivity, Activity activity) {
		//precondition
		assert(lenghtOffSelectedActivity>=0);
		if (startsBeforeSelected(activity) && endsAfterSelected(activity)) { //1
			return lenghtOffSelectedActivity;
		} else if (startsAfterSelected(activity)) { //2
			return 0;
		} else if (endsBeforeSelected(activity)) { //3
			return 0;
		} else if (startsBeforeSelected(activity)) { //4
			return isThereAEndDate(activity) ? daysBetween(selectedActivity.getStartDate(), activity.getEndDate()) : 0; //4.1
		} else if (endsAfterSelected(activity)) { //5
			return daysBetween(activity.getStartDate(), selectedActivity.getEndDate());
		} else { //6
			return isThereAEndDate(activity) ? daysBetween(activity) : 1; //6.1
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

	public void addUsedTime(double usedTime) throws UserNotLoggedIn, NoProjectIsSelected, NoActivityIsSelectedException { //Oliver
		isEmployeeLoggedIn();
		hasProjectBeenSelected();
		hasActivityBeenSelected();
		selectedActivity.addUsedTime(usedTime, employeeLoggedIn.getUsername());
		
	}

	public List<List<String>> getUsedTime() { //Oliver
		return selectedActivity.getUsedTime();
	}
	
	public double getUsedTime(String username) { //Oliver
		return selectedActivity.getUsedTime(username);
	}
	
	public void removeWorker(String username) { // Tobias
		selectedProject.removeWorker(username);
	}

	

}