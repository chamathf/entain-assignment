Feature: Create and Verify Pet

  @Create
  Scenario: Create a new pet and verify details
    Given Create a new pet with random details
    When Retrieve the created pet by ID
    Then The pet details should match the created data