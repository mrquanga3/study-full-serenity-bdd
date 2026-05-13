package com.example.actions;

import com.example.pages.HomePage;
import net.serenitybdd.annotations.Step;

public class HomeAction {

    HomePage homePage;

    @Step("Verify Dashboard page is displayed")
    public boolean isDashboardDisplayed() {
        return homePage.isDashboardDisplayed();
    }

    @Step("Verify username '{0}' is displayed")
    public boolean isUsernameDisplayed(String username) {
        return homePage.isUsernameDisplayed(username);
    }

    @Step("Get displayed username")
    public String getDisplayedUsername() {
        return homePage.getDisplayedUsername();
    }

    @Step("Verify menu '{0}' is displayed")
    public boolean isMenuDisplayed(String menuName) {
        return homePage.isMenuDisplayed(menuName);
    }

    @Step("Verify page title is '{0}'")
    public boolean isPageTitle(String title) {
        return homePage.isPageTitle(title);
    }

    @Step("Verify dashboard heading is '{0}'")
    public boolean isDashboardTitleVisible(String heading) {
        return homePage.isDashboardTitleVisible(heading);
    }

    @Step("Verify breadcrumb contains Home and Dashboard")
    public boolean isBreadcrumbDisplayed(String... crumbs) {
        return homePage.isBreadcrumbDisplayed(crumbs);
    }

    @Step("Verify tile '{0}' is displayed")
    public boolean isTileDisplayed(String tileTitle) {
        return homePage.isTileDisplayed(tileTitle);
    }

    @Step("Verify all main dashboard panels are displayed")
    public boolean areMainDashboardPanelsDisplayed() {
        return homePage.areMainDashboardPanelsDisplayed();
    }

    @Step("Verify footer contains '{0}'")
    public boolean isFooterContains(String expectedText) {
        return homePage.isFooterContains(expectedText);
    }

    @Step("Click View more on '{0}' tile")
    public void clickViewMoreOfTile(String tileTitle) {
        homePage.clickViewMoreOfTile(tileTitle);
    }

    @Step("Verify current URL contains '{0}'")
    public boolean currentUrlContains(String routePart) {
        return homePage.currentUrlContains(routePart);
    }

    @Step("Verify Sales Analytics has Today/Week/Month/Year ranges")
    public boolean hasSalesAnalyticsRanges() {
        return homePage.hasSalesAnalyticsRanges();
    }

    @Step("Verify Sales Analytics chart is displayed")
    public boolean isSalesAnalyticsCanvasDisplayed() {
        return homePage.isSalesAnalyticsCanvasDisplayed();
    }

    @Step("Select Sales Analytics range '{0}'")
    public void clickSalesAnalyticsRange(String range) {
        homePage.clickSalesAnalyticsRange(range);
    }

    @Step("Verify Sales Analytics range '{0}' is active")
    public boolean isSalesAnalyticsRangeActive(String range) {
        return homePage.isSalesAnalyticsRangeActive(range);
    }

    @Step("Verify chart request was sent with range '{0}'")
    public boolean hasChartRequestForRange(String range) {
        return homePage.hasChartRequestForRange(range);
    }

    @Step("Verify World Map widget is displayed")
    public boolean isWorldMapDisplayed() {
        return homePage.isWorldMapDisplayed();
    }

    @Step("Verify Recent Activity shows No results message")
    public boolean isRecentActivityNoResultsDisplayed() {
        return homePage.isRecentActivityNoResultsDisplayed();
    }

    @Step("Verify Latest Orders table is displayed")
    public boolean isLatestOrdersTableDisplayed() {
        return homePage.isLatestOrdersTableDisplayed();
    }

    @Step("Verify Latest Orders table contains standard columns")
    public boolean latestOrdersTableContainsColumns(String... columns) {
        return homePage.latestOrdersTableContainsColumns(columns);
    }

    @Step("Click View button of first order in Latest Orders")
    public void clickFirstLatestOrderViewButton() {
        homePage.clickFirstLatestOrderViewButton();
    }

    @Step("Verify Order detail page is opened")
    public boolean isOrderInfoPageOpened() {
        return homePage.isOrderInfoPageOpened();
    }

    @Step("Open Developer Settings modal")
    public void openDeveloperSettingsModal() {
        homePage.openDeveloperSettingsModal();
    }

    @Step("Verify Developer Settings modal is displayed")
    public boolean isDeveloperSettingsModalDisplayed() {
        return homePage.isDeveloperSettingsModalDisplayed();
    }

    @Step("Verify Developer Settings cache rows are displayed")
    public boolean isDeveloperCacheRowsDisplayed() {
        return homePage.isDeveloperCacheRowsDisplayed();
    }

    @Step("Close Developer Settings modal")
    public void closeDeveloperSettingsModal() {
        homePage.closeDeveloperSettingsModal();
    }

    @Step("Verify Developer Settings modal is closed")
    public boolean isDeveloperSettingsModalClosed() {
        return homePage.isDeveloperSettingsModalClosed();
    }

    @Step("Click sidebar toggle button")
    public void clickSidebarToggle() {
        homePage.clickSidebarToggle();
    }

    @Step("Check if left sidebar is active")
    public boolean isSidebarActive() {
        return homePage.isSidebarActive();
    }

    @Step("Expand '{0}' menu in left sidebar")
    public void expandLeftMenu(String menuName) {
        homePage.expandLeftMenu(menuName);
    }

    @Step("Verify submenu '{0}' is expanded")
    public boolean isSubmenuExpanded(String collapseId) {
        return homePage.isSubmenuExpanded(collapseId);
    }

    @Step("Click '{0}' submenu item")
    public void clickSubmenuItem(String itemName) {
        homePage.clickSubmenuItem(itemName);
    }

    @Step("Open user dropdown menu")
    public void openUserDropdown() {
        homePage.openUserDropdown();
    }

    @Step("Verify user dropdown contains expected items")
    public boolean userDropdownContainsItems(String... itemNames) {
        return homePage.userDropdownContainsItems(itemNames);
    }

    @Step("Verify user dropdown links are not empty")
    public boolean userDropdownLinksAreNotEmpty() {
        return homePage.userDropdownLinksAreNotEmpty();
    }

    @Step("Click logout link")
    public void clickLogout() {
        homePage.clickLogout();
    }
}
