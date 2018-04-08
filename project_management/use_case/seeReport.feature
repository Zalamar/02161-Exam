#Feature: See report 
#	Description: A worker edits or adds informations to activitys
#	Actor: Project manager
#	
#Background: 
#	Given a worker is register in the company 
#	And a worker is logged in 
#	And a project is created named 011001 
#	And a project manager is added to the project 
#	And the worker is added to the acitivity
#	And a project manager selecets the project 011001
#	And a project manager selecets a activity
#	And a project manager adds estimated time 22 to the activity
#	And a worker selecets the project 011001 
#	And  a worker selecets the activity 001
#	And a worker adds his used time 10 the activity 
#	
#Scenario: See the report 
#	When a project manager selecets the project 011001 
#	And a project manager will see the project repoort for 011001 
#	Then he sees the repport