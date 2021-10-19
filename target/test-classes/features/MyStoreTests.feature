@regression @ui @MB2P-218
Feature: Validating My address update functionality
  Scenario: Validating update address functionality with valid address
    Given user navigate to My Store App
    When user clicks on Sign In button
    And user provides email address "mindtek123@gmail.com" and password "mindtek1234" and click on sign in button
    And user clicks on My Addresses button and update button
    And user provides new street name and number " Park ave" and click on save button
    Then user validates that address is updated
