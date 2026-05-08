@Login @LoginSuccess @smoke
Feature: Test Login Success

  Background:
    Given Go to login page

  @ValidCredentials
  Scenario: Login with valid credentials redirects to Dashboard
    When enter username "admin"
    And enter password "admin"
    Then I see Dashboard page

  @VerifyUsername
  Scenario Outline: Verify username is displayed on Home page after login
    When enter username "<username>"
    And enter password "<password>"
    Then I see Dashboard page
    And I see username "<username>" displayed
    Examples:
      | username | password |
      | admin    | admin    |
