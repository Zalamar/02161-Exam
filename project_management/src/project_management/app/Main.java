package project_management.app;

import java.util.Scanner;

// import project_management.app.Activity;
// import project_management.app.Employee;
// import project_management.app.ManagementTool;
// import project_management.app.Project;

public class Main {

	public static void main(String []args){
		
		Scanner reader = new Scanner(System.in);
		
		int selector = 0;
		String inputString = "";
		
		boolean quit = false;
		boolean back = false;
		
		System.out.println("Enter username");
		inputString = reader.next();
		
		while (quit == false) {
			System.out.printf("1. Create project\n2. Select project\n3. Quit\n");
			
			
			selector = reader.nextInt();
			
			switch(selector) {
				case 1: // Create project
					System.out.printf("Enter project name\n");
					inputString = reader.next();
					//Project.createProject(inputString);
					break;
				case 2: // Select project
					System.out.printf("Enter project ID\n");
					inputString = reader.next();
					
					//Project.selectProject(inputString);
					
					while (back == false) {
						System.out.printf("1. Add project manager\n2. Add an activity\n3. Remove an activity\n4. Add time to an activity\n");
						System.out.printf("5. Assign a worker to an activity\n6. Register used time\n");
						System.out.printf("7. See report\n8. Back");
						
						selector = reader.nextInt();
						
						switch(selector) {
							case 1:
								break;
							case 2:
								break;
							case 3:
								break;
							case 4:
								break;
							case 5:
								break;
							case 6:
								break;
							case 7:
								break;
							case 8: // Back
								back = true;
								break;
						}
						
					}
					quit = false;
					break;
				case 3: // Quit
					quit = true;
					break;
			}
				
			
		}
	}
}
