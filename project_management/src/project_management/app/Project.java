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
		workerList.add(worker);
	}

}
