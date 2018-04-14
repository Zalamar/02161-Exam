package project_management.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Activity {

	private String name;
	private int estimatedTime = 0;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private List<Employee> workerList = new ArrayList<Employee>();
	
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

	public GregorianCalendar getStartDate() { // Tobias
		return startDate;
	}

	public GregorianCalendar getEndDate() { // Tobias
		return endDate;
	}

	public void setEndDate(GregorianCalendar endDate) { // Tobias
		this.endDate = endDate;
	}
	
	public void setStartDate(GregorianCalendar startDate) { // Tobias
		this.startDate = startDate;
	}

	public void addWorker(Employee worker) { // Tobias
		workerList.add(worker);
	}

	public Employee searchWorkerList(String name) { // Tobias
		for (Employee e : workerList) {
			if (e.getUsername().equals(name)) {
				return e;
			}
		}
		return null;
	}

}
