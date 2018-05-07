package project_management.app;

/**
 * @author Nikolaj
 *
 */

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

	public static void main(String[] args) throws UserNotLoggedIn, NoProjectWithThatName, NoProjectIsSelected,
			TheProjectAlreadyHaveAManager, ActivityNotFoundException, NoActivityIsSelectedException,
			workerNotOnProjectException, NoWorkerExitingEception {

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
		boolean userLoggedIn = false;

		while (quit == false) {

			System.out.printf("1. Login\n");
			System.out.printf("2. Quit\n");

			selector = reader.nextInt();

			switch (selector) {
			case 1: // Login
				System.out.println("Enter username\n");
				inputString = reader.next();
				managementTool.login(inputString);

				try {
					managementTool.isEmployeeLoggedIn();
					userLoggedIn = true;
				} catch (UserNotLoggedIn e) {
					userLoggedIn = false;
				}

				if (userLoggedIn == false) {
					System.out.printf("There is no user with that username\n");
				}

				while (userLoggedIn && quit == false) {
					System.out.printf("1. Create project\n2. Select project\n3. Add user\n4. Logout\n");

					selector = reader.nextInt();

					switch (selector) {
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
						System.out.println("List of projects");
						List<String> listOfProjects = managementTool.returnListOfProjects();
						for (String s : listOfProjects) {
							System.out.println(s);
						}
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
										System.out.println("List of activities:");
										List<String> listOfActivities = managementTool.returnListOfActivities();
										for (String s : listOfActivities) {
											System.out.println(s);
										}
										
										System.out.printf("Enter activity ID\n");
										inputString = reader.next();

										try {
											managementTool.selectActivity(inputString);

											while (back == false) {
												System.out.printf(
														"1. Add used time\n2. See own used time\n3. See all used time\n");
												System.out.printf("4. Assign a worker to this activity\n");
												System.out.printf("5. Set start date\n6. Set end date\n");
												System.out.printf("7. See start and end date\n");
												System.out.printf("8. See who's available\n");
												System.out.printf("9. Remove activity\n10. Back\n");

												selector = reader.nextInt();
												boolean incorrectDate;

												switch (selector) {
												case 1: // Add used time
													System.out.printf("Enter used time\n");
													inputDouble = reader.nextDouble();
													managementTool.addUsedTime(inputDouble);
													break;
												case 2: // See used time
													System.out.printf("%f\n", managementTool.getUsedTime(
															managementTool.getEmployeeLoggedIn().getUsername()));
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
													incorrectDate = true;
													while(incorrectDate) {
														temp1 = reader.nextInt();
														if (temp1 < 0) {
															System.out.printf("The year cannot be negative");
														} else {
															incorrectDate = false;
														}
													}
													System.out.printf("Enter month number\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp2 = reader.nextInt();
														if (temp2 < 1) {
															System.out.printf("The month cannot be below 1");
														} else if (temp2 > 12) {
															System.out.printf("The month cannot be above 12");
														} else {
															incorrectDate = false;
														}
													}
													System.out.printf("Enter day of month\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp3 = reader.nextInt();
														if (temp3 < 1) {
															System.out.printf("The day cannot be below 1");
														} else if (temp3 > 12) {
															System.out.printf("The day cannot be above 31");
														} else {
															incorrectDate = false;
														}
													}
													GregorianCalendar startDate = new GregorianCalendar(temp1, temp2,
															temp3);
													managementTool.addActivityStartDate(startDate);
													break;
												case 6: // set end date
													System.out.printf("Enter year\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp1 = reader.nextInt();
														if (temp1 < 0) {
															System.out.printf("The year cannot be negative");
														} else {
															incorrectDate = false;
														}
													}
													System.out.printf("Enter month number\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp2 = reader.nextInt();
														if (temp2 < 1) {
															System.out.printf("The month cannot be below 1");
														} else if (temp2 > 12) {
															System.out.printf("The month cannot be above 12");
														} else {
															incorrectDate = false;
														}
													}
													System.out.printf("Enter day of month\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp3 = reader.nextInt();
														if (temp3 < 1) {
															System.out.printf("The day cannot be below 1");
														} else if (temp3 > 12) {
															System.out.printf("The day cannot be above 31");
														} else {
															incorrectDate = false;
														}
													}
													GregorianCalendar endDate = new GregorianCalendar(temp1, temp2,
															temp3);
													try {
														managementTool.addActivityEndDate(endDate);
													} catch (startDateAfterEndDateException e) {
														System.out.println(e.getMessage());
													}
													break;
												case 7: // see start and end date
													try {
														System.out.printf("\nStart date:\n" + managementTool.getActivityStartDate().getTime());
														System.out.printf("\nEnd date:\n" + managementTool.getActivityEndDate().getTime());
														System.out.printf("\n");
													} catch (NullPointerException e) {
														System.out.println("Dates incomplete on project.");
													}
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
									System.out.printf(
											"1. Add project manager\n2. Add an activity\n3. Select an activity\n");
									System.out.printf("4. Back\n");

									selector = reader.nextInt();
									boolean incorrectDate;

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
												System.out.printf("1. Add used time\n2. See used time\n");
												System.out.printf("3. Set start date\n4. Set end date\n");
												System.out.printf("5. See start and end date\n");
												System.out.printf("6. Back\n");

												selector = reader.nextInt();

												switch (selector) {
												case 1: // Add used time
													System.out.printf("Enter used time\n");
													inputDouble = reader.nextDouble();
													managementTool.addUsedTime(inputDouble);
													break;
												case 2: // See used time
													System.out.printf("%f\n", managementTool.getUsedTime(
															managementTool.getEmployeeLoggedIn().getUsername()));
													break;
												case 3: // set start date
													System.out.printf("Enter year\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp1 = reader.nextInt();
														if (temp1 < 0) {
															System.out.printf("The year cannot be negative");
														} else {
															incorrectDate = false;
														}
													}
													System.out.printf("Enter month number\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp2 = reader.nextInt();
														if (temp2 < 1) {
															System.out.printf("The month cannot be below 1");
														} else if (temp2 > 12) {
															System.out.printf("The month cannot be above 12");
														} else {
															incorrectDate = false;
														}
													}
													System.out.printf("Enter day of month\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp3 = reader.nextInt();
														if (temp3 < 1) {
															System.out.printf("The day cannot be below 1");
														} else if (temp3 > 12) {
															System.out.printf("The day cannot be above 31");
														} else {
															incorrectDate = false;
														}
													}
													GregorianCalendar startDate = new GregorianCalendar(temp1, temp2,
															temp3);
													managementTool.addActivityStartDate(startDate);
													break;
												case 4: // set end date
													System.out.printf("Enter year\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp1 = reader.nextInt();
														if (temp1 < 0) {
															System.out.printf("The year cannot be negative");
														} else {
															incorrectDate = false;
														}
													}
													System.out.printf("Enter month number\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp2 = reader.nextInt();
														if (temp2 < 1) {
															System.out.printf("The month cannot be below 1");
														} else if (temp2 > 12) {
															System.out.printf("The month cannot be above 12");
														} else {
															incorrectDate = false;
														}
													}
													System.out.printf("Enter day of month\n");
													incorrectDate = true;
													while(incorrectDate) {
														temp3 = reader.nextInt();
														if (temp3 < 1) {
															System.out.printf("The day cannot be below 1");
														} else if (temp3 > 12) {
															System.out.printf("The day cannot be above 31");
														} else {
															incorrectDate = false;
														}
													}
													GregorianCalendar endDate = new GregorianCalendar(temp1, temp2,
															temp3);
													try {
														managementTool.addActivityEndDate(endDate);
													} catch (startDateAfterEndDateException e) {
														System.out.println(e.getMessage());
													}
													break;
												case 5: // see start and end date
													try {
														System.out.printf("\nStart date:\n" + managementTool.getActivityStartDate().getTime());
														System.out.printf("\nEnd date:\n" + managementTool.getActivityEndDate().getTime());
														System.out.printf("\n");
													} catch (NullPointerException e) {
														System.out.println("Dates incomplete on project.");
													}
													break;
												case 6: // Back
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
					case 3: // Add user
						System.out.printf("Enter username\n");
						String tempString = reader.next();
						System.out.printf("Enter name\n");
						String tempString2 = reader.nextLine();
						tempString2 = reader.nextLine();
						managementTool.addWorker(tempString, tempString2);
						break;
					case 4: // Logout
						managementTool.logout();
						quit = true;
						break;
					}

				}
				quit = false;
				break;
			case 2:
				quit = true;
				break;
			}

		}
		reader.close();
	}

	private static GregorianCalendar setDate(Scanner reader) {
		int year;
		int month;
		int day;
		System.out.printf("Enter year\n");
		year = reader.nextInt();
		System.out.printf("Enter month number\n");
		month = reader.nextInt() - 1;
		System.out.printf("Enter day of month\n");
		day = reader.nextInt();
		GregorianCalendar startDate = new GregorianCalendar(year, month, day);
		return startDate;
	}

	private static void showDate(ManagementTool managementTool) {
		GregorianCalendar startDate = managementTool.getActivityStartDate();
		if (startDate != null) {
			System.out.printf("\nStart date:\n");
			System.out.println(managementTool.getActivityStartDate().getTime());
		}
		GregorianCalendar endDate = managementTool.getActivityEndDate();
		if (endDate != null) {
			System.out.printf("End date:\n");
			System.out.println(endDate.getTime());
		}
		System.out.printf("\n");
	}
}
