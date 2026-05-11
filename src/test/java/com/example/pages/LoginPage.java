package com.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class LoginPage extends PageObject {

    @FindBy(id = "input-username")
    private WebElementFacade usernameField;

    @FindBy(id = "input-password")
    private WebElementFacade passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade loginButton;

    @FindBy(css = "#alert .alert-danger")
    private WebElementFacade errorMessage;

    @FindBy(css = "header .navbar-brand img")
    private WebElementFacade logoImage;

    @FindBy(css = ".card-header")
    private WebElementFacade cardHeader;

    @FindBy(css = "#login-language .dropdown-toggle")
    private WebElementFacade languageSwitcher;

    @FindBy(id = "footer")
    private WebElementFacade footer;

    private String lastHoveredField = null;
    private int errorCountDuringAttempts = 0;
    private int totalLoginAttempts = 0;

    // ================================================================
    // BASIC LOGIN
    // ================================================================

    public void enterUsername(String username) {
        usernameField.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        usernameField.clear();
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

    // ================================================================
    // VALIDATION
    // ================================================================

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.withTimeoutOf(Duration.ofSeconds(6)).waitUntilVisible().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            return errorMessage.withTimeoutOf(Duration.ofSeconds(6)).waitUntilVisible().getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isRequiredFieldWarningDisplayed() {
        Object result = evaluateJavascript(
            "var u = document.getElementById('input-username');" +
            "var p = document.getElementById('input-password');" +
            "return !u.checkValidity() || !p.checkValidity();"
        );
        return Boolean.TRUE.equals(result);
    }

    public String getRequiredFieldValidationMessage() {
        Object usernameMsg = evaluateJavascript(
            "return document.getElementById('input-username').validationMessage;"
        );
        if (usernameMsg != null && !usernameMsg.toString().isEmpty()) {
            return usernameMsg.toString();
        }
        Object passwordMsg = evaluateJavascript(
            "return document.getElementById('input-password').validationMessage;"
        );
        return passwordMsg != null ? passwordMsg.toString() : "";
    }

    // ================================================================
    // AUTO-8: PAGE ELEMENT CHECKS
    // ================================================================

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public boolean isLogoVisible() {
        return logoImage.waitUntilVisible().isDisplayed();
    }

    public String getCardHeaderText() {
        return cardHeader.waitUntilVisible().getText().trim();
    }

    public String getFieldPlaceholder(String fieldName) {
        return resolveField(fieldName).getAttribute("placeholder");
    }

    public boolean isLoginButtonVisibleAndEnabled() {
        return loginButton.waitUntilVisible().isDisplayed() && loginButton.isEnabled();
    }

    public String getLanguageSwitcherText() {
        return languageSwitcher.waitUntilVisible().getText().trim();
    }

    public String getFooterText() {
        return footer.waitUntilVisible().getText().trim();
    }

    // ================================================================
    // AUTO-9: PASSWORD MASKING
    // ================================================================

    public String getFieldTypeAttribute(String fieldName) {
        return resolveField(fieldName).getAttribute("type");
    }

    // ================================================================
    // AUTO-10: LANGUAGE SWITCH
    // ================================================================

    public void clickLanguageSwitcher() {
        languageSwitcher.waitUntilClickable().click();
    }

    public void selectLanguageOption(String langName) {
        find(By.xpath("//ul[contains(@class,'dropdown-menu')]//a[contains(.,'" + langName + "')]"))
            .waitUntilClickable().click();
    }

    public boolean languageSwitcherDisplays(String langName) {
        return getLanguageSwitcherText().contains(langName);
    }

    public boolean urlContainsParameter(String param) {
        return getDriver().getCurrentUrl().contains(param);
    }

    // ================================================================
    // AUTO-13/14: HOVER ON INPUT FIELDS
    // ================================================================

    public void hoverOverField(String fieldName) {
        lastHoveredField = fieldName;
        new Actions(getDriver()).moveToElement(resolveField(fieldName)).perform();
    }

    public boolean isLastHoveredFieldInHoverStyle() {
        if (lastHoveredField == null) return false;
        // Hover border: #b9b9b9 = rgb(185, 185, 185)
        String color = resolveField(lastHoveredField).getCssValue("border-right-color");
        return color.contains("185, 185, 185");
    }

    public String getLastHoveredFieldCursor() {
        if (lastHoveredField == null) return "";
        return resolveField(lastHoveredField).getCssValue("cursor");
    }

    public boolean isLastHoveredFieldFocused() {
        if (lastHoveredField == null) return false;
        Object result = evaluateJavascript(
            "return document.activeElement === arguments[0]",
            resolveField(lastHoveredField)
        );
        return Boolean.TRUE.equals(result);
    }

    // ================================================================
    // AUTO-15: HOVER ON LOGIN BUTTON
    // ================================================================

    public void hoverOverLoginButton() {
        new Actions(getDriver()).moveToElement(loginButton).perform();
    }

    public boolean isLoginButtonInHoverStyle() {
        // Hover background: #1a7bb0 = rgb(26, 123, 176)
        String bg = loginButton.getCssValue("background-color");
        return bg.contains("26, 123, 176");
    }

    public String getLoginButtonCursor() {
        return loginButton.getCssValue("cursor");
    }

    // ================================================================
    // AUTO-16/17: KEYBOARD NAVIGATION
    // ================================================================

    public void focusOnField(String fieldName) {
        resolveField(fieldName).click();
    }

    public void pressEnterKey() {
        getDriver().switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    // ================================================================
    // AUTO-11: SECURITY - SQL INJECTION
    // ================================================================

    public boolean isNotOnDashboard() {
        return !getDriver().getCurrentUrl().contains("route=common/dashboard");
    }

    public boolean hasNoSqlErrorOnPage() {
        String src = getDriver().getPageSource().toLowerCase();
        return !src.contains("you have an error in your sql")
            && !src.contains("warning: mysql")
            && !src.contains("sqlexception")
            && !src.contains("pdoexception");
    }

    // ================================================================
    // AUTO-12: SECURITY - LOCKOUT CHECK
    // ================================================================

    public void loginWithWrongPasswordMultipleTimes(int times, String username) {
        errorCountDuringAttempts = 0;
        totalLoginAttempts = times;
        for (int i = 1; i <= times; i++) {
            enterUsername(username);
            enterPassword("wrongpassword_" + i);
            clickLoginButton();
            if (isErrorMessageDisplayed()) {
                errorCountDuringAttempts++;
            }
        }
    }

    public boolean didEachAttemptShowError() {
        return errorCountDuringAttempts == totalLoginAttempts;
    }

    public boolean isAccountLockedOut() {
        String src = getDriver().getPageSource().toLowerCase();
        return src.contains("account locked")
            || src.contains("too many failed")
            || src.contains("temporarily disabled");
    }

    public boolean hasCaptcha() {
        return !getDriver().findElements(
            By.cssSelector(".g-recaptcha, [data-sitekey], iframe[src*='recaptcha']")
        ).isEmpty();
    }

    // ================================================================
    // HELPER
    // ================================================================

    private WebElementFacade resolveField(String fieldName) {
        return fieldName.equalsIgnoreCase("Password") ? passwordField : usernameField;
    }
}
