#Feature: Add an activity
#	Description: A worker edits or adds informations to activitys
#	Actor: Worker
#	
#Background: 
#	Given a worker is register in the company
#	And a worker is logged in
#	And a project is created named 011001
#	
#Scenario: Add an activity to the project
#	When a worker selecets the project 011001
#	When a worker add an activity 001 to the project
#	Then the activity is now in the project
#	
#Scenario: Try to creat one when not logged in
#	Given a worker is logged out
#	When a worker selecets the project 011001
#	And a worker add an activity 001 to the project
#	Then I get the error message "No user logged in"