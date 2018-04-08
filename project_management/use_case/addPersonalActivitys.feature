#Feature: Add Personal Activitys 
#	Description: A worker adds his/hers personal sctivitys
#	Actors: worker
#	
#Background: 
#	Given a worker is register in the company 
#	And a worker is logged in 
#	
#Scenario: The worker adds an activity 
#	When a worker adds an activity "Holiday" with start time 09112018 and end time 16112018 
#	Then he has an activity called "Holiday" and time 09112018 till 16112018 
#	
#Scenario: Try to do it when logged out 
#	Given a worker is logged out 
#	When a worker adds an activity "Holiday" with start time 09112018 and end time 16112018 
#	Then I get the error message "No user logged in" 
#	
#Scenario: Adds an end time before the start time 
#	When a worker adds an activity "Holiday" with start time 09112018 and end time 16112017 
#	Then I get the error message "The activity ends before it's starts"
#	
#Scenario: Add only a start time
#	When When a worker adds an activity "Doctor" with start time 02042018
#	Then he has an activity called "Doctor" and time 02042018