#Feature: Too see who is available  
#	Description: To be able to see who is available to work on an activity
#	Actors: worker and project manager
#	
#Background: 
#	Given 5 worker is register in the company 
#		| AAAA | "Person 1" | 
#		| AAAB | "Person 2" |
#		| AAAC | "Person 3" |
#		| AAAD | "Person 4" |
#		| AAAE | "Person 5" |
#	And a worker is logged in 
#	And they all have an personal activity 
#		| Doctor | 02042018 | |
#		| Doctor | 06042018 | |
#		| Holiday | 01072018 | 31072018 |
#		| Party | 06042018 | 08042018 |
#		| Holiday | 01052018 | 31072018 |
#	And a project is created named 011001 
#	And an activity 001 is added to the project 
#	And a project manager is added to the project 
#	And the worker is logged out 
#	And the project manager is logged in 
#	
#Scenario: The project manager will see the available workers
#	Given the project adds the start time 01042018 and end time 31082017 to the activity "001"
#	When the project manager selects the project "011001" and acticity "001" 
#	And the project manager selects see available workers 
#	Then he will se whos is avalible 
#		| AAAA | "1 day off" |
#		| AAAB | "1 day off" |
#		| AAAC | "31 days off" |
#		| AAAD | "2 days off" |
#		
