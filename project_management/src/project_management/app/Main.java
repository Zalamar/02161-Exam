package project_management.app;

import project_management.app.exceptions.*;
import project_management.test.ErrorMessage;

import java.util.Scanner;

// import project_management.app.Activity;
// import project_management.app.Employee;
// import project_management.app.ManagementTool;
// import project_management.app.Project;

public class Main {

	public static void main(String []args) throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected, TheProjectAlreadyHaveAManager, ActivityNotFoundException, NoActivityIsSelectedException, workerNotOnProjectException {

		ManagementTool managementTool = new ManagementTool();

		managementTool.addWorker("haha", "Hans Hansen");

		Scanner reader = new Scanner(System.in);

		int selector = 0;
		String inputString = "";
		int inputInt = 0;

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
					try {
						managementTool.createProject(inputString);
				    } catch (UserNotLoggedIn e) {
				    	System.out.println(e.getMessage());
				    }
					break;
				case 2: // Select project
					System.out.printf("Enter project ID\n");
					inputString = reader.next();
						
					try {
					managementTool.selectProject(inputString);


					// Project manager
					if (managementTool.isProjectManager()) {
						while (back == false) {

							System.out.printf("1. Add an activity\n2. Select an activity\n");
							System.out.printf("3. See activities missing time registration\n");
							System.out.printf("4. See report\n5. Remove project\n6. Back\n");

							selector = reader.nextInt();

							switch (selector) {
								case 1: // Add activity
									System.out.printf("Enter activity ID\n");
									inputString = reader.next();
									managementTool.addAnActivity(inputString);
									break;
								case 2: // Select activity
									System.out.printf("Enter activity ID\n");
									inputString = reader.next();
									
									try {
									managementTool.selectActivity(inputString);

									while (back == false) {
										System.out.printf("1. Add used time\n2. Edit used time\n3. See remaining time\n");
										System.out.printf("4. Assign a worker to this activity\n");
										System.out.printf("5. Remove activity\n6. Back\n");

										selector = reader.nextInt();

										switch (selector) {
											case 1: // Add used time
												System.out.printf("Enter used time\n");
												inputInt = reader.nextInt();
												try {
												managementTool.addUsedTime(inputInt);
												} catch (workerNotOnProjectException e) {
													System.out.println(e.getMessage());
												}
												break;
											case 2: // mangler funktion
												System.out.printf("\nmangler\n");//"Currently you have %d hours registered for this activity\n", managementTool.getUsedTime());
												break;
											case 3: // mangler funktion
												System.out.printf("\nmangler\n");
												break;
											case 4: // Add worker to activity
												System.out.printf("Enter the worker's username\n");
												inputString = reader.next();
												managementTool.addWorkerToActivity(inputString);
												break;
											case 5: // Remove activity
												managementTool.deleteActivity();
												back = true;
												break;
											case 6: // back
												back = true;
												break;
										}

									}
									} catch (ActivityNotFoundException e) {
										System.out.println(e.getMessage());
									}
									back = false;
									break;
								case 4: // See report
									System.out.printf("Everything's on fire\n");
									break;
								case 5: // Remove project
									managementTool.deleteProject();
									back = true;
									break;
								case 6: // Back
									back = true;
									break;
							}
						}
					}
					// Not a project manager
					else {
						while (back == false) {
							System.out.printf("1. Add project manager\n2. Add an activity\n3. Select an activity\n");
							System.out.printf("4. Back\n");

							selector = reader.nextInt();

							switch (selector) {
								case 1: // Add project manager
									System.out.printf("Enter username for project manager\n");
									inputString = reader.next();
									try {
									managementTool.addProjectManager(inputString);
									} catch (TheProjectAlreadyHaveAManager e) {
										System.out.println(e.getMessage());
									}
									break;
								case 2: // Add activity
									System.out.printf("Enter activity ID\n");
									inputString = reader.next();
									managementTool.addAnActivity(inputString);
									break;
								case 3: // Select activity
									System.out.printf("Enter activity ID\n");
									
									inputString = reader.next();

									try {
										managementTool.selectActivity(inputString);
									

									while (back == false) {
										System.out.printf("1. Add used time\n2. Edit used time\n3. See remaining time\n");
										System.out.printf("4. Back\n");

										selector = reader.nextInt();

										switch (selector) {
											case 1: // Add used time
												System.out.printf("Enter used time\n");
												inputInt = reader.nextInt();
												managementTool.addUsedTime(inputInt);
												break;
											case 2: // mangler funktion
												System.out.printf("\nmangler\n");//"Currently you have %d hours registered for this activity\n", managementTool.getUsedTime());
												break;
											case 3: // mangler funktion
												System.out.printf("\nmangler\n");
												break;
											case 4: // Back
												back = true;
												break;
										}

									}
									
									} catch (ActivityNotFoundException e) {
										System.out.println(e.getMessage());
									}
									back = false;
									break;
								case 4: // Back
									back = true;
									break;
							}
						}
					}
					} catch (NoProjectWithThatName e) {
						System.out.println(e.getMessage());
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
