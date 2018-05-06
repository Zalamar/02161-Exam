package project_management.app;

import project_management.app.exceptions.*;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

// import project_management.app.Activity;
// import project_management.app.Employee;
// import project_management.app.ManagementTool;
// import project_management.app.Project;

public class Main {

	public static void main(String []args) throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected, TheProjectAlreadyHaveAManager, ActivityNotFoundException, NoActivityIsSelectedException, workerNotOnProjectException {

		ManagementTool managementTool = new ManagementTool();

		managementTool.addWorker("haha", "Hans Hansen");
		managementTool.addWorker("lala", "Lars Larsen");

		Scanner reader = new Scanner(System.in);

		int selector = 0;
		String inputString = "";
		double inputDouble = 0;
		int temp1 = 0;
		int temp2 = 0;
		int temp3 = 0;
		
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
							System.out.printf("3. Add worker to this project\n");
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
										System.out.printf("1. Add used time\n2. See own used time\n3. See all used time\n");
										System.out.printf("4. Assign a worker to this activity\n");
										System.out.printf("5. Set start date\n6. Set end date\n");
										System.out.printf("7. See start and end date\n");
										System.out.printf("8. See who's available\n");
										System.out.printf("9. Remove activity\n10. Back\n");

										selector = reader.nextInt();

										switch (selector) {
											case 1: // Add used time
												System.out.printf("Enter used time\n");
												inputDouble = reader.nextDouble();
												managementTool.addUsedTime(inputDouble);
												break;
											case 2: // See used time
												System.out.printf("%f\n", managementTool.getUsedTime(managementTool.getEmployeeLoggedIn().getUsername()));
												break;
											case 3: // See all used time
												System.out.println(managementTool.getUsedTime());
												break;
											case 4: // Add worker to activity
												System.out.printf("Enter the worker's username\n");
												inputString = reader.next();
												try {
												managementTool.addWorkerToActivity(inputString);
												} catch (workerNotOnProjectException e) {
													System.out.println(e.getMessage());
												}
												break;
											case 5: // set start date
												System.out.printf("Enter year\n");
												temp1 = reader.nextInt();
												System.out.printf("Enter month number\n");
												temp2 = reader.nextInt();
												System.out.printf("Enter day of month\n");
												temp3 = reader.nextInt();
												GregorianCalendar startDate = new GregorianCalendar(temp1, temp2, temp3);
												managementTool.addActivityStartDate(startDate);
												break;
											case 6: // set end date
												System.out.printf("Enter year\n");
												temp1 = reader.nextInt();
												System.out.printf("Enter month number\n");
												temp2 = reader.nextInt();
												System.out.printf("Enter day of month\n");
												temp3 = reader.nextInt();
												GregorianCalendar endDate = new GregorianCalendar(temp1, temp2, temp3);
												try {
													managementTool.addActivityEndDate(endDate);
												} catch (startDateAfterEndDateException e) {
													System.out.println(e.getMessage());
												}
												break;
											case 7: // see start and end date
												System.out.printf("\nStart date:\n");
												System.out.println(managementTool.getActivityStartDate().getTime());
												System.out.printf("End date:\n");
												System.out.println(managementTool.getActivityEndDate().getTime());
												System.out.printf("\n");
												break;
											case 8: // See who's available
											try {
												System.out.println(managementTool.getWhosAvailable());
											} catch (NoWorkerAvailble e) {
												System.out.println(e.getMessage());
											}
												break;
											case 9: // Remove activity
												managementTool.deleteActivity();
												back = true;
												break;
											case 10: // back
												back = true;
												break;
										}

									}
									} catch (ActivityNotFoundException e) {
										System.out.println(e.getMessage());
									}
									back = false;
									break;
								case 3: // add worker to project	
									System.out.printf("Enter username\n");
									inputString = reader.next();
									try {
										managementTool.addWorkerToProject(inputString);
									} catch (UserNotLoggedIn e) {
										System.out.println(e.getMessage());
									}
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
										System.out.printf("1. Add used time\n2. See used time\n3. See remaining time\n");
										System.out.printf("4. Set start date\n5. Set end date\n");
										System.out.printf("6. See start and end date\n");
										System.out.printf("7. Back\n");

										selector = reader.nextInt();

										switch (selector) {
											case 1: // Add used time
												System.out.printf("Enter used time\n");
												inputDouble = reader.nextDouble();
												managementTool.addUsedTime(inputDouble);
												break;
											case 2: // See used time
												System.out.printf("%f\n", managementTool.getUsedTime(managementTool.getEmployeeLoggedIn().getUsername()));
												break;
											case 3: // mangler funktion
												System.out.printf("\nmangler\n");
												break;
											case 4: // set start date
												System.out.printf("Enter year\n");
												temp1 = reader.nextInt();
												System.out.printf("Enter month number\n");
												temp2 = reader.nextInt();
												System.out.printf("Enter day of month\n");
												temp3 = reader.nextInt();
												GregorianCalendar startDate = new GregorianCalendar(temp1, temp2, temp3);
												managementTool.addActivityStartDate(startDate);
												break;
											case 5: // set end date
												System.out.printf("Enter year\n");
												temp1 = reader.nextInt();
												System.out.printf("Enter month number\n");
												temp2 = reader.nextInt();
												System.out.printf("Enter day of month\n");
												temp3 = reader.nextInt();
												GregorianCalendar endDate = new GregorianCalendar(temp1, temp2, temp3);
												try {
													managementTool.addActivityEndDate(endDate);
												} catch (startDateAfterEndDateException e) {
													System.out.println(e.getMessage());
												}
												break;
											case 6: // see start and end date
												System.out.printf("\nStart date:\n");
												System.out.println(managementTool.getActivityStartDate().getTime());
												System.out.printf("End date:\n");
												System.out.println(managementTool.getActivityEndDate().getTime());
												System.out.printf("\n");
												break;
											case 7: // Back
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
		reader.close();
	}
}
