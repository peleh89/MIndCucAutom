@regression @smoke @ui @MB2P-118
Feature: Validating Edit Reset and Cancel Timesheet functionality
  Background:
    Given user navigate to orangeHRM application
    When user provides username "Admin" and password "admin123" and clicks on login button
    And  user clicks on Time module
    And  user select employeeTimesheet from TimesSheets dropdown and click


  Scenario: Validating reset timesheet functionality
    And   user clicks on edit button and add a row for testing
    And   user clicks on edit button and update some of project name and click reset button
    Then  user validates that project name didn't update

  Scenario: Validating cancel timesheet Functionality
    And  user clicks on edit button and click on cancel button
    Then user validates that is on employeeTimesheet page


