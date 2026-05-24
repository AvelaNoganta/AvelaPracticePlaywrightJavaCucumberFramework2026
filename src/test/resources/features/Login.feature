Feature: Login Functionality

  Background:
    Given user is on the login page

  Scenario: User should be able to login with valid credentials
    When user logs in with valid credentials
    Then user should be logged in successfully

  Scenario: User should be able to login with admin credentials
    When user logs in with admin credentials
    Then user should be logged in successfully

  Scenario Outline: Login with different credentials
    When user enters email "<email>" and password "<password>"
    Then user should be logged in successfully

    Examples:
      | email                  | password        |
      | aveetestuser@gmail.com | Testing123456!  |
      | admin@gmail.com        | @12345678!      |
