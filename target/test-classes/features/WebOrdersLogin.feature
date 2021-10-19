@regression @smoke @ui
Feature: Validating WebOrders application login functionality
# Test case description
  Scenario: Validating login functionality with valid credential
    #precondition
    Given user navigate to weborders application
    #acction
    When user provides username "Tester" and password "test" and clicks on login
    #validation step
    Then user validates application is logged in

  Scenario: Validating login functionality with invalid credential
    Given user navigate to weborders application
    When user provides username "invalid" and password "invalid" and clicks on login
    Then user validates error message "Invalid Login or Password."