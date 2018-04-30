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
		| AAAC | Holiday | 010418 | 300418 |
		| AAAD | Party | 060418 | 080418 |
		| AAAE | Holiday | 010318 | 100418 |
	And a worker is logged in 
	And a project is created named "011001" 
	And the project manager selecets the project "011001" 
	And a project manager "AAAA" is added to the project 
	And a worker add an activity "001" to the project 
	And that no worker is logged in 
	And the project manager is logged in 
	
Scenario: The project manager will see the available workers 
	Given the project manager selecets the project "011001" 
	And the project manager adds the start time "010418" and end time "300418" to the activity "001" 
	When the project manager selects the project "011001" and acticity "001" 
	And the project manager selects see available workers 
	Then he will se whos is avalible 
		| AAAA | 1 |
		| AAAB | 1 |
		| AAAD | 3 |
		| AAAE | 10|
		
Scenario: See none are available because of they have no time 
	Given they all have more personal activity 
		| AAAA | Birthday | 010418 | 040418 |
		| AAAB | Birthday | 010418 | 040418 |
		| AAAD | Birthday | 010418 | 040418 |
	And the project manager is logged in 
	And the project manager selecets the project "011001" 
	And the project manager adds the start time "010418" and end time "020418" to the activity "001" 
	When the project manager selects the project "011001" and acticity "001" 
	And the project manager try to see available workers 
	Then I get the error message "No worker is available" 
	
Scenario: Try whne logged out 
	Given the project manager is logged in 
	And the project manager selecets the project "011001" 
	And the project manager adds the start time "010418" and end time "300418" to the activity "001" 
	When the project manager selects the project "011001" and acticity "001" 
	And that no project mangager is logged in 
	And the project manager try to see available workers 
	Then I get the error message "No user is logged in" 
	
Scenario: try when no project is selected 
	Given the project manager is logged in 
	And the project manager selecets the project "011001" 
	And the project manager adds the start time "010418" and end time "300418" to the activity "001" 
	When the project manager deselects the project 
	And the project manager try to see available workers 
	Then I get the error message "No project is selected" 
	
Scenario: try when no project is selected 
	Given the project manager is logged in 
	And the project manager selecets the project "011001" 
	And the project manager adds the start time "010418" and end time "300418" to the activity "001" 
	When the project manager deselects the project 
	And the project manager selecets the project "011001" 
	And the project manager try to see available workers 
	Then I get the error message "No activity is selected" 
	
Scenario:
The project manager will see the available workers out from how many procent days they can have off 
	Given the project manager selecets the project "011001" 
	And the project manager adds the start time "010418" and end time "300418" to the activity "001" 
	When the project manager selects the project "011001" and acticity "001" 
	And the project manager selects see available workers, with 10 % off limit 
	Then he will se whos is avalible 
		| AAAA | 1 |
		| AAAB | 1 |
		| AAAD | 3 |
		
Scenario: Try to do it, when he wants 0 days off 
	Given the project manager selecets the project "011001" 
	And the project manager adds the start time "010418" and end time "300418" to the activity "001" 
	When the project manager selects the project "011001" and acticity "001" 
	And the project manager try to see available workers, with 0 % days off 
	Then I get the error message "No worker is available" 