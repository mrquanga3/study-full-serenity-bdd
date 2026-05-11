package com.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    @FindBy(id = "input-username")
    private WebElementFacade usernameField;

    @FindBy(id = "input-password")
    private WebElementFacade passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade loginButton;

    @FindBy(css = ".alert-danger")
    private WebElementFacade errorMessage;

    @FindBy(css = ".field-error, .text-danger, input:invalid")
    private WebElementFacade requiredFieldWarning;

    public void enterUsername(String username) {
        usernameField.waitUntilVisible().clear();
        usernameField.type(username);
    }

    public void enterPassword(String password) {
        passwordField.waitUntilVisible().clear();
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.waitUntilVisible().isDisplayed();
    }

    public boolean isRequiredFieldWarningDisplayed() {
        return requiredFieldWarning.waitUntilVisible().isDisplayed();
    }
}
