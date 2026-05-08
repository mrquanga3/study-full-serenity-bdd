package com.example.stepdefinitions;

import com.example.pages.HomePage;
import io.cucumber.java.en.Then;

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
}
