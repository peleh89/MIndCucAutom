@api @smoke @regression @HR2021
Feature: Validating Api Calls for Employees

  Scenario Outline: Validating POST Api call for employees
    Given User sends create employee api post call with data
      | firstName      | <firstName>      |
      | lastName       | <lastName>       |
      | departmentName | <departmentName> |
    Then user validates status code 201
    Then user validates data populated in UI
      | firstName      | <firstName>      |
      | lastName       | <lastName>       |
      | departmentName | <departmentName> |
    Then  User validates employee data os persisted into DB
    Then  User validates data with get employee api call
      | firstName      | <firstName>      |
      | lastName       | <lastName>       |
      | departmentName | <departmentName> |
    Examples:
      | firstName | lastName | departmentName |
      | Ben       | Gory     | IT             |