@api @MB2P-202 @regression @smoke
  Feature: Validating Bank rest API calls
    Scenario: Validating account api cals
      Given user creates account with api call with data
      |accountType|Checking|
      |balance    |100000  |
      Then user validate 201 status code
      And user validate that account is created with api get call
      When user updates account with api put call with data
        |accountType|Saving|
        |balance    |100500  |
      Then user validate 200 status code
      And user validates that account is updated with api get call
      When user deletes account with api call
      Then user validate 204 status code
      And user validates that account deleted with api call

