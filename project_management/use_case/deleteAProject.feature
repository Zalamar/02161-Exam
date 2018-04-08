#Feature: Creat a project
#	Description: A worker creat a project for the company
#	Actors: worker
#
#Background:
#	Given a worker is register in the company
#	And a worker is logged in
#	And the worker creat a project 011001
#
#Scenario: Delete a project
#	When a worker selecets the project 01001
#	When the worker delete the project 011001
#	Then there are no project
#	
#Scenario: Try to delete a non exiting project
#	When a worker selecets the project 011002
#	Then I get the error message "No project with that name"
#	
#Scenario: Try to delete when not logged in
#	Given a worker is logged out
#	When a worker selecets the project 011001
#	And the worker delete the project 011001
#	Then I get the error message "No user is logged in"