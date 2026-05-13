Feature: Mercado Libre departments API


  @api
  Scenario: Validate Mercado Libre departments service contains departments
    Given the Mercado Libre departments service is available
    When the user sends a GET request to the departments endpoint
    Then the API response status code should be 200
    And the API response should contain departments