@ErrorHandling
Feature: Error handling for Scenarios

  Scenario: Fetching a non-existing pet returns 404
    Then fetching pet id 999999999999 should return 404

  Scenario: Deleting a non-existing pet returns 404
    Then deleting pet id 999999999999 should return 404
