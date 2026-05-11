package com.example.stepdefinitions;

import com.example.actions.LoginAction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    @Steps
    LoginAction loginAction;

    @Given("Go to login page")
    public void goToLoginPage() {
        loginAction.navigateToLoginPage();
    }

    @When("enter username {string}")
    public void enterUsername(String username) {
        loginAction.enterUsername(username);
    }

    @When("enter password {string}")
    public void enterPassword(String password) {
        loginAction.enterPassword(password);
    }

    @Then("I see error message is displayed")
    public void iSeeErrorMessageIsDisplayed() {
        assertTrue(loginAction.isErrorMessageDisplayed(),
                "Error message should be displayed after invalid login");
    }

    @Then("I see required field warning is displayed")
    public void iSeeRequiredFieldWarningIsDisplayed() {
        assertTrue(loginAction.isRequiredFieldWarningDisplayed(),
                "Required field warning should be displayed when submitting empty fields");
    }

    @When("I login with user {string} and password {string}")
    public void iLoginWithUserAndPassword(String username, String password) {
        loginAction.enterUsername(username);
        loginAction.enterPassword(password);
        loginAction.clickLoginButton();
    }
}
