@FindByStatus
Feature: Find pets by status

  Scenario Outline: Query pets by status and validate schema
    When query pets by status "<status>"
    Then only pets with status "<status>" are returned
    And the response matches the findByStatus schema

    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |