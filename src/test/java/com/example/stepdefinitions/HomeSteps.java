package com.example.stepdefinitions;

import com.example.actions.HomeActions;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;

public class HomeSteps {

    @Steps
    HomeActions homeActions;

    @Then("I see Dashboard page")
    public void iSeeDashboardPage() {
        homeActions.verifyDashboardDisplayed();
    }

    @Then("I see username {string} displayed")
    public void iSeeUsernameDisplayed(String username) {
        homeActions.verifyUsernameDisplayed(username);
    }

    @Then("I see {string} menu displayed")
    public void iSeeMenuDisplayed(String menuName) {
        homeActions.verifyMenuDisplayed(menuName);
    }
}
