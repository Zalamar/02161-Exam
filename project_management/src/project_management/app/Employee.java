package project_management.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import project_management.app.exceptions.startDateAfterEndDateException;

public class Employee { 

	private String username;
	private String name;
	private List<Activity> personalActivityList = new ArrayList<Activity>();
	
	public Employee(String username, String name) { //Alex
		this.username = username;
		this.name = name;
	}
	
	public String getUsername() { //Alex
		return this.username;
	}
	
	public String getName() { //Alex
		return this.name;
	}

	public void addPersonalActivity(String activityName, GregorianCalendar startDate, GregorianCalendar endDate) throws startDateAfterEndDateException { // Tobias
		if (startDate.after(endDate)) {
			Activity newActivity = new Activity(activityName);
			newActivity.setStartDate(startDate);
			newActivity.setEndDate(endDate);
			personalActivityList.add(newActivity);
		} else {
			throw new startDateAfterEndDateException();
		}
	}

	public List<Activity> getPersonalActivityList() { // Tobias
		return personalActivityList;
	}

	public void addPersonalActivity(String activityName, GregorianCalendar date) {
		Activity newActivity = new Activity(activityName);
		newActivity.setStartDate(date);
		personalActivityList.add(newActivity);
	}
}
