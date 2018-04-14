Feature: Remove an activity
	Description: A worker creat a project for the company
	Actors: worker and project leader

Background:
	Given a worker is register in the company
	And a worker is logged in
	And a project is created named "011001"
	And a worker selecets the project "011001"
	And a worker add an activity "001" to the project
	And a project manager "AAAA" is assign to the project 
	
Scenario: Remove an activity
	When a worker selecets an activity "001"
	And a worker deletes the activity
	Then there aint no activity
	
Scenario: Try to delete a activity when not logged in
	When a worker selecets the project "011001"
	And a worker selecets an activity "001"
	And a worker is logged out
	And a worker tries to delete the activity
	Then I get removeActivity error message "No user is logged in"
	
Scenario: Try to delete a activity when selected a non exitsing activity
	When a worker selecets the project "011001"
	And a worker selecets an activity "001"
	And activity is removed from project
	And a worker deletes the activity
	Then I get removeActivity error message "The activity dosnt exits"