@regression @ui
Feature: Validating Etsy search and filter functionalities

  Background: Repeated first steps in each scenario.
    Given user navigate to Etsy application
    When user searches for "carpet"


  Scenario: Validating price range filter functionality for searched item
    And user applies price filter over 1000
    Then user validates that item prices over 1000
    @smoke
    Scenario: Validating search results
      Then user validates search result items contains keyword "carpet"