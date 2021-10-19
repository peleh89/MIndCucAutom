@regression @ui @MB2P-HM7
Feature: Validating delete/edit functionalities in HR Application

  Background: First repeated steps in all scenarios
    Given user navigates to HR Application
    When user logs in with username "Mindtek" password "MindtekStudent"
    And user clicks on Create New Employee button
    And user creates employee with data
      | First name | Scott       |
      | Last name  | Ramos       |
      | Department | IT          |
      | Job Title  | Stock Clerk |
      | Salary     | 125000      |

  Scenario: Validating delete employee functionality in HR Application
    And user deletes created employee
    Then user validates that employee is deleted

  Scenario: Validating edit employee functionality in HR Application
    And user edit created employee first name
    Then user validates that employee first name is updated
