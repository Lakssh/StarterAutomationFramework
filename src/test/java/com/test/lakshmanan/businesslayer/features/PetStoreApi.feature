Feature: Pet Store API Tests

  @Regression @Api
  Scenario Outline: CRUD operations with Pet store APIs
    Given I hit the pet store api
    When I add a new pet with name <name> and status <status>
    Then I validate the response code 200
    And I capture the newly created id
    And I get and validate the updated pet details and response code 200
    When I update the name <updatedName> and status <updatedStatus>
    Then I validate the response code 200
    And I delete the pet and validate the response code 200
    And I get and validate response code 404

    Examples:
      |name   | status    | updatedName   | updatedStatus |
      |doggie | available | Fish          | unavailable   |
