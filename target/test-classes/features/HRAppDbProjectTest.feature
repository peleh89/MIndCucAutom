@DB2021 @regression
Feature: Validating Database

  Scenario Outline: Validating employee with database employee data
    Given user navigates to HRApp
    When user logs in with username “Mindtek” and password “MindtekStudent”
    And user search for employee with employee id <employeeId>
    Then user validates that employee data in UI matches with Database data with employee id <employeeId>
    Examples:
      | employeeId |
      | 100        |
      | 200        |
      | 206        |
