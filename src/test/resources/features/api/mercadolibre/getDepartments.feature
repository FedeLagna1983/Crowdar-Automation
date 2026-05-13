Feature: Mercado Libre Departments API

  Background:
    * url mercadoLibreBaseUrl
    * configure headers =
    """
    {
      "User-Agent": "Mozilla/5.0",
      "Accept": "application/json",
      "Content-Type": "application/json"
    }
    """

  @api
  @mercadolibre
  @departments
  Scenario: Validate GET departments response
    Given path 'menu', 'departments'
    When method get
    Then status 200
    And match response != null
    And match response.departments != []
