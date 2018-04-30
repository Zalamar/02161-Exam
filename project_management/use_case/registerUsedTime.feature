Feature: Register used time and see it 
	Description: A worker adds, edits, and sees register time on activitys he is on
	Actor: worker
	
Background: 
	Given a worker is register in the company 
	And a worker is logged in 
	And a project is created named "011001" 
	And a worker selecets the project "011001" 
	And a worker add an activity "001" to the project 
	And a project manager "AAAA" is added to the project 
	And a worker is added to the project
	And a worker adds an worker to the activity "001"
	
Scenario: Add used time to the activity he on 
	When a worker selecets the project "011001" 
	And a worker selecets an activity "001" 
	And a worker adds his used time 10 the activity 
	Then there are time register 
		| AAAA | 10 |
	
#Scenario: try to add time when not logged in 
#	Given that no worker is logged in 
#	When a worker selecets the project "011001" 
#	And a worker selecets an activity "001" 
#	And a worker adds his used time 10 the activity 
#	Then I get the error message "No user is logged in" 
	
#Scenario: try to add time when not logged in 
#	Given that no worker is logged in 
#	When a worker selecets the project "011001" 
#	And a worker adds his used time 10 the activity 
#	Then I get the error message "No activity is selected" 
#	
#Scenario: try to add time when not logged in 
#	Given that no worker is logged in 
#	When a worker adds his used time 10 the activity 
#	Then I get the error message "No project is selected" 
#	
#Scenario: Edit an allready registered time 
#	Given a worker selecets the project "011001" 
#	And a worker selecets an activity "001" 
#	And a worker adds his used time 10 the activity 
#	And the project manager deselects the project 
#	When a worker selecets the project "011001" 
#	And a worker selecets an activity "001" 
#	And a worker edits a register time, adds 2 
#	Then the time is 12 
#	
#Scenario: Try to edit an allready registered time when not logged in 
#	Given a worker selecets the project "011001" 
#	And a worker selecets an activity "001" 
#	And a worker adds his used time 10 the activity 
#	And that no worker is logged in 
#	When a worker selecets the project "011001" 
#	And a worker selecets an activity "001" 
#	And a worker try to edits a register time, adds 3 
#	Then I get the error message "No user is logged in" 
#	
#Scenario: Register time on an activity the worker is not on 
#	Given another project is created named "011002" 
#	And a worker selecets the project "011002" 
#	And a worker add an activity "021" to the project 
#	When a worker selecets the project "011002" 
#	And a worker selecets an activity "021" 
#	And a worker adds his used time 10 the activity 
#	Then there are time register on the new activity 
#	
#Scenario: 
#	Try to register time on an activity the worker is not on, when no one is logged in 
#	Given that no worker is logged in 
#	When a worker selecets the project "011001" 
#	And a worker selecets an activity "001" 
#	When a worker adds his used time 10 the activity 
#	Then I get the error message "No user is logged in"