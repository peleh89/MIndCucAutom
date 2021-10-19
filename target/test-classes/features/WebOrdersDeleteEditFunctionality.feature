@regression @ui @homework_01
Feature: Validating delete/edit functionalities in WebOrders application

  Background: Repeated first steps in each scenario.
   Given user navigates to WebOrdersapplication
  When user logins in with username "Tester" and password "test"


  Scenario: Validating delete selected order functionality in View All Orders
    #Given user navigate to weborders application
    #When user provides username "Tester" and password "test" and clicks on login
    And user selects any order from View All Orders
    And user deletes selected order
    Then user validates that order is deleted

    Scenario: Validating edit order functionality in View All Orders
     # Given user navigate to weborders application
     # When user provides username "Tester" and password "test" and clicks on login
      And user selects any order from View All Orders
      And user clicks on edit button and updates customer name
      Then user validates that customer name is updated in View All Orders pge
