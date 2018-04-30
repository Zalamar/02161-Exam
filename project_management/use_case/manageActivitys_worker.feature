Feature: Manage the activity - Add worker 
	Description: A worker edits or adds informations to activitys
	Actor: Project leader and worker
	
Background: 
	Given a worker is register in the company 
	And a worker is logged in 
	And a project is created named "011001"
	And a worker selecets the project "011001" 
	And a worker add an activity "001" to the project
	And a worker is added to the project 
	
Scenario: Add worker to an activity 
	When a worker selecets an activity "001"
	And a worker adds an worker to the activity 
	Then the worker is added to the activity
	And I get no errors
	
Scenario: try to add a worker to the activity when no on is logged in 
	When a worker is logged out
	And a worker adds an worker to the activity 
	Then I get the error message "No user is logged in" 
	
Scenario: attempting to add worker without selecting project
	When a worker deselecets a project
	And a worker adds an worker to the activity
	Then I get the error message "No project is selected"
	
Scenario: attempting to add worker without selecting an activity
	When a worker adds an worker to the activity
	Then I get the error message "No activity is selected"
	
Scenario: attempting to add worker without selecting an activity
	When a worker is removed from project
	And a worker selecets an activity "001"
	And a worker adds an worker to the activity
	Then I get the error message "Worker is not on project"