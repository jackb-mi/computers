Feature: Edit computer
  Edit computer in the computer database

Scenario: Success message displayed when computer deleted edited
    Given I want to edit or delete computer "<name>"
    When I edit the computer
    Then a success message "<message>" is displayed

  Examples:
    | name                  | message                                               |
    | TestEditComputer001   | Done! Computer TestEditComputer001 has been updated   |



Scenario: Computers updated with correct details
    Given I want to edit or delete computer "<name>"
    When I edit the computers:
    | new name      | new introduced date   | new discontinued date     | new company       |
    | <new name>    | <new introduced date> | <new discontinued date>   | <new company>     |

    Then the computers are found with correct details:
    | expected name result   | expected introduced date result   | expected discontinued date result   | expected company result   |
    | <expected name result> | <expected introduced date result> | <expected discontinued date result> | <expected company result> |

Examples:
    | name                | new name                | expected name result   | introduced date | new introduced date  | expected introduced date result | discontinued date | new discontinued date | expected discontinued date result | company           | new company | expected company result |
    | TestEditComputer002 |                         | TestEditComputer002    | 2020-01-01      |                      | 2020-01-01                      |                   | 2020-02-01            | 2020-02-01                        | Thinking Machines |             | Thinking Machines       |
    | TestEditComputer003 | TestUpdatedComputer003  | TestUpdatedComputer003 | 2020-01-01      | 2019-02-01           | 2019-02-01                      | 2020-02-01        | 2020-01-01            | 2020-01-01                        | Thinking Machines | Apple Inc.  | Apple Inc.              |