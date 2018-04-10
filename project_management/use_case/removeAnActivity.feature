#Feature: Remove an activity
#	Description: A worker creat a project for the company
#	Actors: worker and project leader
#
#Background:
#	Given a worker is register in the company
#	And a worker is logged in
#	And the worker creat a project 011001
#	And a worker selecets the project 011001 
#	And an activity "001" is added to the project 
#	And a project manager is added to the project
#	And the project manager is logged in
#	And the worker is logged out
#	
#Scenario: Remove an activity
#	When a project manager selecets the project 01001
#	And a worker selecets an activity "001"
#	And a worker deletes the activity
#	Then there aint no activity
#	
#Scenario: Try to delete a activity when not logged in
#	Given the worker is logged out
#	When a worker selecets the project "01001"
#	And a worker selecets an activity "001"
#	And a worker deletes the activity
#	Then I get the error message "No user is logged in"
#	
#Scenario: Try to delete a activity when selected a non exitsing activity
#	When a worker selecets the project "01001"
#	And a worker selecets an activity "002"
#	And a worker deletes the activity
#	Then I get the error message "The activity dosnt exits"
#	
#Scenario: Delete a project as a worker
#	Given a worker is logged in
#	And the project manager is logged out
#	When a worker selecets the project 01001
#	And a worker selecets an activity "001"
#	And a worker deletes the activity
#	Then there aint no activity