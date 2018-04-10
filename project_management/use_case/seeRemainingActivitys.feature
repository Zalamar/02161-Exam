#Feature: See remaining activitys that are missing registren time
#	Description: A worker clicks on a button to see what activitys he is missing to register time on
#	Actor: worker and project manager
#	
#Background: 
#	Given a worker is register in the company 
#	And a worker is logged in 
#	And a project is created named "011001" 
#	And an activity "001" is added to the project 
#	And a project manager is added to the project 
#	And the worker is added to the acitivity "001" 
#	And a worker selecets the project "011001"
#	And an activity "002"  is added to the project
#	And the worker is added to the acitivity "002"  
#	And a worker deselect the activity "002" 
#	And a worker selecets the project "011001" 
#	And  a worker selecets the activity "001" 
#	And a worker adds his used time 10 the activity
#	And a worker deselect the activity "001" 
#
#Scenario: See they remaining activitys that are missing timed used
#	When a worker clicks "See unregister time used on activitys"
#	Then a worker sees "Activity "002"  havn't been register for used time"
#	
#Scenario: Try to see remaining activitys that are missing timed used when not logged in
#	Given that no worker is logged in
#	When a worker clicks "See unregister time used on activitys" 
#	Then I get the error message "No user is logged in"
#	
#Scenario: See they remaining activitys that are missing timed used, when no more activitys are missing the used time
#	Given a worker selecets the project "011001" 
#	And  a worker selecets the activity "002" 
#	And a worker adds his used time 10 the activity
#	And a worker deselect the activity "002" 
#	When a worker clicks "See unregister time used on activitys" 
#	Then a worker sees "No activitys are missing to register used time"