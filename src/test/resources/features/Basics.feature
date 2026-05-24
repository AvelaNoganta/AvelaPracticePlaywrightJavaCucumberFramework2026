Feature: Basic Application Tests

  Scenario: Verify page title
    Given user navigates to the application URL
    Then the page title should be "Ndosi Test Automation"

  Scenario: Verify page loads successfully
    Given user navigates to the application URL
    Then user waits for 2 seconds
