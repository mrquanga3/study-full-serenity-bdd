# 👀 review-code

**Mô tả:** Review test code, kiểm tra quality, security, và best practices của Serenity BDD.

---

## 🎯 Khi nào sử dụng?

- Review code quality của test code
- Kiểm tra convention compliance
- Validate best practices
- Audit security concerns
- Check maintainability

---

## 🔑 Keywords

- `review code`
- `code review`
- `check quality`
- `validate`
- `audit code`

---

## 📋 Hướng dẫn chi tiết

### 1. **Structure Review**

Kiểm tra cấu trúc project & naming convention:

#### **Package Structure**
```java
com.example.*
├── runners/           ← CucumberTestRunner
├── stepdefinitions/   ← Step definitions
├── pages/            ← Page objects
├── actions/          ← Screenplay tasks
└── utils/            ← Helper classes
```

**Review points:**
- ✓ Package naming: `com.example.*` follow convention
- ✓ File location: match folder structure (Pages → pages/, Steps → stepdefinitions/)
- ✓ No misplaced files (Page Object in stepdefinitions/)

#### **Class Naming**
```java
✓ LoginPage.java              ← PascalCase
✓ LoginSteps.java             ← PascalCase + "Steps"
✓ LoginWithValidCredentials   ← Action: PascalCase, describe action
❌ login_page.java            ← snake_case (wrong)
❌ LoginPageObject.java       ← Redundant "Page"
❌ Do_Login.java              ← Inconsistent naming
```

#### **Method Naming**
```java
✓ fillEmail(String email)     ← camelCase, clear action
✓ clickLoginButton()          ← verb + object
✓ verifySuccessMessage()      ← describe verification
❌ fill_email()               ← snake_case (wrong)
❌ click()                    ← too generic
❌ doStuff()                  ← not descriptive
```

---

### 2. **Page Object Review**

#### **Class Declaration**
```java
✓ public class LoginPage extends PageObject { }
❌ public class LoginPageObject extends PageObject { }
❌ public class LoginPage extends WebDriver { }
```

#### **Locator (@FindBy) Review**
```java
// ✓ Good: Specific, stable CSS selector
@FindBy(css = "button.login-btn")
private WebElement loginButton;

// ✓ Good: ID selector (most stable)
@FindBy(id = "email-input")
private WebElement emailField;

// ✓ Good: XPath with specific attribute
@FindBy(xpath = "//button[@type='submit']")
private WebElement submitButton;

// ❌ Bad: Generic selector (fragile)
@FindBy(css = "button")  // Could match many buttons
private WebElement btn;

// ❌ Bad: Index-based (breaks easily)
@FindBy(xpath = "//button[3]")
private WebElement thirdButton;

// ❌ Bad: Hardcoded text (fragile)
@FindBy(xpath = "//button[text()='Exact Text']")
private WebElement button;
```

**Best practice:**
```
Priority order:
1. ID selector      - most stable
2. CSS selector     - stable
3. XPath attribute  - ok
4. Text content     - fragile
5. Index-based      - very fragile
```

#### **Method Quality**
```java
// ❌ Bad: Assertion in Page Object
public void fillEmail(String email) {
  emailField.sendKeys(email);
  assert emailField.getValue().equals(email);  // ← Wrong place!
}

// ✓ Good: Page Object only returns/performs action
public void fillEmail(String email) {
  emailField.clear();
  emailField.sendKeys(email);
}

// ✓ Good: Assertion in Step or Action
public void verifyEmailFilled(String email) {
  emailField.shouldHaveValue(email);
}
```

#### **Wait Strategy**
```java
// ❌ Bad: Hardcode sleep
Thread.sleep(3000);
emailField.click();

// ✓ Good: Explicit wait
emailField.waitUntilClickable().click();

// ✓ Good: Custom timeout
element.waitUntilVisible().withTimeoutOf(Duration.ofSeconds(10));

// ✓ Good: Wait for specific condition
wait.until(ExpectedConditions.visibilityOf(element));
```

---

### 3. **Step Definition Review**

#### **Annotation Review**
```java
// ✓ Good: Clear, one step per method
@Given("user is on login page")
public void userOnLoginPage() {
  loginPage.open();
}

@When("user enters email {string}")
public void userEntersEmail(String email) {
  loginPage.fillEmail(email);
}

@Then("user should see error message")
public void userSeesError() {
  loginPage.shouldShowErrorMessage();
}

// ❌ Bad: Missing annotation
public void userLogin() {
  // No @Given/@When/@Then → won't be recognized
}

// ❌ Bad: Multiple steps in one
@When("user does login and sees dashboard and profile loads")
public void complexStep() {
  // Too many actions
}
```

#### **Parameter Handling**
```java
// ✓ Good: Clear parameter type
@When("user enters email {string}")
public void enterEmail(String email) { }

@Given("user has {int} items in cart")
public void hasItems(int count) { }

// ✓ Good: Descriptive regex
@When("user enters {word} in search")
public void search(String term) { }

// ❌ Bad: Ambiguous regex
@When("user enters (.+)")
public void enter(String value) {
  // Unclear what "value" is
}

// ❌ Bad: Hardcode instead of parameter
@When("user enters email")
public void enterEmail() {
  loginPage.fillEmail("test@example.com");  // ← Hardcoded!
}
```

#### **Dependency Injection**
```java
// ✓ Good: Inject via constructor
public class LoginSteps {
  private LoginPage loginPage;
  
  public LoginSteps(LoginPage loginPage) {
    this.loginPage = loginPage;
  }
}

// ❌ Bad: Static reference
public class LoginSteps {
  private static LoginPage loginPage = new LoginPage();
}

// ❌ Bad: Create new instance
public class LoginSteps {
  private LoginPage loginPage = new LoginPage();
}
```

#### **No Selenium Directly**
```java
// ❌ Bad: Selenium code in step
@When("user clicks email field")
public void clickEmail() {
  driver.findElement(By.id("email")).click();  // ← Direct Selenium!
}

// ✓ Good: Call Page Object
@When("user clicks email field")
public void clickEmail() {
  loginPage.focusEmail();
}
```

---

### 4. **Screenplay/Action Review**

#### **Task Implementation**
```java
// ✓ Good: Implements Task, clear performAs
public class LoginWithValidCredentials implements Task {
  private String email;
  private String password;
  
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
      Enter.theValue(email).into(LoginPage.EMAIL),
      Enter.theValue(password).into(LoginPage.PASSWORD),
      Click.on(LoginPage.SUBMIT)
    );
  }
}

// ❌ Bad: No clear structure
public class LoginTask {
  public void login(Actor actor) {
    // Not implementing Task interface
  }
}

// ❌ Bad: Selenium code instead of Interactions
@Override
public <T extends Actor> void performAs(T actor) {
  WebDriver driver = actor.usingAbility(BrowseTheWeb.class).getDriver();
  driver.findElement(By.id("email")).sendKeys("test");  // ← Wrong!
}
```

#### **Builder Pattern**
```java
// ✓ Good: Builder pattern for configuration
public class SearchProduct implements Task {
  private String keyword;
  
  public static SearchProduct byKeyword(String keyword) {
    SearchProduct task = new SearchProduct();
    task.keyword = keyword;
    return task;
  }
}

// Usage:
actor.attemptsTo(SearchProduct.byKeyword("laptop"));

// ❌ Bad: No builder pattern
public class SearchProduct implements Task {
  public SearchProduct(String keyword) { }
}
```

---

### 5. **Test Best Practice Review**

#### **Feature File Review**
```gherkin
// ✓ Good: Clear tags, readable steps
@ui @smoke
Feature: User Login
  
  Scenario: Login with valid credentials
    Given user is on login page
    When user enters valid email and password
    And user clicks login button
    Then user should see dashboard

// ❌ Bad: No tags
Feature: Login

// ❌ Bad: Technical steps, not business
Scenario: Login test
  Given WebDriver is initialized
  And element with id "email" is present
  When element click is executed
  Then assertion on class name passes
```

#### **Data Handling**
```java
// ✓ Good: Use Examples or CSV
Scenario Outline: Login with credentials
  When user login with <email> and <password>
  Examples:
    | email | password |
    | test@test.com | pass123 |

// ✓ Good: Use testdata folder
@CsvSource({
  "test@test.com,pass123",
  "admin@test.com,admin123"
})

// ❌ Bad: Hardcode in step
@When("user login")
public void login() {
  loginPage.login("test@test.com", "pass123");
}
```

#### **Hooks & Setup**
```java
// ✓ Good: Clear hooks with tag
@Before("@db-cleanup")
public void setupDatabase() {
  dbHelper.cleanupData();
}

@After
public void cleanup() {
  driver.quit();
}

// ❌ Bad: Generic hooks without need
@Before
public void beforeEach() {
  // What does this do?
}

// ❌ Bad: Sleep in hooks
@Before
public void setup() {
  Thread.sleep(2000);  // Why sleep?
}
```

---

### 6. **Code Quality Checklist**

#### **Naming & Clarity**
- [ ] Classes: PascalCase, descriptive names
- [ ] Methods: camelCase, verb + object or clear intent
- [ ] Variables: meaningful names, no single-letter (except loop)
- [ ] Constants: UPPER_SNAKE_CASE

#### **No Code Duplication**
- [ ] No copy-paste code (extract to method)
- [ ] No duplicate locators (consolidate)
- [ ] No duplicate test data (use file or parameterization)

#### **No Hardcode Values**
- [ ] No hardcoded email/password/URL
- [ ] No hardcoded wait times (use explicit wait)
- [ ] No magic numbers (extract to constants)
- [ ] No test data in code (use Examples or CSV)

#### **No Anti-patterns**
- [ ] ❌ No `Thread.sleep()` - use explicit wait
- [ ] ❌ No assertions in Page Objects - only return/perform
- [ ] ❌ No Selenium code in Step Definitions - call Page Object
- [ ] ❌ No static WebDriver/Page Object references
- [ ] ❌ No ignored/skipped tests without reason

#### **Security Review**
- [ ] ❌ No passwords hardcoded in code
- [ ] ❌ No API keys in code (use config/env vars)
- [ ] ❌ No sensitive data in logs
- [ ] ❌ No SQL injection risk (use prepared statements if DB testing)

#### **Maintainability**
- [ ] Code has clear structure (not spaghetti)
- [ ] Methods are small and focused (< 20 lines)
- [ ] Comments explain WHY, not WHAT
- [ ] Error messages are descriptive

---

## ✅ Code Review Checklist

When reviewing code:

```
□ Structure: Package, naming convention, file locations
□ Page Object:
  ├─ Extend PageObject, locators specific
  ├─ Methods clear and focused
  ├─ No assertions, only actions/returns
  └─ Wait explicit, no Thread.sleep()
  
□ Step Definitions:
  ├─ Annotations clear (@Given/@When/@Then)
  ├─ Only call Page Object/Action
  ├─ No hardcode data
  └─ Dependency inject correctly
  
□ Actions/Tasks:
  ├─ Implement Task interface
  ├─ Clear performAs() method
  ├─ Use built-in Interactions
  └─ Builder pattern if applicable
  
□ Tests:
  ├─ Feature files readable, clear tags
  ├─ Test data from Examples/CSV
  ├─ Hooks well-defined
  └─ No @Ignore without comment
  
□ Quality:
  ├─ No duplication, clear naming
  ├─ No hardcode, no magic values
  ├─ No security concerns
  ├─ Tests pass locally + CI
  └─ No dead code
```

---

## 📝 Review Feedback Format

```
## Issue: [Category]
**Location:** file.java:lineNumber
**Severity:** Critical / High / Medium / Low

**Current:**
```java
// current code
```

**Suggested:**
```java
// suggested fix
```

**Why:** Explanation of issue
```

**Example:**
```
## Issue: Magic Sleep Time
**Location:** LoginSteps.java:15
**Severity:** High

**Current:**
Thread.sleep(3000);
element.click();

**Suggested:**
element.waitUntilClickable().click();

**Why:** Hard-coded sleep makes tests flaky. Use explicit wait for reliability.
```

---

## 🏷️ Tags

- `code-review`
- `quality`
- `validation`
- `serenity`

