package project_management.app;

import java.util.ArrayList;
import java.util.List;

public class ManagementTool {
	
	private List<Project> projectList = new ArrayList<Project>();

	public void creatProject(int name) {
		projectList.add(new Project(name));
	}

}