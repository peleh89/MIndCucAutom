@DB @regression @smoke @DB101
Feature: Validate create employee functionality
  Scenario: Validate create an employee persisted in Database
    Given New employee was created
    When User accesses the Database
    Then User validates new employee
