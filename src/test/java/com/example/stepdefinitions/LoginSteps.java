package com.example.stepdefinitions;

import com.example.actions.LoginActions;
import com.example.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class LoginSteps {

    LoginPage loginPage;

    @Steps
    LoginActions loginActions;

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
        loginActions.verifyErrorMessageDisplayed();
    }

    @Then("I see required field warning is displayed")
    public void iSeeRequiredFieldWarningIsDisplayed() {
        loginActions.verifyRequiredFieldWarningDisplayed();
    }
}
