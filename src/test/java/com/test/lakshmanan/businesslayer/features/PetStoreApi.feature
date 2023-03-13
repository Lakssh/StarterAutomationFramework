Feature: Search Product

  @Regression @Api
  Scenario Outline: Create Pet, Update Pet, get and verify details, and delete pet
    Given I hit the pet store api
    # post method
    When I add a new pet with name <name> and status <status>
    Then I validate the response code 200
    And I capture the newly created id
    # get method
    And I get and validate the updated pet details and response code 200
    # put method
    When I update the name <updatedName> and status <updatedStatus>
    Then I validate the response code 200
    # delete method
    And I delete the pet and validate the response code 200
    And I get and validate response code 404

    Examples:
      |name   | status    | updatedName   | updatedStatus |
      |doggie | available | Fish          | unavailable   |
