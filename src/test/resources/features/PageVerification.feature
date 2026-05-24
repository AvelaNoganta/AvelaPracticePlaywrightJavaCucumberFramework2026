Feature: Page Title Verification

  Scenario: Verify application page title
    Given user navigates to the application URL
    Then the page title should be "Ndosi Test Automation"

  Scenario: Verify application is accessible
    Given user navigates to the application URL
    Then user waits for 1 seconds
