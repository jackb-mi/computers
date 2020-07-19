Feature: Add computer
  Add computer to the computer database


Scenario: Success message displayed when computer added
    Given I want to add a computer
    When I add computer "<name>"
    Then a success message "<message>" is displayed

Examples:
    | name               | message                                               |
    | TestAddComputer001 | Done! Computer TestAddComputer001 has been created    |



Scenario: Computers created with correct details
    Given I want to add a computer
    When I add computers:
    | name   | introduced date      | discontinued date     | company       |
    | <name> | <introduced date>    | <discontinued date>   | <company>     |

    Then the computers are found with correct details:
    | name   | introduced date      | discontinued date     | company       |
    | <name> | <introduced date>    | <discontinued date>   | <company>     |

Examples:
    | name               | introduced date  | discontinued date | company           |
    | TestAddComputer002 |                  |                   |                   |
    | TestAddComputer003 | 2020-01-01       |                   |                   |
    | TestAddComputer004 | 2020-01-01       |                   | ASUS              |
    | TestAddComputer005 | 2020-01-01       |                   | Apple Inc.        |
    | TestAddComputer006 | 2020-01-01       | 2020-02-01        | Thinking Machines |




Scenario: Name is mandatory when adding computer
    Given I want to add a computer
    When I add computer "<name>"
    Then I am on the Add a computer page

Examples:
    | name               | message                                               |
    |                    | Done! Computer TestAddComputer001 has been created    |



Scenario: Cancel add computer flow
    Given I want to add a computer
    When I cancel the add computer flow
    Then I am on the Computer database search page