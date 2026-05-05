@Login @LoginFail
Feature: Test Login Fail

  @InvalidUsernameAndPassword
  Scenario: Test Login with invalid username and password
    Given Go to login page
    When enter "invaliduser"
    And enter "invalidpass"
    Then I see error message is displayed

  @emptyUsername @MissingField
  Scenario: Test Login with empty username
    Given Go to login page
    When enter ""
    And enter "passs"
    Then I see required field warning is displayed

  @emptyUsernameAndPassWord @MissingField
  Scenario: Test Login with empty username and password
    Given Go to login page
    When enter ""
    And enter ""
    Then I see required field warning is displayed