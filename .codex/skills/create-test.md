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

### 4. **Action Classes (@Steps/@Step Pattern)**

**Location:** `src/test/java/com/example/actions/<Domain>Action.java`

**Cấu trúc:**
```java
package com.example.actions;

import com.example.pages.LoginPage;
import net.serenitybdd.annotations.Step;

public class LoginAction {

    LoginPage loginPage;   // Serenity tự inject — không dùng new

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
}
```

**Inject vào Step Definition:**
```java
public class LoginSteps {

    @Steps                       // import net.serenitybdd.annotations.Steps
    LoginAction loginAction;     // Serenity tạo proxy, KHÔNG dùng new

    @When("enter username {string}")
    public void enterUsername(String username) {
        loginAction.enterUsername(username);
    }
}
```

**Quy chuẩn:**
- ✓ Package: `com.example.actions`
- ✓ Class naming: `<Domain>Action` (LoginAction, ProductAction)
- ✓ Import: `net.serenitybdd.annotations.Step` và `net.serenitybdd.annotations.Steps`
- ✓ Mỗi method public có `@Step("mô tả hiển thị trong report")`
- ✓ `'{0}'` trong `@Step` = placeholder tham số thứ nhất, `'{1}'` = thứ hai
- ✓ PageObject khai báo là field — Serenity tự inject, không `new`
- ✓ Gọi Page Object methods, không Selenium trực tiếp
- ✓ Step Def inject Action class qua `@Steps` field (không constructor injection)

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
- [ ] Inject Action class qua `@Steps` field (không constructor, không `new`)
- [ ] Chỉ gọi Action method, không Page Object trực tiếp, không Selenium
- [ ] Annotation @Given/@When/@Then đầy đủ
- [ ] Parameter rõ ràng

**Page Object:**
- [ ] Class trong `pages` package, extend PageObject
- [ ] @FindBy locators cụ thể
- [ ] Methods tên rõ (động từ + object)
- [ ] Dùng wait, không Thread.sleep()
- [ ] Không có assertion trực tiếp

**Action (@Steps/@Step):**
- [ ] Class trong `actions` package
- [ ] Mỗi method public có `@Step("mô tả")`
- [ ] PageObject là field — Serenity inject, không `new`
- [ ] Gọi Page Object, không Selenium trực tiếp
- [ ] Inject vào Step Def qua `@Steps` field

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

