Feature: Add Personal Activitys 
	Description: A worker adds his/hers personal sctivitys
	Actors: worker
	
Background: 
	Given a worker is register in the company 
	And a worker is logged in
	
Scenario: The worker adds an activity 
	When a worker adds an activity "Holiday" with start time "091118" and end time "161118" 
	Then he has an activity called "Holiday" and time "091118" till "161118" 
	
Scenario: Try to do it when logged out 
	Given a worker is logged out 
	When a worker adds an activity "Holiday" with start time "091118" and end time "161118" 
	Then I get the addPersonalActivity error message "No user is logged in" 
	
Scenario: Adds an end time before the start time 
	When a worker adds an activity "Holiday" with start time "171118" and end time "161117" 
	Then I get the addPersonalActivity error message "The activity ends before it's starts"
	
Scenario: Add only a start time
	When When a worker adds an activity "Doctor" with start time "020418"
	Then he has an activity called "Doctor" and time "020418"