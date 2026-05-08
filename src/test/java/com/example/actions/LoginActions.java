package com.example.actions;

import com.example.pages.LoginPage;
import net.serenitybdd.annotations.Step;
import org.junit.jupiter.api.Assertions;

public class LoginActions {

    LoginPage loginPage;

    @Step("Verify error message is displayed")
    public void verifyErrorMessageDisplayed() {
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed after invalid login");
    }

    @Step("Verify required field warning is displayed")
    public void verifyRequiredFieldWarningDisplayed() {
        Assertions.assertTrue(loginPage.isRequiredFieldWarningDisplayed(),
                "Required field warning should be displayed when submitting empty fields");
    }
}
