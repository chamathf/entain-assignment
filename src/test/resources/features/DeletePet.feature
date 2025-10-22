@Delete
Feature: Delete a Pet

  Scenario: Delete an existing pet and verify removal
    Given a pet exists to delete
    When delete the pet
    Then the pet is deleted successfully