#Feature: Manage the activity - Add worker 
#	Description: A worker edits or adds informations to activitys
#	Actor: Project leader and worker
#	
#Background: 
#	Given a worker is register in the company 
#	And a worker is logged in 
#	And a project is created named 011001 
#	And an activity 001 is added to the project 
#	And a project manager is added to the project 
#	And the worker is logged out 
#	And the project leader is logged in 
#	
#Scenario: Add worker to an activity 
#	When a project manager selecets the project 011001 
#	And a project manager selecets an activity 001
#	And a project manger adds an worker to the activity 
#	Then the worker is added to the activity 
#	
#Scenario: try to add a worker to the activity when no on is logged in 
#	Given a project manager is not logged in 
#	When a project manager selecets the project 011001 
#	And a project manager selecets an activity 001
#	And a project manger adds an worker to the activity 
#	Then I get the error message "No user is logged in" 
#	
#Scenario: Lets a worker adds a worker to an activity 
#	Given the project mananger is logged out 
#	And a worker is loggede in 
#	When a worker selecets the project 011001 
#	And a worker selecets an activity 001
#	And a worker adds an worker to the activity 
#	Then the worker is added to the activity