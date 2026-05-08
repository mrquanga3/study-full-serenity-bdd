package com.example.actions;

import com.example.pages.HomePage;
import net.serenitybdd.annotations.Step;
import org.junit.jupiter.api.Assertions;

public class HomeActions {

    HomePage homePage;

    @Step("Verify Dashboard page is displayed")
    public void verifyDashboardDisplayed() {
        Assertions.assertTrue(homePage.isDashboardDisplayed(),
                "Dashboard heading should be visible after successful login");
    }

    @Step("Verify username '{0}' is displayed")
    public void verifyUsernameDisplayed(String expectedUsername) {
        Assertions.assertTrue(homePage.isUsernameDisplayed(expectedUsername),
                "Expected username '" + expectedUsername + "' should be displayed, got: " + homePage.getDisplayedUsername());
    }

    @Step("Verify '{0}' menu is displayed on Dashboard")
    public void verifyMenuDisplayed(String menuName) {
        Assertions.assertTrue(homePage.isMenuDisplayed(menuName),
                "Menu '" + menuName + "' should be displayed on Dashboard");
    }
}
