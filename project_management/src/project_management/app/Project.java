package project_management.app;

import java.util.ArrayList;
import java.util.List;

public class Project {

	private String name;
	private List<Activity> activityList = new ArrayList<Activity>();
	private Employee manager;
	private List<Employee> workerList = new ArrayList<Employee>();

	public Project(String Name) { // Alex
		this.name = Name;
	}

	public String getName() { // Alex
		return this.name;
	}

	public void addActivity(Activity activity) { // Alex
		activityList.add(activity);
	}

	public boolean searchActivitys(String name) { // Alex
		for (Activity a : activityList) {
			if (a.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void addManager(Employee e) {
		manager = e;
		if (!isEmployeeOnList(e.getUsername())) {
			workerList.add(e);
		}
	}

	public boolean isEmployeeOnList(String username) {
		for (Employee employee : workerList) {
			if (employee.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public Employee getManager() {
		return manager;
	}

	public boolean hasAManager() {
		if (manager != null) {
			return true;
		} else {
			return false;
		}
	}

	public void removeManager() {
		manager = null;
	}

	public List<Activity> getActivityList() { // Tobias
		return activityList;
	}

	public boolean deleteActivity(Activity selectedActivity) { // Tobias
		return activityList.remove(selectedActivity);
	}

	public void addWorker(Employee worker) { // Tobias
		if (!isEmployeeOnList(worker.getUsername())) {
			workerList.add(worker);
		} else {

		}
	}

	public List<Employee> getWorkerList() { // Tobias
		return workerList;
	}

	public void removeWorker(String username) { // Tobias
		Employee removedWorker = null;
		for (Employee e : workerList) {
			if (e.getUsername().equals(username)) {
				removedWorker = e;
			}
		}
		workerList.remove(removedWorker);
	}

}
