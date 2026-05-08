package com.example.pages;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    @FindBy(id = "nav-profile")
    private WebElementFacade userProfileLink;

    @FindBy(css = "#header h1, .page-header h1")
    private WebElementFacade dashboardHeading;

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

    @Step("Verify Dashboard page is displayed")
    public void verifyDashboardDisplayed() {
        Assertions.assertTrue(isDashboardDisplayed(),
                "Dashboard heading should be visible after successful login");
    }

    @Step("Verify username '{0}' is displayed")
    public void verifyUsernameDisplayed(String expectedUsername) {
        Assertions.assertTrue(isUsernameDisplayed(expectedUsername),
                "Expected username '" + expectedUsername + "' should be displayed, got: " + getDisplayedUsername());
    }

    @Step("Verify '{0}' menu is displayed on Dashboard")
    public void verifyMenuDisplayed(String menuName) {
        String xpath = "//nav//a[normalize-space()='" + menuName + "']"
                + " | //nav//span[normalize-space()='" + menuName + "']";
        find(By.xpath(xpath)).shouldBeVisible();
    }
}
