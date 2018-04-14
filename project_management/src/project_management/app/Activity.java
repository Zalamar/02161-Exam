package project_management.app;

import java.util.GregorianCalendar;

public class Activity {

	private String name;
	private int estimatedTime = 0;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	
	public Activity(String name) {
		this.name = name;
	}

	public Object getName() {
		return this.name;
	}

	public void addTime(int activityTime) { // Tobias
		setEstimatedTime(getEstimatedTime() + activityTime);
	}

	public int getEstimatedTime() { // Tobias
		return estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) { // Tobias
		this.estimatedTime = estimatedTime;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}
	
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

}
