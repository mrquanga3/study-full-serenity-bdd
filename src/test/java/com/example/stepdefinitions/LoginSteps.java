package com.example.stepdefinitions;

import com.example.actions.LoginAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    @Steps
    LoginAction loginAction;

    // ================================================================
    // BACKGROUND / NAVIGATION
    // ================================================================

    @Given("Go to login page")
    public void goToLoginPage() {
        loginAction.navigateToLoginPage();
    }

    // ================================================================
    // ACTIONS
    // ================================================================

    @When("enter username {string}")
    public void enterUsername(String username) {
        loginAction.enterUsername(username);
    }

    @When("enter password {string}")
    public void enterPassword(String password) {
        loginAction.enterPassword(password);
    }

    @When("I login with user {string} and password {string}")
    public void iLoginWithUserAndPassword(String username, String password) {
        loginAction.loginAs(username, password);
    }

    @When("I enter {string} into the Password field")
    public void iEnterIntoPasswordField(String value) {
        loginAction.enterPassword(value);
    }

    @When("I click the language switcher")
    public void iClickLanguageSwitcher() {
        loginAction.clickLanguageSwitcher();
    }

    @When("I select language {string}")
    public void iSelectLanguage(String langName) {
        loginAction.selectLanguageOption(langName);
    }

    @When("I hover over the {string} input field")
    public void iHoverOverInputField(String fieldName) {
        loginAction.hoverOverField(fieldName);
    }

    @When("I hover over the Login button")
    public void iHoverOverLoginButton() {
        loginAction.hoverOverLoginButton();
    }

    @When("I focus on the {string} field")
    public void iFocusOnField(String fieldName) {
        loginAction.focusOnField(fieldName);
    }

    @When("I press the Enter key")
    public void iPressEnterKey() {
        loginAction.pressEnterKey();
    }

    @When("I attempt to login with wrong password {int} times for user {string}")
    public void iAttemptLoginWithWrongPasswordTimes(int times, String username) {
        loginAction.loginWithWrongPasswordMultipleTimes(times, username);
    }

    // ================================================================
    // ASSERTIONS — NAVIGATION
    // ================================================================

    @Then("I see error message {string}")
    public void iSeeErrorMessage(String expectedMessage) {
        String actual = loginAction.getErrorMessageText();
        assertTrue(actual.contains(expectedMessage),
            "Expected error message: \"" + expectedMessage + "\" but got: \"" + actual + "\"");
    }

    @Then("I see required field warning {string}")
    public void iSeeRequiredFieldWarning(String expectedMessage) {
        String actual = loginAction.getRequiredFieldValidationMessage();
        assertTrue(actual.contains(expectedMessage),
            "Expected validation message: \"" + expectedMessage + "\" but got: \"" + actual + "\"");
    }

    @Then("I am not redirected to the Dashboard")
    public void iAmNotRedirectedToDashboard() {
        assertTrue(loginAction.isNotOnDashboard(),
            "Should remain on Login page and not be redirected to Dashboard");
    }

    // ================================================================
    // ASSERTIONS — AUTO-8: PAGE ELEMENTS
    // ================================================================

    @Then("I see the page title is {string}")
    public void iSeeThePageTitleIs(String expectedTitle) {
        assertEquals(expectedTitle, loginAction.getPageTitle(),
            "Page title should be: " + expectedTitle);
    }

    @Then("I see the OpenCart logo in the header")
    public void iSeeTheOpenCartLogoInTheHeader() {
        assertTrue(loginAction.isLogoVisible(),
            "OpenCart logo should be visible in the header");
    }

    @Then("I see the card header text {string}")
    public void iSeeTheCardHeaderText(String expectedText) {
        assertTrue(loginAction.getCardHeaderText().contains(expectedText),
            "Card header should contain: " + expectedText);
    }

    @Then("I see the {word} field with placeholder {string}")
    public void iSeeTheFieldWithPlaceholder(String fieldName, String expectedPlaceholder) {
        assertEquals(expectedPlaceholder, loginAction.getFieldPlaceholder(fieldName),
            fieldName + " field placeholder should be: " + expectedPlaceholder);
    }

    @Then("I see the Login button is visible and enabled")
    public void iSeeTheLoginButtonIsVisibleAndEnabled() {
        assertTrue(loginAction.isLoginButtonVisibleAndEnabled(),
            "Login button should be visible and enabled");
    }

    @Then("I see the language switcher showing {string}")
    public void iSeeTheLanguageSwitcherShowing(String expectedLang) {
        assertTrue(loginAction.getLanguageSwitcherText().contains(expectedLang),
            "Language switcher should show: " + expectedLang);
    }

    @Then("I see the footer text {string}")
    public void iSeeTheFooterText(String expectedText) {
        assertTrue(loginAction.getFooterText().contains(expectedText),
            "Footer should contain: " + expectedText);
    }

    // ================================================================
    // ASSERTIONS — AUTO-9: PASSWORD MASKING
    // ================================================================

    @Then("the Password field masks the characters")
    public void thePasswordFieldMasksTheCharacters() {
        assertEquals("password", loginAction.getFieldTypeAttribute("Password"),
            "Password field type should be 'password' to mask input characters");
    }

    @Then("the Password input has type attribute {string}")
    public void thePasswordInputHasTypeAttribute(String expectedType) {
        assertEquals(expectedType, loginAction.getFieldTypeAttribute("Password"),
            "Password input type attribute should be: " + expectedType);
    }

    // ================================================================
    // ASSERTIONS — AUTO-10: LANGUAGE SWITCH
    // ================================================================

    @Then("the language switcher displays {string}")
    public void theLanguageSwitcherDisplays(String langName) {
        assertTrue(loginAction.languageSwitcherDisplays(langName),
            "Language switcher should display: " + langName);
    }

    @Then("the URL contains the parameter {string}")
    public void theUrlContainsTheParameter(String param) {
        assertTrue(loginAction.urlContainsParameter(param),
            "URL should contain parameter: " + param);
    }

    // ================================================================
    // ASSERTIONS — AUTO-13/14: HOVER ON INPUT FIELDS
    // ================================================================

    @Then("the input border changes to hover style")
    public void theInputBorderChangesToHoverStyle() {
        assertTrue(loginAction.isLastHoveredFieldInHoverStyle(),
            "Input border-right-color should change to #b9b9b9 (rgb 185,185,185) on hover");
    }

    @Then("the cursor on the input field is a text cursor")
    public void theCursorOnTheInputFieldIsATextCursor() {
        String cursor = loginAction.getLastHoveredFieldCursor();
        assertTrue(cursor.equals("text") || cursor.equals("auto"),
            "Input field cursor should be 'text', got: " + cursor);
    }

    @Then("the input field is not focused")
    public void theInputFieldIsNotFocused() {
        assertFalse(loginAction.isLastHoveredFieldFocused(),
            "Input field should not be focused after hover-only (no click)");
    }

    // ================================================================
    // ASSERTIONS — AUTO-15: HOVER ON LOGIN BUTTON
    // ================================================================

    @Then("the Login button background changes to hover color {string}")
    public void theLoginButtonBackgroundChangesToHoverColor(String hexColor) {
        assertTrue(loginAction.isLoginButtonInHoverStyle(),
            "Login button background should change to hover color " + hexColor + " (rgb 26,123,176)");
    }

    @Then("the cursor on the Login button changes to pointer")
    public void theCursorOnTheLoginButtonChangesToPointer() {
        assertTrue(loginAction.getLoginButtonCursor().contains("pointer"),
            "Login button cursor should be 'pointer'");
    }

    @Then("the Login button is not clicked")
    public void theLoginButtonIsNotClicked() {
        assertTrue(loginAction.isNotOnDashboard(),
            "Should remain on Login page — button was hovered, not clicked");
    }

    // ================================================================
    // ASSERTIONS — AUTO-11: SECURITY
    // ================================================================

    @Then("no SQL error or stack trace is shown on the page")
    public void noSqlErrorOrStackTraceIsShownOnThePage() {
        assertTrue(loginAction.hasNoSqlErrorOnPage(),
            "No SQL error or stack trace should be visible on the page");
    }

    // ================================================================
    // ASSERTIONS — AUTO-12: LOCKOUT
    // ================================================================

    @Then("after each attempt the error message is displayed")
    public void afterEachAttemptTheErrorMessageIsDisplayed() {
        assertTrue(loginAction.didEachAttemptShowError(),
            "Error message should have been displayed after every failed login attempt");
    }

    @Then("the account is not locked out")
    public void theAccountIsNotLockedOut() {
        assertFalse(loginAction.isAccountLockedOut(),
            "Account should not be locked out after multiple failed login attempts");
    }

    @Then("no CAPTCHA challenge is shown")
    public void noCaptchaChallengeIsShown() {
        assertFalse(loginAction.hasCaptcha(),
            "No CAPTCHA should appear on the login page");
    }
}
