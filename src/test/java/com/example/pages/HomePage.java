package com.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomePage extends PageObject {

    @FindBy(css = "#nav-profile, #header .dropdown a.dropdown-toggle")
    private WebElementFacade userProfileLink;

    @FindBy(css = ".page-header h1")
    private WebElementFacade dashboardHeading;

    @FindBy(id = "button-menu")
    private WebElementFacade sidebarToggleButton;

    @FindBy(id = "column-left")
    private WebElementFacade leftSidebar;

    @FindBy(css = "a[href*='route=common/logout']")
    private WebElementFacade logoutLink;

    public boolean isDashboardDisplayed() {
        return dashboardHeading.waitUntilVisible().isDisplayed();
    }

    public String getDisplayedUsername() {
        return userProfileLink.waitUntilVisible().getText().trim();
    }

    public boolean isUsernameDisplayed(String expectedUsername) {
        String actual = getDisplayedUsername();
        return actual.toLowerCase().contains(expectedUsername.toLowerCase());
    }

    public boolean isMenuDisplayed(String menuName) {
        String xpath = "//nav//a[normalize-space()='" + menuName + "']"
                + " | //nav//span[normalize-space()='" + menuName + "']";
        return find(By.xpath(xpath)).waitUntilVisible().isDisplayed();
    }

    public boolean isDashboardTitleVisible(String expectedTitle) {
        return dashboardHeading.waitUntilVisible().getText().trim().equalsIgnoreCase(expectedTitle);
    }

    public boolean isBreadcrumbDisplayed(String... crumbs) {
        List<WebElementFacade> breadcrumbItems = findAll(By.cssSelector(".breadcrumb li"));
        List<String> actual = new ArrayList<>();
        for (WebElementFacade item : breadcrumbItems) {
            String text = item.getText().trim();
            if (!text.isEmpty()) {
                actual.add(text.toLowerCase(Locale.ROOT));
            }
        }

        for (String crumb : crumbs) {
            if (!actual.contains(crumb.toLowerCase(Locale.ROOT))) {
                return false;
            }
        }
        return true;
    }

    public boolean isTileDisplayed(String tileTitle) {
        for (WebElementFacade tile : findAll(By.cssSelector("div.tile"))) {
            if (tile.getText().toLowerCase(Locale.ROOT).contains(tileTitle.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    public void clickViewMoreOfTile(String tileTitle) {
        for (WebElementFacade tile : findAll(By.cssSelector("div.tile"))) {
            if (tile.getText().toLowerCase(Locale.ROOT).contains(tileTitle.toLowerCase(Locale.ROOT))) {
                tile.find(By.xpath(".//a[contains(normalize-space(),'View more')]")).waitUntilClickable().click();
                return;
            }
        }
        throw new IllegalStateException("Cannot find tile: " + tileTitle);
    }

    public boolean currentUrlContains(String routePart) {
        return getDriver().getCurrentUrl().contains(routePart);
    }

    public boolean isPageTitle(String expectedTitle) {
        return getDriver().getTitle().trim().equalsIgnoreCase(expectedTitle);
    }

    public boolean areMainDashboardPanelsDisplayed() {
        return isPanelDisplayed("World Map")
                && isPanelDisplayed("Sales Analytics")
                && isPanelDisplayed("Recent Activity")
                && isPanelDisplayed("Latest Orders");
    }

    public boolean isFooterContains(String expectedText) {
        String footer = find(By.cssSelector("footer")).waitUntilVisible().getText().trim();
        return footer.contains(expectedText);
    }

    public void clickSalesAnalyticsRange(String range) {
        if (!elementExists(By.cssSelector("#range a[href='" + range + "'], a[href='" + range + "']"))) {
            throw new IllegalStateException("Sales Analytics range option not found: " + range);
        }

        if (elementExists(By.id("button-setting"))) {
            WebElementFacade settingButton = find(By.id("button-setting"));
            if (settingButton.isVisible()) {
                try {
                    settingButton.waitUntilClickable().click();
                } catch (Exception ex) {
                    evaluateJavascript("arguments[0].click();", settingButton);
                }
            }
        }

        Object clicked = evaluateJavascript(
                "var selector = \"#range a[href='\" + arguments[0] + \"'], a[href='\" + arguments[0] + \"']\";"
                        + "var link = document.querySelector(selector);"
                        + "if (!link) return false;"
                        + "link.click();"
                        + "return true;",
                range
        );

        if (!Boolean.TRUE.equals(clicked)) {
            throw new IllegalStateException("Cannot click Sales Analytics range: " + range);
        }

        waitABit(1000);
    }

    public boolean isSalesAnalyticsRangeActive(String range) {
        Object active = evaluateJavascript(
                "return Array.from(document.querySelectorAll(\"#range a[href='\" + arguments[0] + \"'], a[href='\" + arguments[0] + \"']\"))"
                        + ".some(a => a.classList.contains('active'));",
                range
        );
        return Boolean.TRUE.equals(active);
    }

    public boolean hasChartRequestForRange(String range) {
        Object result = evaluateJavascript(
                "return window.performance.getEntriesByType('resource')"
                        + ".some(e => e.name.includes('route=extension/opencart/dashboard/chart.chart')"
                        + " && e.name.includes('range=' + arguments[0]));",
                range
        );
        return Boolean.TRUE.equals(result);
    }

    public boolean hasSalesAnalyticsRanges() {
        return elementExists(By.cssSelector("#range a[href='day'], a[href='day']"))
                && elementExists(By.cssSelector("#range a[href='week'], a[href='week']"))
                && elementExists(By.cssSelector("#range a[href='month'], a[href='month']"))
                && elementExists(By.cssSelector("#range a[href='year'], a[href='year']"));
    }

    public boolean isSalesAnalyticsCanvasDisplayed() {
        return find(By.cssSelector("#chart-sale")).waitUntilVisible().isDisplayed();
    }

    public boolean isWorldMapDisplayed() {
        WebElementFacade map = find(By.id("vmap")).waitUntilVisible();
        Object hasSvg = evaluateJavascript("return arguments[0].querySelector('svg') !== null;", map);
        return map.isDisplayed() && Boolean.TRUE.equals(hasSvg);
    }

    public boolean isRecentActivityNoResultsDisplayed() {
        WebElementFacade panel = find(By.xpath("//div[contains(@class,'card')][.//div[contains(@class,'card-header') and contains(.,'Recent Activity')]]"));
        return panel.find(By.xpath(".//li[contains(@class,'list-group-item') and contains(normalize-space(),'No results!')]"))
                .waitUntilVisible().isDisplayed();
    }

    public boolean isLatestOrdersTableDisplayed() {
        return find(By.xpath("//div[contains(@class,'card')][.//div[contains(@class,'card-header') and contains(.,'Latest Orders')]]//table"))
                .waitUntilVisible().isDisplayed();
    }

    public boolean latestOrdersTableContainsColumns(String... columns) {
        WebElementFacade table = find(By.xpath("//div[contains(@class,'card')][.//div[contains(@class,'card-header') and contains(.,'Latest Orders')]]//table"));
        String headerText = table.find(By.tagName("thead")).getText().toLowerCase(Locale.ROOT);

        for (String column : columns) {
            if (!headerText.contains(column.toLowerCase(Locale.ROOT))) {
                return false;
            }
        }
        return true;
    }

    public void clickFirstLatestOrderViewButton() {
        WebElementFacade button = find(By.xpath(
                "//div[contains(@class,'card')][.//div[contains(@class,'card-header') and contains(.,'Latest Orders')]]"
                        + "//tbody/tr[1]//a[@title='View']"
        ));
        WebElement raw = button.waitUntilVisible();
        evaluateJavascript("arguments[0].scrollIntoView({block:'center'});", raw);

        try {
            button.waitUntilClickable().click();
        } catch (ElementClickInterceptedException ex) {
            evaluateJavascript("arguments[0].click();", raw);
        }
    }

    public boolean isOrderInfoPageOpened() {
        String url = getDriver().getCurrentUrl();
        return url.contains("route=sale/order.info") && url.contains("order_id=");
    }

    public void openDeveloperSettingsModal() {
        find(By.cssSelector("button[title='Developer Setting']")).waitUntilClickable().click();
    }

    public boolean isDeveloperSettingsModalDisplayed() {
        return find(By.xpath("//div[contains(@class,'modal')]//h5[contains(normalize-space(),'Developer Settings')]"))
                .waitUntilVisible().isDisplayed();
    }

    public boolean isDeveloperCacheRowsDisplayed() {
        WebElementFacade modal = find(By.xpath("//div[contains(@class,'modal') and .//h5[contains(.,'Developer Settings')]]"));
        String text = modal.getText().toLowerCase(Locale.ROOT);
        return text.contains("theme") && text.contains("sass");
    }

    public void closeDeveloperSettingsModal() {
        find(By.cssSelector(".modal.show .btn-close")).waitUntilClickable().click();
    }

    public boolean isDeveloperSettingsModalClosed() {
        return findAll(By.cssSelector(".modal.show")).isEmpty();
    }

    public boolean isSidebarActive() {
        return leftSidebar.waitUntilVisible().getAttribute("class").contains("active");
    }

    public void clickSidebarToggle() {
        if (elementExists(By.id("button-menu")) && !find(By.id("button-menu")).isVisible()) {
            getDriver().manage().window().setSize(new Dimension(900, 900));
            waitABit(400);
        }

        WebElementFacade button = find(By.id("button-menu")).waitUntilVisible();
        try {
            button.waitUntilClickable().click();
        } catch (Exception ex) {
            evaluateJavascript("arguments[0].click();", button);
        }
        waitABit(300);
    }

    public void expandLeftMenu(String menuName) {
        find(By.xpath("//ul[@id='menu']//a[contains(@class,'parent') and contains(normalize-space(),'" + menuName + "')]"))
                .waitUntilClickable().click();
        waitABit(500);
    }

    public boolean isSubmenuExpanded(String collapseId) {
        return find(By.id(collapseId)).getAttribute("class").contains("show");
    }

    public void clickSubmenuItem(String itemName) {
        find(By.xpath("//ul[@id='menu']//a[normalize-space()='" + itemName + "']")).waitUntilClickable().click();
    }

    public void openUserDropdown() {
        WebElementFacade userToggle = find(By.xpath(
                "//header//a[contains(@class,'dropdown-toggle') and contains(.,'(admin)')]"
        )).waitUntilVisible();

        try {
            userToggle.waitUntilClickable().click();
        } catch (Exception ex) {
            evaluateJavascript("arguments[0].click();", userToggle);
        }
    }

    public boolean userDropdownContainsItems(String... itemNames) {
        WebElementFacade menu = find(By.xpath(
                "//header//a[contains(@class,'dropdown-toggle') and contains(.,'(admin)')]/following-sibling::ul[contains(@class,'dropdown-menu')]"
        ));
        String menuText = menu.waitUntilVisible().getText().toLowerCase(Locale.ROOT);

        for (String itemName : itemNames) {
            if (!menuText.contains(itemName.toLowerCase(Locale.ROOT))) {
                return false;
            }
        }
        return true;
    }

    public boolean userDropdownLinksAreNotEmpty() {
        List<WebElementFacade> links = findAll(By.xpath(
                "//header//a[contains(@class,'dropdown-toggle') and contains(.,'(admin)')]/following-sibling::ul[contains(@class,'dropdown-menu')]//a"
        ));
        if (links.isEmpty()) {
            return false;
        }

        for (WebElementFacade link : links) {
            String href = link.getAttribute("href");
            if (href == null || href.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void clickLogout() {
        logoutLink.waitUntilClickable().click();
    }

    private boolean isPanelDisplayed(String panelTitle) {
        return find(By.xpath("//div[contains(@class,'card-header') and contains(normalize-space(),'" + panelTitle + "')]"))
                .waitUntilVisible().isDisplayed();
    }

    private boolean elementExists(By locator) {
        return !getDriver().findElements(locator).isEmpty();
    }
}
