Feature: Too see who is available  
	Description: To be able to see who is available to work on an activity
	Actors: worker and project manager
	
Background: 
	Given 5 worker is register in the company 
		| AAAA | Person 1 | 
		| AAAB | Person 2 |
		| AAAC | Person 3 |
		| AAAD | Person 4 |
		| AAAE | Person 5 |
	And they all have an personal activity 
		| AAAA | Doctor | 020418 | |
		| AAAB | Doctor | 060418 | |
		| AAAC | Holiday | 010718 | 310719 |
		| AAAD | Party | 060418 | 080419 |
		| AAAE | Holiday | 010518 | 310719 |
	And a worker is logged in 	
	And a project is created named "011001"
	And the project manager selecets the project "011001"
	And a project manager "AAAA" is added to the project
	And a worker add an activity "001" to the project 
	And that no worker is logged in 
	And the project manager is logged in
	
Scenario: The project manager will see the available workers
	Given the project manager selecets the project "011001"
	And the project manager adds the start time "010418" and end time "310818" to the activity "001"
	When the project manager selects the project "011001" and acticity "001" 
	And the project manager selects see available workers 
	Then he will se whos is avalible 
		| AAAA | 1 day off |
		| AAAB | 1 day off |
		| AAAC | 31 days off |
		| AAAD | 2 days off |
		
#Scenario: See none are available because of they have no time
#	Given the project manager adds the start time 010418 and end time 03042018 to the activity "001"