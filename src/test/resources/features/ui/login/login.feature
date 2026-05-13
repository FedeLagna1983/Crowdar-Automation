Feature: Login functionality


  @login @smoke
  Scenario: Successful login with valid credentials
    Given the user opens the SauceDemo login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the Inventory page should be displayed

  @login @regression
  Scenario: Login with valid username and invalid password
    Given the user opens the SauceDemo login page
    When the user logs in with username "standard_user" and password "wrong_password"
    Then the login error message should be displayed


  @login @regression
  @intentionalFail
  Scenario: Intentional failure to validate screenshot and report generation
    Given the user opens the SauceDemo login page
    When the user logs in with username "standard_user" and password "wrong_password"
    Then the login error message should fail intentionally
