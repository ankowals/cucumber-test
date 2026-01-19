@get-pastry-details
Feature: Get pastry details

  Scenario: Client checks pastry availability
    Given I can access pastry service
    When I ask about availability of pastry "Millefeuille"
    Then I should be told it is available