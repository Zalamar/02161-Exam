package project_management.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import project_management.app.exceptions.startDateAfterEndDateException;

public class Activity {

	private String name;
	private int estimatedTime = 0;
	private GregorianCalendar startDate = null;
	private GregorianCalendar endDate = null;
	private List<Employee> workerList = new ArrayList<Employee>();
	private List<List<String>> listOfUsedTime = new ArrayList<List<String>>();

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

	public void setEndDate(GregorianCalendar endDate) throws startDateAfterEndDateException { // Tobias
		if (startDate.before(endDate)) {
			this.endDate = endDate;
		} else {
			throw new startDateAfterEndDateException();
		}
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

	public void addUsedTime(int usedTime, String username) { // Oliver
		List<String> line = getLine(username);
		if (line != null) {
			int newUsedTime = usedTime + Integer.parseInt(line.get(1));
			String newUsedTimeString1 = Integer.toString(newUsedTime);
			line.set(1, newUsedTimeString1);
		} else {
			List<String> tempLine = new ArrayList<String>();
			tempLine.add(0, username);
			tempLine.add(1, "" + usedTime);
			listOfUsedTime.add(tempLine);
		}
	}

	private List<String> getLine(String username) { //Oliver
		for (List<String> line : listOfUsedTime) {
			if (line.get(0).equals(username)) {
				return line;
			}
		}
		return null;
	}

	public List<List<String>> getUsedTime() { //Oliver
		return this.listOfUsedTime;
	}

	public int getUsedTime(String username) {
		return Integer.parseInt(getLine(username).get(1));
	}

}
