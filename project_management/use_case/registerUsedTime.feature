#Feature: Register used time and see it 
#	Description: A worker adds, edits, and sees register time on activitys he is on
#	Actor: worker
#	
#Background: 
#	Given a worker is register in the company 
#	And a worker is logged in 
#	And a project is created named "011001" 
#	And an activity "001" is added to the project 
#	And a project manager is added to the project 
#	And the worker is added to the acitivity 
#	
#Scenario: Add used time to the activity he on 
#	When a worker selecets the project "011001" 
#	And  a worker selecets the activity "001"
#	And a worker adds his used time 10 the activity 
#	Then there are time register 
#	
#Scenario: try to add time when not logged in 
#	Given the worker is logged out 
#	When a worker selecets the project "011001" 
#	And  a worker selecets the activity "001"
#	And a worker adds his used time the activity
#	Then I get the error message "No user is logged in" 
#	
#Scenario: Edit an allready registered time 
#	Given a worker selecets the project "011001" 
#	And  a worker selecets the activity "001"
#	And a worker adds his used time 10 the activity
#	And deselects the activity and project 
#	When a worker selecets the project "011001" 
#	And  a worker selecets the activity "001"
#	And a worker edits a register time, adds 2 
#	Then the time is 12 
#	
#Scenario: Try to edit an allready registered time when not logged in 
#	Given a worker selecets the project "011001" 
#	And  a worker selecets the activity "001"
#	And a worker adds his used time 10 the activity 
#	And the worker is logged out 
#	When a worker selecets the project "011001" 
#	And a worker selecets the activity "001"
#	And a worker edits a register time 
#	Then I get the error message "No user is logged in" 
#	
#Scenario: Register time on an activity the worker is not on 
#	Given another project is created named "011002"
#	And an activity 21 is added to the new project
#	When a worker selecets the project "011002"
#	And  a worker selecets the activity "021"
#	And a worker adds his used time the activity 
#	Then there are time register on the new activity 
#	
#Scenario: Try to register time on an activity the worker is not on, when no one is logged in 
#	Given the worker is logged out
#	When a worker selecets the project "011001"
#	And  a worker selecets the activity "001"
#	When a worker adds his used time the activity 
#	Then I get the error message "No user is logged in"