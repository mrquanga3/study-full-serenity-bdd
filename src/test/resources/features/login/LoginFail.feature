@Login @LoginFail
Feature: Test Login Fail

  Background:
    Given Go to login page

  @InvalidUsernameAndPassword
  Scenario: Test Login with invalid username and password
    When enter username "invaliduser"
    And enter password "invalidpass"
    Then I see error message is displayed

#  @emptyUsername @MissingField
#  Scenario: Test Login with empty username
#    When enter ""
#    And enter "passs"
#    Then I see required field warning is displayed
#
#  @emptyUsernameAndPassWord @MissingField
#  Scenario: Test Login with empty username and password
#    When enter ""
#    And enter ""
#    Then I see required field warning is displayed
  @MissingField
  Scenario Outline: Test Login with empty fields
    When enter username "<username>"
    And enter password "<password>"
    Then I see required field warning is displayed
    Examples:
      | username | password |
      |          |          |
      |          | passs    |