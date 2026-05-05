package com.example.stepdefinitions;

import com.example.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    LoginPage loginPage;

    private boolean isUsernameEntered = false;

    @Given("Go to login page")
    public void goToLoginPage() {
        loginPage.open();
        isUsernameEntered = false;
    }

    @When("enter {string}")
    public void enter(String value) {
        if (!isUsernameEntered) {
            loginPage.enterUsername(value);
            isUsernameEntered = true;
        } else {
            loginPage.enterPassword(value);
        }
    }

    @Then("I see error message is displayed")
    public void iSeeErrorMessageIsDisplayed() {
        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed after invalid login");
    }

    @Then("I see required field warning is displayed")
    public void iSeeRequiredFieldWarningIsDisplayed() {
        assertTrue(loginPage.isRequiredFieldWarningDisplayed(),
                "Required field warning should be displayed when submitting empty fields");
    }
}
