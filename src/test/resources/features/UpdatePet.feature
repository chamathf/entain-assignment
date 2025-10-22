Feature: Update And Verify Pet Details

  @Update
  Scenario: Update an existing pet and verify details
    Given an existing pet
    When  change its name to "renamed-fluffy" and status to "sold"
    Then verify the update is persisted
