#Feature: See remaining time 
#	Description: A worker edits or adds informations to activitys
#	Actor: Worker and project manager
#	
#Background: 
#	Given a worker is register in the company 
#	And a worker is logged in
#	And a project is created named 011001
#	And a project manager is added to the project
#	And an activity 001 is added to the project
#	And the worker is added to the acitivity 001
#	And a project manager adds estimated time 22 to the activity
#	And a worker selecets the project 011001 
#	And  a worker selecets the activity 001
#	And a worker adds his used time 10 the activity 
#	
#	
#Scenario: See the register time on an activity 
#	When a woker selecets the project 011001
#	And  a woker selecets an activity 01
#	And a worker will see the used time 
#	Then he sees 22 
#	
#Scenario: See the remaingen time un a project 
#	When a woker selecets the project 011001
#	And  a woker selecets an activity 001
#	And a worker will see reaming time 
#	Then he sees 12