package project_management.app;

import project_management.app.exceptions.*;

import java.util.Scanner;

// import project_management.app.Activity;
// import project_management.app.Employee;
// import project_management.app.ManagementTool;
// import project_management.app.Project;

public class Main {

	public static void main(String []args) throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected, TheProjectAlreadyHaveAManager, ActivityNotFoundException {

		ManagementTool managementTool = new ManagementTool();

		managementTool.addWorker("haha", "Hans Hansen");

		Scanner reader = new Scanner(System.in);

		int selector = 0;
		String inputString = "";

		boolean quit = false;
		boolean back = false;

		System.out.println("Enter username");
		inputString = reader.next();

		managementTool.login(inputString);
		
		while (quit == false) {
			System.out.printf("1. Create project\n2. Select project\n3. Quit\n");


			selector = reader.nextInt();

			switch(selector) {
				case 1: // Create project
					System.out.printf("Enter project name\n");
					inputString = reader.next();
					managementTool.createProject(inputString);
					break;
				case 2: // Select project
					System.out.printf("Enter project ID\n");
					inputString = reader.next();

					managementTool.selectProject(inputString);

					if (managementTool.isProjectManager()) {
						while (back == false) {
							System.out.printf("1. Add an activity\n2. Remove an activity\n3. Add time to an activity\n");
							System.out.printf("4. Assign a worker to an activity\n5. Register used time\n6. See activities missing time registration\n7. See remaining time\n");
							System.out.printf("8. Delete project\n9. See report\n10. Back");

							selector = reader.nextInt();

							switch (selector) {
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
								case 8:
									break;
								case 9:
									break;
								case 10: // Back
									back = true;
									break;
							}
						}
					}
					else {
						while (back == false) {
							System.out.printf("1. Add project manager\n2. Add an activity\n3. Select activity\n");
							System.out.printf("4. Back");

							selector = reader.nextInt();

							switch (selector) {
								case 1: // Add project manager
									System.out.printf("Enter username for project manager\n");
									inputString = reader.next();
									managementTool.addProjectManager(inputString);
									break;
								case 2: // Add activity
									System.out.printf("Enter activity ID\n");
									inputString = reader.next();
									managementTool.addAnActivity(inputString);
									break;
								case 3: // Remove activity
									System.out.printf("Enter activity ID\n");
									inputString = reader.next();

									managementTool.selectActivity(inputString);

									while (back == false) {
										System.out.printf("1. Register used time\n2. See remaining time\n");
										System.out.printf("3. Back");

										selector = reader.nextInt();

										switch (selector) {
											case 1:
												break;
											case 2:
												break;
											case 3:
												back = true;
												break;
										}

									}
									back = false;
									break;
								case 4: // Back
									back = true;
									break;
							}
						}
					}
					back = false;
					break;
				case 3: // Quit
					quit = true;
					break;
			}


		}
	}
}
