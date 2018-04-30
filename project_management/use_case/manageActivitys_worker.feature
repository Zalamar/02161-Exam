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
	When a worker selecets the project "011001" 
	And a worker selecets an activity "001"
	And a worker is logged out
	And a worker adds an worker to the activity 
	Then I get the manageActivityWorkers error message "No user is logged in" 