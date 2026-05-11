package com.example.actions;

import com.example.pages.LoginPage;
import net.serenitybdd.annotations.Step;

public class LoginAction {

    LoginPage loginPage;

    @Step("Navigate to login page")
    public void navigateToLoginPage() {
        loginPage.open();
    }

    @Step("Enter username '{0}'")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Step("Login as user '{0}'")
    public void loginAs(String username, String password) {
        loginPage.loginAs(username, password);
    }

    @Step("Check error message is displayed")
    public boolean isErrorMessageDisplayed() {
        return loginPage.isErrorMessageDisplayed();
    }

    @Step("Check required field warning is displayed")
    public boolean isRequiredFieldWarningDisplayed() {
        return loginPage.isRequiredFieldWarningDisplayed();
    }
}
