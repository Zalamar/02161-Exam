Feature: Add an activity
	Description: A worker edits or adds informations to activitys
	Actor: Worker
	
Background: 
	Given a worker is register in the company
	And a worker is logged in
	And a project is created named "011001"
	
Scenario: Add an activity to the project
	When a worker selecets the project "011001"
	When a worker add an activity "001" to the project
	Then the activity "001" is now in the project
	
Scenario: Try to creat one when not logged in
	Given that no worker is logged in
	When a worker selecets the project "011001"
	And a worker add an activity "001" to the project
	Then I get the error message "No user is logged in"
	
Scenario: Try to creat one when no project is selected
	When a worker add an activity "001" to the project
	Then I get the error message "No project is selected"
	
Scenario: Try to creat an activity with the same name
	When a worker add an activity "001" to the project
	And a worker add an activity "001" to the project
	Then I get the error message "Activity already exiting"