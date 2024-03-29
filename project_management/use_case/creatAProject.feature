Feature: Creat a project
	Description: A worker creat a project for the company
	Actors: worker
	
Background:
	Given a worker is register in the company
	And a worker is logged in
	
Scenario: Creat a project
	When the worker creat a project "011001"
	Then the project "011001" is created	

Scenario: The worker is not logged in
	Given that no worker is logged in
	When they try to creat a project "011001"
	Then I get the error message "No user is logged in"
	
Scenario: Try to creat a project with the same name
	Given the worker creat a project "011001"
	When they try to creat a project "011001"
	Then I get the error message "Project already exiting"
	