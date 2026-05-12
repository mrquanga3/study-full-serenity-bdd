package com.example.stepdefinitions;

import com.example.pages.HomePage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeSteps {

    HomePage homePage;

    @Then("I see Dashboard page")
    public void iSeeDashboardPage() {
        assertTrue(homePage.isDashboardDisplayed(),
                "Dashboard heading should be visible after successful login");
    }

    @Then("I see username {string} displayed")
    public void iSeeUsernameDisplayed(String username) {
        assertTrue(homePage.isUsernameDisplayed(username),
                "Expected username '" + username + "' should be displayed on Home page, got: " + homePage.getDisplayedUsername());
    }

    @Then("I see {string} menu displayed")
    public void iSeeMenuDisplayed(String menuName) {
        assertTrue(homePage.isMenuDisplayed(menuName),
                "Menu '" + menuName + "' should be displayed on Dashboard");
    }

    @Then("Dashboard page title is {string}")
    public void dashboardPageTitleIs(String title) {
        assertTrue(homePage.isPageTitle(title),
                "Expected page title to be '" + title + "'");
    }

    @Then("page title is {string}")
    public void pageTitleIs(String title) {
        assertTrue(homePage.isPageTitle(title),
                "Expected page title to be '" + title + "'");
    }

    @Then("Dashboard heading is {string}")
    public void dashboardHeadingIs(String heading) {
        assertTrue(homePage.isDashboardTitleVisible(heading),
                "Expected dashboard heading to be '" + heading + "'");
    }

    @Then("I see breadcrumb Home and Dashboard")
    public void iSeeBreadcrumbHomeAndDashboard() {
        assertTrue(homePage.isBreadcrumbDisplayed("Home", "Dashboard"),
                "Expected breadcrumb to contain Home and Dashboard");
    }

    @Then("I see all main dashboard widgets")
    public void iSeeAllMainDashboardWidgets() {
        assertTrue(homePage.isTileDisplayed("TOTAL ORDERS"), "TOTAL ORDERS tile should be visible");
        assertTrue(homePage.isTileDisplayed("TOTAL SALES"), "TOTAL SALES tile should be visible");
        assertTrue(homePage.isTileDisplayed("TOTAL CUSTOMERS"), "TOTAL CUSTOMERS tile should be visible");
        assertTrue(homePage.isTileDisplayed("PEOPLE ONLINE"), "PEOPLE ONLINE tile should be visible");
        assertTrue(homePage.areMainDashboardPanelsDisplayed(), "All main dashboard panels should be visible");
        assertTrue(homePage.isFooterContains("Version 4.0.2.3"), "Footer should contain OpenCart version");
    }

    @When("I click View more on {string} tile")
    public void iClickViewMoreOnTile(String tileTitle) {
        homePage.clickViewMoreOfTile(tileTitle);
    }

    @Then("I am redirected to route containing {string}")
    public void iAmRedirectedToRouteContaining(String route) {
        assertTrue(homePage.currentUrlContains(route),
                "Expected URL to contain route: " + route);
    }

    @Then("Sales Analytics has ranges Today Week Month Year")
    public void salesAnalyticsHasRangesTodayWeekMonthYear() {
        assertTrue(homePage.hasSalesAnalyticsRanges(),
                "Sales Analytics should contain Today/Week/Month/Year ranges");
    }

    @Then("Sales Analytics chart is displayed")
    public void salesAnalyticsChartIsDisplayed() {
        assertTrue(homePage.isSalesAnalyticsCanvasDisplayed(),
                "Sales Analytics chart should be visible");
    }

    @When("I select Sales Analytics range {string}")
    public void iSelectSalesAnalyticsRange(String range) {
        homePage.clickSalesAnalyticsRange(range.toLowerCase());
    }

    @Then("Sales Analytics range {string} is active")
    public void salesAnalyticsRangeIsActive(String range) {
        assertTrue(homePage.isSalesAnalyticsRangeActive(range.toLowerCase()),
                "Expected range " + range + " to be active");
    }

    @Then("dashboard chart request is sent with range {string}")
    public void dashboardChartRequestIsSentWithRange(String range) {
        assertTrue(homePage.hasChartRequestForRange(range.toLowerCase()),
                "Expected chart request with range=" + range);
    }

    @Then("World Map widget is displayed")
    public void worldMapWidgetIsDisplayed() {
        assertTrue(homePage.isWorldMapDisplayed(), "World Map widget should be displayed with SVG map");
    }

    @Then("Recent Activity shows No results message")
    public void recentActivityShowsNoResultsMessage() {
        assertTrue(homePage.isRecentActivityNoResultsDisplayed(),
                "Recent Activity should show No results! when empty");
    }

    @Then("Latest Orders table is displayed with standard columns")
    public void latestOrdersTableIsDisplayedWithStandardColumns() {
        assertTrue(homePage.isLatestOrdersTableDisplayed(), "Latest Orders table should be displayed");
        assertTrue(homePage.latestOrdersTableContainsColumns(
                        "Order ID",
                        "Customer",
                        "Status",
                        "Date Added",
                        "Total",
                        "Action"),
                "Latest Orders table should contain standard columns");
    }

    @When("I open first order from Latest Orders")
    public void iOpenFirstOrderFromLatestOrders() {
        homePage.clickFirstLatestOrderViewButton();
    }

    @Then("Order detail page is opened")
    public void orderDetailPageIsOpened() {
        assertTrue(homePage.isOrderInfoPageOpened(),
                "Order detail page should be opened with order_id");
    }

    @When("I open Developer Settings from dashboard")
    public void iOpenDeveloperSettingsFromDashboard() {
        homePage.openDeveloperSettingsModal();
    }

    @Then("Developer Settings modal is shown")
    public void developerSettingsModalIsShown() {
        assertTrue(homePage.isDeveloperSettingsModalDisplayed(),
                "Developer Settings modal should be visible");
        assertTrue(homePage.isDeveloperCacheRowsDisplayed(),
                "Developer Settings modal should contain Theme and SASS cache rows");
    }

    @When("I close Developer Settings modal")
    public void iCloseDeveloperSettingsModal() {
        homePage.closeDeveloperSettingsModal();
    }

    @Then("Developer Settings modal is closed")
    public void developerSettingsModalIsClosed() {
        assertTrue(homePage.isDeveloperSettingsModalClosed(),
                "Developer Settings modal should be closed");
    }

    @When("I toggle left sidebar menu")
    public void iToggleLeftSidebarMenu() {
        homePage.clickSidebarToggle();
    }

    @Then("left sidebar active state is toggled")
    public void leftSidebarActiveStateIsToggled() {
        boolean firstState = homePage.isSidebarActive();
        homePage.clickSidebarToggle();
        boolean secondState = homePage.isSidebarActive();
        assertFalse(firstState == secondState,
                "Left sidebar active state should change after toggling");
    }

    @When("I expand {string} menu in left sidebar")
    public void iExpandMenuInLeftSidebar(String menuName) {
        homePage.expandLeftMenu(menuName);
    }

    @Then("submenu {string} is expanded")
    public void submenuIsExpanded(String submenuId) {
        assertTrue(homePage.isSubmenuExpanded(submenuId),
                "Expected submenu " + submenuId + " to be expanded");
    }

    @When("I click {string} submenu item")
    public void iClickSubmenuItem(String submenuItem) {
        homePage.clickSubmenuItem(submenuItem);
    }

    @When("I open user dropdown menu")
    public void iOpenUserDropdownMenu() {
        homePage.openUserDropdown();
    }

    @Then("user dropdown shows profile shortcuts")
    public void userDropdownShowsProfileShortcuts() {
        assertTrue(homePage.userDropdownContainsItems(
                        "Your Profile",
                        "Your Store",
                        "OpenCart Homepage",
                        "Documentation",
                        "Support Forum"),
                "User dropdown should contain expected shortcut items");
        assertTrue(homePage.userDropdownLinksAreNotEmpty(),
                "User dropdown links should have non-empty href");
    }

    @When("I logout from dashboard")
    public void iLogoutFromDashboard() {
        homePage.clickLogout();
    }

    @Then("I am redirected to login page")
    public void iAmRedirectedToLoginPage() {
        assertTrue(homePage.currentUrlContains("route=common/login"),
                "Expected URL to contain login route after logout");
    }
}
