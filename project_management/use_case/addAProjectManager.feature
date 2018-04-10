Feature: Add a project manager 
	Description: A worker assing a project manager
	Actors: worker
	
Background: 
	Given a worker is register in the company 
	And a worker is logged in 
	And a project is created named "011001" 
	
Scenario: Assign a project manager to a project 
	When a worker selecets the project "011001" 
	And a worker assign the project a project manager "AAAA" 
	Then the project have a project manager "AAAA" 
	
Scenario: Assign a project manager to a project with a manager allready assign 
	Given another worker is register in the company 
	And a worker selecets the project "011001" 
	And a project manager "AAAB" is assign to the project 
	When a worker assign the project a project manager "AAAA" 
	Then I get the error message "The project already have a project manager" 
	
Scenario: Assign a project manager to a project when not logged in 
	Given that no worker is logged in 
	When a worker selecets the project "011001" 
	And a worker assign the project a project manager "AAAA" 
	Then I get the error message "No user is logged in" 
	
Scenario: Try to add a project manager when no project is selected 
	When a worker assign the project a project manager "AAAA" 
	Then I get the error message "No project is selected"
	
Scenario: Remove a project manager 
	Given a worker selecets the project "011001" 
	And a project manager "AAAA" is assign to the project 
	When a worker selecets the project "011001" 
	And a worker remove a project manager 
	Then the project manager is removed 
	
Scenario: Remove a project manager when not logged in 
	When a worker remove a project manager 
	Then I get the error message "No project is selected" 

Scenario: Remove a project manager when not logged in 
	Given that no worker is logged in 
	When a worker selecets the project "011001" 
	And a worker remove a project manager 
	Then I get the error message "No user is logged in" 

