Feature: Add products to cart functionality


  @cart
  Scenario: Add product from product detail page and validate it in cart
    Given the user opens the SauceDemo login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    And the user opens the first product detail from Inventory
    And the user adds the product to the cart from Inventory Item page
    And the user opens the cart page
    Then the selected product should be displayed correctly in the cart

  @cart
  Scenario: Remove product from cart and validate cart is empty
    Given the user opens the SauceDemo login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    And the user opens the first product detail from Inventory
    And the user adds the product to the cart from Inventory Item page
    And the user opens the cart page
    When the user removes the product from the cart
    Then the cart should be empty
