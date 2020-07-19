Feature: Delete computer
  Delete computer from the computer database

Scenario: Success message displayed when computer deleted
    Given I want to edit or delete computer "<name>"
    When I delete the computer
    Then a success message "<message>" is displayed

  Examples:
    | name                  | message                          |
    | TestDeleteComputer001 | Done! Computer has been deleted  |


Scenario: Deleted computer no longer found in search
    Given I want to edit or delete computer "<name>"
    When I delete the computer
    Then the computer "<name>" is not found in search

  Examples:
    | name                  | message                          |
    | TestDeleteComputer002 | Done! Computer has been deleted  |