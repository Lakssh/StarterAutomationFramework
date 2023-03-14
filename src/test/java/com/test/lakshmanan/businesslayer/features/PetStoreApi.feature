Feature: Pet store API Tests

  @Regression @Api
  Scenario Outline: CRUD operations with Pet store APIs
    Given I hit the pet store api
    When I add a new pet with name <name> and status <status>
    Then I capture the newly created pet details and validate the response
    And I query using get operation and validate the pet details
    And I update the name <updatedName> and status <updatedStatus>
    Then I validate the updated response details
    And I delete the pet and validate the response

    Examples:
      |name   | status    | updatedName   | updatedStatus |
      |doggie | available | Fish          | unavailable   |
