package com.example.actions;

import com.example.pages.LoginPage;
import net.serenitybdd.annotations.Step;

public class LoginAction {

    LoginPage loginPage;

    // ================================================================
    // BASIC LOGIN
    // ================================================================

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

    // ================================================================
    // VALIDATION
    // ================================================================

    @Step("Check error message is displayed")
    public boolean isErrorMessageDisplayed() {
        return loginPage.isErrorMessageDisplayed();
    }

    @Step("Get error message text")
    public String getErrorMessageText() {
        return loginPage.getErrorMessageText();
    }

    @Step("Check required field warning is displayed")
    public boolean isRequiredFieldWarningDisplayed() {
        return loginPage.isRequiredFieldWarningDisplayed();
    }

    @Step("Get required field validation message")
    public String getRequiredFieldValidationMessage() {
        return loginPage.getRequiredFieldValidationMessage();
    }

    // ================================================================
    // AUTO-8: PAGE ELEMENT CHECKS
    // ================================================================

    @Step("Get page title")
    public String getPageTitle() {
        return loginPage.getPageTitle();
    }

    @Step("Check OpenCart logo is visible")
    public boolean isLogoVisible() {
        return loginPage.isLogoVisible();
    }

    @Step("Get card header text")
    public String getCardHeaderText() {
        return loginPage.getCardHeaderText();
    }

    @Step("Get placeholder of '{0}' field")
    public String getFieldPlaceholder(String fieldName) {
        return loginPage.getFieldPlaceholder(fieldName);
    }

    @Step("Check Login button is visible and enabled")
    public boolean isLoginButtonVisibleAndEnabled() {
        return loginPage.isLoginButtonVisibleAndEnabled();
    }

    @Step("Get language switcher text")
    public String getLanguageSwitcherText() {
        return loginPage.getLanguageSwitcherText();
    }

    @Step("Get footer text")
    public String getFooterText() {
        return loginPage.getFooterText();
    }

    // ================================================================
    // AUTO-9: PASSWORD MASKING
    // ================================================================

    @Step("Get type attribute of '{0}' field")
    public String getFieldTypeAttribute(String fieldName) {
        return loginPage.getFieldTypeAttribute(fieldName);
    }

    // ================================================================
    // AUTO-10: LANGUAGE SWITCH
    // ================================================================

    @Step("Click language switcher")
    public void clickLanguageSwitcher() {
        loginPage.clickLanguageSwitcher();
    }

    @Step("Select language '{0}'")
    public void selectLanguageOption(String langName) {
        loginPage.selectLanguageOption(langName);
    }

    @Step("Check language switcher displays '{0}'")
    public boolean languageSwitcherDisplays(String langName) {
        return loginPage.languageSwitcherDisplays(langName);
    }

    @Step("Check URL contains '{0}'")
    public boolean urlContainsParameter(String param) {
        return loginPage.urlContainsParameter(param);
    }

    // ================================================================
    // AUTO-13/14: HOVER ON INPUT FIELDS
    // ================================================================

    @Step("Hover over '{0}' input field")
    public void hoverOverField(String fieldName) {
        loginPage.hoverOverField(fieldName);
    }

    @Step("Check hovered input field has hover border style")
    public boolean isLastHoveredFieldInHoverStyle() {
        return loginPage.isLastHoveredFieldInHoverStyle();
    }

    @Step("Get cursor of hovered input field")
    public String getLastHoveredFieldCursor() {
        return loginPage.getLastHoveredFieldCursor();
    }

    @Step("Check hovered input field is not focused")
    public boolean isLastHoveredFieldFocused() {
        return loginPage.isLastHoveredFieldFocused();
    }

    // ================================================================
    // AUTO-15: HOVER ON LOGIN BUTTON
    // ================================================================

    @Step("Hover over Login button")
    public void hoverOverLoginButton() {
        loginPage.hoverOverLoginButton();
    }

    @Step("Check Login button has hover background color")
    public boolean isLoginButtonInHoverStyle() {
        return loginPage.isLoginButtonInHoverStyle();
    }

    @Step("Get Login button cursor style")
    public String getLoginButtonCursor() {
        return loginPage.getLoginButtonCursor();
    }

    // ================================================================
    // AUTO-16/17: KEYBOARD NAVIGATION
    // ================================================================

    @Step("Focus on '{0}' field")
    public void focusOnField(String fieldName) {
        loginPage.focusOnField(fieldName);
    }

    @Step("Press Enter key")
    public void pressEnterKey() {
        loginPage.pressEnterKey();
    }

    // ================================================================
    // AUTO-11: SECURITY - SQL INJECTION
    // ================================================================

    @Step("Check current page is not the Dashboard")
    public boolean isNotOnDashboard() {
        return loginPage.isNotOnDashboard();
    }

    @Step("Check no SQL error or stack trace on page")
    public boolean hasNoSqlErrorOnPage() {
        return loginPage.hasNoSqlErrorOnPage();
    }

    // ================================================================
    // AUTO-12: SECURITY - LOCKOUT CHECK
    // ================================================================

    @Step("Login with wrong password {0} times for user '{1}'")
    public void loginWithWrongPasswordMultipleTimes(int times, String username) {
        loginPage.loginWithWrongPasswordMultipleTimes(times, username);
    }

    @Step("Check error was shown after each failed attempt")
    public boolean didEachAttemptShowError() {
        return loginPage.didEachAttemptShowError();
    }

    @Step("Check account is not locked out")
    public boolean isAccountLockedOut() {
        return loginPage.isAccountLockedOut();
    }

    @Step("Check no CAPTCHA is displayed")
    public boolean hasCaptcha() {
        return loginPage.hasCaptcha();
    }
}
