@regression @ui @MB2P-120 @smoke
Feature: Validating calculate and order creation functionality

  Scenario Outline: Validating calculate functionality
    Given user navigate to weborders application
    When user provides username "Tester" and password "test" and clicks on login
    And user clicks on Order module
    And user selects "<Product>" product with <Quantity> quantity
    Then user validates total is calculated for quantity <Quantity>

    Examples:
      | Product     | Quantity |
      | MyMoney     | 1        |
      | FamilyAlbum | 2        |
      | ScreenSaver | 5        |
      | MyMoney     | 25        |
      | FamilyAlbum | 100       |
      | FamilyAlbum | 4656       |
      | ScreenSaver | 330000        |
    @MB2P-121
    Scenario Outline: Validating create order functionality
      Given user navigate to weborders application
      When user provides username "Tester" and password "test" and clicks on login
      And user counts number of orders in table
      And user clicks on Order module
      And user creates order with data
        | order   | quantity   | name   | address   | city   | state   | zip   | cc   | expire date   |
        | <order> | <quantity> | <name> | <address> | <city> | <state> | <zip> | <cc> | <expire date> |
      Then user validates success message "New order has been successfully added."
      And user validates order added to List Of Orders
      Examples:
        | order       | quantity | name      | address      | city     | state    | zip   | cc               | expire date |
        | MyMoney     | 1        | Jet Li    | 4343 n Neva  | Chicago  | Illinois | 60706 | 4598632578415522 | 12/22       |
        | FamilyAlbum | 5        | Bob Dylan | 1022 Western | New York | New York | 10701 | 4598456778941234 | 10/22       |


