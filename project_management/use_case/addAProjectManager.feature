#Feature: Add a project manager 
#	Description: A worker Edit a project
#	Actors: worker
#	
#Background: 
#	Given a worker is register in the company 
#	And a worker is logged in 
#	And a project is created named 011001 
#	
#Scenario: Assign a project manager to a project
#	When a worker selecets the project 011001 
#	And a worker assign the project a project manager 
#	Then the project have a project manager 
#	
#Scenario: Assign a project manager to a project with a manager allready assign 
#	Given a worker selecets the project 011001
#	And a project manager is assign to the project 
#	When a worker assign the project a project manager 
#	Then I get the error message "The project allready have a project manager" 
#	
#Scenario: Assign a project manager to a project when not logged in 
#	Given a worker is logged out
#	When a worker selecets the project 011001
#	And a worker assign the project manager 
#	Then I get the error message "No user logged in" 
#	
#Scenario: Remove a project manager
#	Given a worker selecets the project 011001
#	And a project manager is assign to the project
#	When a worker selecets the project 011001
#	And a worker remove a project manager 
#	Then the project manager is removed 
#	
#	