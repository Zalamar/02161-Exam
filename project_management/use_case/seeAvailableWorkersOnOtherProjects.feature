Feature: Too see who is available on other projects
  Description: Extension to seeAvailableWorkers to include other projects
  Actors: worker and project manager

  Background:
    Given 5 worker is register in the company
      | AAAA | Person 1 |
      | AAAB | Person 2 |
      | AAAC | Person 3 |
      | AAAD | Person 4 |
      | AAAE | Person 5 |
    And a worker is logged in

    And a project is created named "011001"
    And the project manager selecets the project "011001"
    And a project manager "AAAA" is added to the project
    And a project manager adds the following workers to the project
      | AAAA |
      | AAAB |
      | AAAC |
      | AAAD |
      | AAAE |
    And a worker add an activity "001" to the project
    And the project manager adds the start time "010418" and end time "040418" to the activity "001"
    And a project manager adds the following workers to the activity
      | AAAA |
      | AAAB |
      | AAAD |
    And a project is created named "011002"
    And the project manager selecets the project "011002"
    And a project manager "AAAA" is added to the project
    And a worker add an activity "001" to the project

    And that no worker is logged in
    And the project manager is logged in

Scenario: See all workers, but some have more time
  Given the project manager selecets the project "011002"
  And the project manager adds the start time "010418" and end time "250418" to the activity "001"
  And the project manager selects see available workers
  Then he will se whos is avalible
    | AAAA | 4 |
    | AAAB | 4 |
    | AAAC | 0 |
    | AAAD | 4 |
    | AAAE | 0 |

Scenario: Only see some workers
  Given the project manager selecets the project "011001"
  And a worker add an activity "002" to the project
  And the project manager adds the start time "010418" and end time "070518" to the activity "002"
  And a project manager adds the following workers to the activity
    | AAAC |
    | AAAE |
  And the project manager selecets the project "011002"
  And the project manager adds the start time "010418" and end time "250418" to the activity "001"
  And the project manager selects see available workers
  Then he will se whos is avalible
    | AAAA | 4 |
    | AAAB | 4 |
    | AAAD | 4 |

  Scenario: The project manager will see the available workers based on how many percent days they have off
    Given the project manager selecets the project "011002"
    And the project manager adds the start time "010418" and end time "250418" to the activity "001"
    When the project manager selects see available workers, with 10 % off limit
    Then he will se whos is avalible
      | AAAC | 0 |
      | AAAE | 0 |

  Scenario: Try to do it with everyone assigned to project
    Given the project manager selecets the project "011001"
    And a worker selecets an activity "001"
    And a project manager adds the following workers to the activity
      | AAAC |
      | AAAE |
    And the project manager selecets the project "011002"
    And the project manager adds the start time "010418" and end time "250418" to the activity "001"
    And the project manager try to see available workers, with 10 % days off
    Then I get the error message "No worker is available"