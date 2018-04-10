package project_management.app;

import java.util.ArrayList;
import java.util.List;

public class Project {

	private String name;
	private List<Activity> activityList = new ArrayList<Activity>();
	
	public Project(String Name) { //Alex
		this.name = Name;
	}

	public String getName() { //Alex
		return this.name;
	}

	public void addActivity(Activity activity) { //Alex
		activityList.add(activity);
	}

	public boolean searchActivitys(String name) { //Alex
		for (Activity a : activityList) {
			if (a.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	
}
