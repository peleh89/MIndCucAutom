@regression @smoke @ui @MB2P-204
  Feature: Create Employee functionality Validation

    Background: First repeated steps in all scenarios
      Given user navigates to HR Application
      When user logs in with username "Mindtek" password "MindtekStudent"
      And user clicks on Create New Employee button

    Scenario Outline: Validating Create employee functionality with valid data
      And user creates employee with data
        | First name | <First name> |
        | Last name  | <Last name>  |
        | Department | <Department> |
        | Job Title  | <Job Title>  |
        | Salary     | <Salary>     |
      Then user validates that employee is in list of employees
      Examples:
        | First name | Last name | Department | Job Title     | Salary |
        | Adrian     | Torres    | IT         | Stock Clerk   | 100000 |
        | Peter      | Well      | Shipping   | Sales Manager | 125125 |
        | Will       | Lee       | Marketing  | Accountant    | 130000 |

      @MB2P-204-TC2
      Scenario: Validating Create employee functionality without providing name
        And user creates employee with data
          | Department | IT          |
          | Job Title  | Stock Clerk |
          | Salary     | 100000      |
        Then user validates error message in HR App "First name and last name are required fields."

        Scenario: Validating Create employee functionality with negative salary
          And user creates employee with data
            | First name | Scott      |
            | Last name  | Ramos      |
            | Department | IT          |
            | Job Title  | Stock Clerk |
            | Salary     | -89000      |
          Then user validates error message in HR App "Salary can not be negative."