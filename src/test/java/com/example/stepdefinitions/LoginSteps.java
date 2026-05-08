package com.example.stepdefinitions;

import com.example.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    LoginPage loginPage;

    @Given("Go to login page")
    public void goToLoginPage() {
        loginPage.open();
    }

    @When("enter username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("enter password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
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
