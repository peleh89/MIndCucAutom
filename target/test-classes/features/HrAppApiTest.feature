@MB2-333 @api @ui @regression
Feature: validating HR App api calls
  Scenario Outline: Validating employees from specific department in UI matches with Api data
    Given user navigates to HR Application
    And user logs in with username "Mindtek" password "MindtekStudent"
    When user selects "<Department Name>" department
    And user stores ui data from selected department
    And user sends get employees api call for "<Department Name>" department
    Then user validates 200 status code
    And user validates ui data matches with api data with "<Department Name>" department employees
    When user gets employees from DB who works in "<Department Name>" department
    Then user validates ui data matches with DB data
    Examples:
    |Department Name|
    |IT             |
    |Marketing      |
  @MB2-333-1 @api
  Scenario: Validating employees api calls
    Given user creates employee with api post call with data
      | First Name | Jim  |
      | Last Name  | Beam |
    Then user validates 201 status code
    And user validates employee is created with api get call
    And user validates 200 status code
    When user updates employee data
      | First Name | Jack    |
      | Last Name  | Daniels |
    And user validates 200 status code
    And user validates employee info is updated with api get call
    And user validates 200 status code
    When user deletes employee with api delete call
    Then user validates 204 status code
    And user validates employee is deleted with api get call
    And user validates 200 status code
