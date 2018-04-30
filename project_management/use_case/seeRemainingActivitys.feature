Feature: See remaining activitys that are missing registren time
	Description: A worker clicks on a button to see what activitys he is missing to register time on
	Actor: worker and project manager
	
Background: 
	Given a worker is register in the company 
	And a worker is logged in 
	And a project is created named "011001" 
	And a worker selecets the project "011001" 
	And a worker add an activity "001" to the project 
	And a project manager "AAAA" is added to the project 
	And a worker adds an worker to the activity "001" 
	And a worker selecets the project "011001"
	And a worker add an activity "002" to the project
	And a worker adds an worker to the activity "002"  
	And a worker deselect the activity  
	And a worker selecets an activity "001" 
	And a worker adds his used time 10 the activity
	And a worker deselect the activity

Scenario: See they remaining activitys that are missing timed used
	When a worker clicks See unregister time used on activitys
	Then a worker sees
			|002|
	
Scenario: Try to see remaining activitys that are missing timed used when not logged in
	Given that no worker is logged in
	When a worker selecets the project "011001" 
	And a worker try to click see unregister time used on activitys
	Then I get the error message "No user is logged in"

Scenario: Try to see remaining activitys that are missing timed used when no project is selected
	When a worker try to click see unregister time used on activitys
	Then I get the error message "No project is selected"
	
#Scenario: Try to see remaining activitys that are missing timed used when not logged in
#	Given that no worker is logged in
#	When a worker selecets the project "011001" 
#	And a worker try to click see unregister time used on activitys
#	Then I get the error message "No user is logged in"
#
#Scenario: Try to see remaining activitys that are missing timed used when no project is selected
#	When a worker try to click see unregister time used on activitys
#	Then I get the error message "No project is selected"
#	
#Scenario: See they remaining activitys that are missing timed used, when no more activitys are missing the used time
#	Given a worker selecets the project "011001" 
#	And  a worker selecets an activity "002" 
#	And a worker adds his used time 10 the activity
#	When a worker try to click see unregister time used on activitys
#	Then a worker sees "No activity's are missing register used time"
#
#Scenario: See they remaining activitys that are missing timed used, on more then 1 actvity
#	Given a worker makes 10 more activitys in project "011001"
#	When a worker clicks See unregister time used on activitys
#	Then a worker sees
#		|002|003|004|005|006|007|008|009|010|011|012|