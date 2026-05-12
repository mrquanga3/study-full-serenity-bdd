@Dashboard @HomePage
Feature: Verify HomePage (Dashboard) functions

  Background:
    Given Go to login page
    When I login with user "admin" and password "admin"
    Then I see Dashboard page

  @AUTO-1 @REGRESSION @SMOKE
  Scenario: AUTO-1 - Open dashboard successfully after login
    Then I am redirected to route containing "route=common/dashboard"
    And Dashboard page title is "Dashboard"
    And Dashboard heading is "Dashboard"

  @AUTO-2 @REGRESSION
  Scenario: AUTO-2 - Verify main Dashboard components
    Then I see breadcrumb Home and Dashboard
    And I see all main dashboard widgets

  @VerifyMenu @REGRESSION
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

  @AUTO-3 @AUTO-4 @AUTO-5 @AUTO-6 @REGRESSION @SMOKE
  Scenario Outline: AUTO-3/4/5/6 - KPI View more navigation
    When I click View more on "<tile>" tile
    Then I am redirected to route containing "<route>"
    And page title is "<title>"

    Examples:
      | tile            | route                    | title     |
      | TOTAL ORDERS    | route=sale/order         | Orders    |
      | TOTAL SALES     | route=sale/order         | Orders    |
      | TOTAL CUSTOMERS | route=customer/customer  | Customers |
      | PEOPLE ONLINE   | route=report/online      | Online Report |

  @AUTO-7 @REGRESSION
  Scenario: AUTO-7 - Switch Sales Analytics range to Week
    When I select Sales Analytics range "week"
    Then Sales Analytics range "week" is active
    And dashboard chart request is sent with range "week"

  @AUTO-8 @REGRESSION
  Scenario: AUTO-8 - Verify Sales Analytics range options
    Then Sales Analytics has ranges Today Week Month Year
    And Sales Analytics chart is displayed

  @AUTO-9 @REGRESSION
  Scenario: AUTO-9 - Verify World Map widget
    Then World Map widget is displayed

  @AUTO-10 @REGRESSION
  Scenario: AUTO-10 - Verify Recent Activity empty state
    Then Recent Activity shows No results message

  @AUTO-11 @REGRESSION @SMOKE
  Scenario: AUTO-11 - Open first order from Latest Orders
    Then Latest Orders table is displayed with standard columns
    When I open first order from Latest Orders
    Then Order detail page is opened

  @AUTO-12 @REGRESSION
  Scenario: AUTO-12 - Open and close Developer Settings
    When I open Developer Settings from dashboard
    Then Developer Settings modal is shown
    When I close Developer Settings modal
    Then Developer Settings modal is closed

  @AUTO-13 @REGRESSION
  Scenario: AUTO-13 - Toggle left sidebar
    When I toggle left sidebar menu
    Then left sidebar active state is toggled

  @AUTO-14 @REGRESSION @SMOKE
  Scenario: AUTO-14 - Expand Catalog and open Categories
    When I expand "Catalog" menu in left sidebar
    Then submenu "collapse-1" is expanded
    When I click "Categories" submenu item
    Then I am redirected to route containing "route=catalog/category"
    And page title is "Categories"

  @AUTO-15 @REGRESSION
  Scenario: AUTO-15 - Verify user dropdown shortcuts
    When I open user dropdown menu
    Then user dropdown shows profile shortcuts

  @AUTO-16 @REGRESSION @SMOKE
  Scenario: AUTO-16 - Logout from dashboard
    When I logout from dashboard
    Then I am redirected to login page
    And page title is "Administration"
