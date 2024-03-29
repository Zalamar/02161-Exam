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

	public String getName() {
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

	public void addUsedTime(double usedTime, String username) { // Oliver
		// precondition
		assert true;
		List<String> line = getLine(username);	//1
		if (line != null) {	//2							
			double newUsedTime = usedTime + Double.parseDouble(line.get(1));
			String newUsedTimeString1 = Double.toString(newUsedTime);
			line.set(1, newUsedTimeString1);
		} else { //3
			List<String> tempLine = new ArrayList<String>();
			tempLine.add(0, username);
			tempLine.add(1, Double.toString(usedTime));
			listOfUsedTime.add(tempLine);
		}
		//postcondition
		assert true;
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

	public double getUsedTime(String username) {
		try {
			return Double.parseDouble(getLine(username).get(1));
		} catch (NullPointerException e) {
			return 0;
		}
	}

	public List<Employee> getWorkerList() {
		return workerList;
	}
}
