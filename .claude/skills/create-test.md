# ✨ create-test

**Mô tả:** Tạo test mới: feature files, step definitions, page objects, và screenplay tasks.

---

## 🎯 Khi nào sử dụng?

- Tạo feature file (.feature) mới
- Viết step definitions mới
- Tạo page object mới
- Viết screenplay task/action mới
- Tạo test data files (CSV, JSON)

---

## 🔑 Keywords

- `create test`
- `new feature`
- `add step`
- `new page object`
- `add screenplay`
- `create action`

---

## 📋 Hướng dẫn chi tiết

### 1. **Feature Files (.feature)**

**Location:** `src/test/resources/features/<domain>/<feature_name>.feature`

**Format:**
```gherkin
@<domain> @smoke  # Luôn có tag
Feature: Feature description

  Background:  # Optional: setup chung
    Given user is on home page

  Scenario: Clear scenario description
    Given precondition
    When action
    Then verification
    And more verification
```

**Quy chuẩn:**
- ✓ File naming: snake_case (login_flow.feature)
- ✓ Domain tag: `@ui`, `@api`, `@integration`
- ✓ Scenario tag: `@smoke`, `@regression`
- ✓ Gherkin chuẩn: Given-When-Then
- ✓ Không hardcode test data → dùng Examples: hoặc testdata/
- ✓ Scenario mô tả rõ business flow, không kỹ thuật

**Ví dụ:**
```gherkin
@ui @smoke
Feature: User Login

  Scenario: Login with valid credentials
    Given user is on login page
    When user enters email "test@example.com"
    And user enters password "password123"
    And user clicks login button
    Then user should see dashboard
    And user name should display "Test User"
```

---

### 2. **Step Definitions**

**Location:** `src/test/java/com/example/stepdefinitions/<Domain>Steps.java`

**Cấu trúc:**
```java
package com.example.stepdefinitions;

import io.cucumber.java.en.*;
import com.example.pages.*;

public class LoginSteps {
  
  private LoginPage loginPage;
  
  public LoginSteps(LoginPage loginPage) {
    this.loginPage = loginPage;
  }
  
  @Given("user is on login page")
  public void userOnLoginPage() {
    loginPage.open();
  }
  
  @When("user enters email {string}")
  public void enterEmail(String email) {
    loginPage.fillEmail(email);
  }
  
  @Then("user should see dashboard")
  public void verifyDashboard() {
    loginPage.shouldSeeDashboard();
  }
}
```

**Quy chuẩn:**
- ✓ Package: `com.example.stepdefinitions`
- ✓ Class naming: `<Domain>Steps` (LoginSteps, ProductSteps)
- ✓ Inject Page Object/Action via constructor
- ✓ Annotation: `@Given`, `@When`, `@Then` rõ ràng
- ✓ Parameter: dùng Cucumber Expressions `{string}`, `{int}`, `{double}`
- ✓ Không chứa Selenium logic trực tiếp
- ✓ Chỉ gọi Page Object hoặc Action method
- ✓ Không dùng `Thread.sleep()`

---

### 3. **Page Objects**

**Location:** `src/test/java/com/example/pages/<PageName>Page.java`

**Cấu trúc:**
```java
package com.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {
  
  @FindBy(id = "email")
  private WebElement emailField;
  
  @FindBy(css = "button[type='submit']")
  private WebElement loginButton;
  
  public void fillEmail(String email) {
    emailField.clear();
    emailField.sendKeys(email);
  }
  
  public void clickLogin() {
    loginButton.click();
  }
  
  public void shouldSeeDashboard() {
    $(By.cssSelector(".dashboard")).shouldBeVisible();
  }
  
  public void open() {
    openUrl("https://example.com/login");
  }
}
```

**Quy chuẩn:**
- ✓ Package: `com.example.pages`
- ✓ Class naming: `<PageName>Page` (LoginPage, ProductPage)
- ✓ Extend `PageObject` từ Serenity
- ✓ Locators: `@FindBy` với specific selectors (id > css > xpath)
- ✓ Methods tên rõ: `clickLoginButton()`, không `click()`
- ✓ Không chứa Assert trực tiếp
- ✓ Dùng `waitUntilVisible()`, `withTimeoutOf()` thay `Thread.sleep()`
- ✓ Return value nếu cần dùng trong step

---

### 4. **Screenplay Tasks/Actions**

**Location:** `src/test/java/com/example/actions/<ActionName>.java`

**Cấu trúc:**
```java
package com.example.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import com.example.pages.LoginPage;

public class LoginWithValidCredentials implements Task {
  
  private String email;
  private String password;
  
  private LoginPage loginPage;
  
  public LoginWithValidCredentials(String email, String password) {
    this.email = email;
    this.password = password;
  }
  
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
      Click.on(loginPage.getLoginButton()),
      Enter.theValue(email).into(loginPage.getEmailField()),
      Enter.theValue(password).into(loginPage.getPasswordField()),
      Click.on(loginPage.getSubmitButton())
    );
  }
  
  public static LoginWithValidCredentials withCredentials(String email, String password) {
    return new LoginWithValidCredentials(email, password);
  }
}
```

**Quy chuẩn:**
- ✓ Package: `com.example.actions`
- ✓ Class naming: Action động từ + object (LoginWithValidCredentials)
- ✓ Implement `Task` từ Serenity
- ✓ Method `performAs(Actor actor)`
- ✓ Gọi Page Object methods, không Selenium trực tiếp
- ✓ Builder pattern: static method tạo instance
- ✓ Tên rõ ràng, describe business action

---

### 5. **Test Data Files**

**Location:** `src/test/resources/testdata/<domain>/<scenario>.csv` hoặc `.json`

**CSV Format:**
```csv
email,password,expected_result
valid@test.com,password123,success
invalid@test.com,wrongpass,error
```

**JSON Format:**
```json
{
  "testCases": [
    {
      "email": "valid@test.com",
      "password": "password123",
      "expectedResult": "success"
    }
  ]
}
```

**Quy chuẩn:**
- ✓ Location: `src/test/resources/testdata/<domain>/`
- ✓ Naming: snake_case (login_valid_data.csv)
- ✓ Format: CSV hoặc JSON (tùy preference)
- ✓ Không hardcode sensitive data
- ✓ Header rõ ràng (email, password, expected_result)

---

## ✅ Checklist tạo test mới

**Feature File:**
- [ ] File naming: snake_case
- [ ] Có @domain @tag
- [ ] Scenario mô tả rõ business flow
- [ ] Dùng Examples hoặc testdata, không hardcode

**Step Definition:**
- [ ] Class trong `stepdefinitions` package
- [ ] Inject Page Object via constructor
- [ ] Chỉ gọi Page Object/Action, không Selenium
- [ ] Annotation @Given/@When/@Then đầy đủ
- [ ] Parameter rõ ràng

**Page Object:**
- [ ] Class trong `pages` package, extend PageObject
- [ ] @FindBy locators cụ thể
- [ ] Methods tên rõ (động từ + object)
- [ ] Dùng wait, không Thread.sleep()
- [ ] Không có assertion trực tiếp

**Action (Screenplay):**
- [ ] Class trong `actions` package, implement Task
- [ ] Builder pattern static method
- [ ] performAs() gọi Page Object, không Selenium
- [ ] Tên rõ ràng

**Test Data:**
- [ ] File trong `testdata/<domain>/`
- [ ] Format rõ ràng (CSV/JSON)
- [ ] Header/key meaningful
- [ ] Không hardcode sensitive data

**General:**
- [ ] Code compile, test run được
- [ ] Test chạy pass
- [ ] Update PROJECT_PROGRESS.md
- [ ] Update README.md nếu là ví dụ mới

---

## 🏷️ Tags

- `test`
- `feature`
- `serenity`
- `automation`

