@Dashboard @Menu
Feature: Verify Dashboard Menu Items

  Background:
    Given Go to login page
    When enter username "admin"
    And enter password "admin"
    Then I see Dashboard page

  @VerifyMenu
  Scenario Outline: Verify "<menu>" menu is displayed on Dashboard
    Then I see "<menu>" menu displayed
    Examples:
      | menu       |
      | Dashboard  |
      | Catalog    |
      | Sales      |
      | Customers  |
      | Marketing  |
      | Extensions |
      | System     |
      | Reports    |
