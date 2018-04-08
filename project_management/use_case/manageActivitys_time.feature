#Feature: Manage the activity - Add time 
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
#Scenario: Add time to the activity 
#	When a project manager selecets the project 011001 
#	And a project manager selecets an activity 001
#	And a project manager adds estimated time 22 to the activity 
#	Then there are time 22 register to the activity 
#	
#Scenario: try to add time to the activity when no on is logged in 
#	Given a project manager is not logged in 
#	When a project manager selecets the project 011001 
#	And a project manager selecets an activity 001
#	And a project manager adds estimated time 11 to the activity 
#	Then I get the error message "No user is logged in" 
#	
#Scenario: Lets a worker add estimate time to an activity 
#	Given the project mananger is logged out 
#	And a worker is loggede in 
#	When a worker selecets the project 011001 
#	And a worker selecets an activity 001
#	When a worker adds estimated time 22 to the activity 
#	Then there are time 22 register to the activity 
#	
#Scenario: Set the starter and end time of a project 
#	When a project manager selecets the project 011001 
#	And a project manager selecets an activity 001
#	And a project manager added the start date 221118 
#	And a project manager added the end date 241118 
#	Then there are a start date 221118 
#	And there are a start date 241118 
#	
#Scenario: try to add start time when not logged in 
#	Given the project mananger is logged out 
#	When a project manager selecets the project 011001 
#	And a project manager selecets an activity 001
#	And a project manager added the start date 221118 
#	Then I get the error message "No user is logged in" 
#	
#Scenario: try to add end time when not logged in 
#	Given the project mananger is logged out 
#	And a project manager added the start date 221118 
#	When a project manager selecets the project 011001 
#	And a project manager selecets an activity 01
#	And a project manager added the end date 241118 
#	Then I get the error message "No user is logged in" 
#	
#Scenario: Lets a worker add time to a activity
#	Given the project mananger is logged out 
#	And a worker is logged in
#	When a worker selecets the project 011001 
#	And a worker selecets an activity 001
#	And a worker added the start date 221118 
#	And a worker added the end date 241118 
#	Then there are a start date 221118 
#	And there are a start date 241118 