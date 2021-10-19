@regression @smoke @MB2P-123
Feature: Validating pizza application functionalities

  Scenario Outline: Validating Place Order functionality
    Given user navigate to pizza application
    When user creates pizza order with data
      | Pizza        | <Pizza>        |
      | Toppings 1   | <Toppings 1>   |
      | Toppings 2   | <Toppings 2>   |
      | Quantity     | <Quantity>     |
      | Name         | <Name>         |
      | Email        | <Email>        |
      | Phone        | <Phone>        |
      | Payment Type | <Payment Type> |
    Then user validates that order is created with success message "Thank you for your order! TOTAL: " for "<Quantity>" "<Pizza>"
    Examples:
      | Pizza                        | Toppings 1 | Toppings 2   | Quantity | Name     | Email             | Phone      | Payment Type   |
      | Small 6 Slices - no toppings | Mushrooms  | Extra cheese | 1        | Jhon Doe | jhondoe@gmail.com | 7731592511 | Cash on Pickup |
      | Large 10 Slices - 2 toppings | Caramelized onions  | Extra cheese | 3        | Adrian Torres | atorres@gmail.com | 7731594511 | Credit Card |
      | Medium 8 Slices - 2 toppings | Olives  | Diced Mango | 5        | Adrian Torres | atorres@gmail.com | 7731594511 | Credit Card |
      | Small 6 Slices - no toppings | Salami  | Salami | 1        | Jhon Doe | jhondoe@gmail.com | 7731592511 | Cash on Pickup |
      | Large 10 Slices - no toppings | Mushrooms  | Extra cheese | 8        | Adrian Torres | atorres@gmail.com | 7731594511 | Credit Card |
      | Small 6 Slices - 1 topping | Parmesan cheese  | Diced Mango | 10        | Adrian Torres | atorres@gmail.com | 7731594511 | Credit Card |




