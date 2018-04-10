package project_management.app;

public class Employee {

	private String username;
	private String name;
	
	public Employee(String username, String name) {
		this.username = username;
		this.name = name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}

}
