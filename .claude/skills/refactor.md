# ♻️ refactor

**Mô tả:** Refactor code, cải thiện cấu trúc test, và optimize page objects và actions.

---

## 🎯 Khi nào sử dụng?

- Consolidate duplicate selectors
- Extract common actions thành method/task riêng
- Move hardcode data → CSV/JSON test data files
- Tách logic phức tạp thành action riêng
- Clean up dead code
- Cải thiện naming clarity
- Optimize performance

---

## 🔑 Keywords

- `refactor`
- `improve code`
- `optimize`
- `extract`
- `consolidate`
- `clean up`
- `simplify`

---

## 📋 Hướng dẫn chi tiết

### 1. **Page Object Refactoring**

#### **A. Consolidate Duplicate Locators**

**Before:**
```java
@FindBy(css = "button.login")
private WebElement loginBtn;

@FindBy(css = "button.login")  // ← Duplicate!
private WebElement submitBtn;

@FindBy(id = "login-button")  // ← Same element, different locator
private WebElement loginButton;
```

**After:**
```java
@FindBy(css = "button.login")
private WebElement loginButton;
```

**Process:**
1. Identify duplicate locators
2. Choose one best/most stable locator
3. Replace all @FindBy references
4. Update method names consistently
5. Run tests - confirm all pass

---

#### **B. Extract Common Interaction Methods**

**Before:**
```java
public void fillEmailAndPassword(String email, String password) {
  emailField.clear();
  emailField.sendKeys(email);
  passwordField.clear();
  passwordField.sendKeys(password);
  submitButton.click();
}

public void fillEmailAndPasswordAndSubmit(String email, String password) {
  emailField.clear();
  emailField.sendKeys(email);
  passwordField.clear();
  passwordField.sendKeys(password);
  submitButton.click();
}
```

**After:**
```java
public void fillEmail(String email) {
  emailField.clear();
  emailField.sendKeys(email);
}

public void fillPassword(String password) {
  passwordField.clear();
  passwordField.sendKeys(password);
}

public void submitForm() {
  submitButton.click();
}

// Reuse methods
public void loginWith(String email, String password) {
  fillEmail(email);
  fillPassword(password);
  submitForm();
}
```

---

#### **C. Remove Unused Methods**

**Identify:**
- IDE: Right-click method → "Analyze" → "Run Inspection"
- Or: Search usages (Ctrl+Alt+F7)

**Verify before delete:**
- [ ] Method not used in other files
- [ ] Method not used in tests
- [ ] Method not public API (step definitions don't call it)

**Delete safely:**
```java
// ❌ Don't just delete without checking
// private void unusedMethod() { ... }

// ✓ Check usages, then delete
// (after confirming no usages)
```

---

### 2. **Step Definition Refactoring**

#### **A. Extract Common Setup**

**Before:**
```java
@Given("user opens login page")
public void step1() {
  loginPage.open();
  loginPage.waitForPageLoad();
}

@Given("user opens product page")
public void step2() {
  productPage.open();
  productPage.waitForPageLoad();
}
```

**After:**
```java
@Given("user opens login page")
public void userOpenLoginPage() {
  openPage(loginPage);
}

@Given("user opens product page")
public void userOpenProductPage() {
  openPage(productPage);
}

private void openPage(BasePage page) {
  page.open();
  page.waitForPageLoad();
}
```

---

#### **B. Move Complex Logic to Action**

**Before:**
```java
@When("user login with valid credentials")
public void userLogin() {
  loginPage.fillEmail("test@example.com");
  loginPage.fillPassword("password123");
  loginPage.waitUntilLoadingDone();
  loginPage.clickSubmit();
  loginPage.waitForPageLoad();
}
```

**After:**
```java
// Step definition - simple, readable
@When("user login with valid credentials")
public void userLogin() {
  actor.attemptsTo(LoginWithValidCredentials.credentials());
}

// Action - complex logic encapsulated
public class LoginWithValidCredentials implements Task {
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
      Enter.theValue("test@example.com").into(...),
      Enter.theValue("password123").into(...),
      Wait.forLoadingToFinish(),
      Click.on(...),
      Wait.forPageLoad()
    );
  }
}
```

---

### 3. **Test Data Refactoring**

#### **A. Move Hardcode Data to File**

**Before:**
```java
@When("user login with {string} and {string}")
public void userLogin(String email, String password) {
  // Hardcoded in feature file
  // Scenario:
  //   When user login with "test@example.com" and "password123"
}
```

**After:**

Feature file:
```gherkin
Scenario Outline: Login with different credentials
  When user login with <email> and <password>
  
  Examples:
    | email | password |
    | test@example.com | password123 |
    | admin@example.com | admin123 |
```

Or from CSV:
```java
@CsvSource({
  "test@example.com,password123",
  "admin@example.com,admin123"
})
```

---

#### **B. Consolidate Duplicate Test Data**

**Before:**
```
testdata/login_valid.csv
testdata/login_valid_data.csv
testdata/login_pass.csv

// All contain same data structure
```

**After:**
```
testdata/login.csv
// All valid/invalid scenarios in one file

email,password,expected
test@example.com,password123,success
invalid@example.com,wrongpass,error
```

---

### 4. **Safe Refactoring Practice**

#### **Golden Rule: Test First!**

```
1. ✓ RUN TESTS
   └─ mvn verify → all pass

2. REFACTOR (small steps)
   ├─ Change 1 thing
   ├─ Run tests after each change
   └─ Confirm still pass

3. ✓ VERIFY AGAIN
   └─ mvn verify → all pass
```

#### **Do NOT Mix Refactor + Bug Fix**

**Bad:**
```
Commit: "Refactor Page Object + Fix login bug"
```

**Good:**
```
Commit 1: "Fix login bug"
Commit 2: "Refactor Page Object (extract methods)"
```

---

#### **Use IDE Safely**

**Rename with IDE:** (Safe - auto-updates references)
```
Right-click name → Refactor → Rename
→ IDE updates all usages
```

**Extract Method with IDE:** (Safe - auto-creates method)
```
Select code → Refactor → Extract Method
→ IDE creates method + replaces calls
```

**Manual rename:** (Risky - might miss some usages)
```
❌ Don't use find-replace for class/method names
✓ Use IDE refactoring tools
```

---

### 5. **Code Quality Improvements**

#### **A. Naming Clarity**

**Before:**
```java
public void do_it() { ... }
public void exec() { ... }
public void run_test() { ... }
```

**After:**
```java
public void loginWithValidCredentials() { ... }
public void verifyLoginSuccess() { ... }
public void validateErrorMessage(String message) { ... }
```

---

#### **B. Remove Magic Strings/Numbers**

**Before:**
```java
public void login(String email, String password) {
  waitUntilVisible(Duration.ofSeconds(5));  // ← Magic number
  emailField.sendKeys(email);
}
```

**After:**
```java
private static final int DEFAULT_WAIT_SECONDS = 5;
private static final String DEFAULT_TEST_EMAIL = "test@example.com";

public void login(String email, String password) {
  waitUntilVisible(Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
  emailField.sendKeys(email);
}
```

---

#### **C. Remove Dead Code**

**Before:**
```java
public void oldLoginMethod() {  // ← Not used anymore
  // old implementation
}

public void newLoginMethod() {
  // new implementation
}
```

**After:**
```java
public void login() {
  // current implementation
}
// oldLoginMethod removed
```

**How to identify:**
```
IDE → Right-click method → Analyze → Run Inspection
→ Find "Unused" code
```

---

### 6. **Refactoring by Component**

#### **Page Object Refactoring Checklist**

- [ ] Consolidate duplicate @FindBy locators
- [ ] Extract common interaction patterns
- [ ] Remove unused methods
- [ ] Improve method naming
- [ ] Add missing javadoc for public methods
- [ ] Test all interactions still work

#### **Step Definition Refactoring Checklist**

- [ ] Extract common setup to helper method
- [ ] Move complex logic to Action/Task
- [ ] Group related steps by domain
- [ ] Consistent parameter naming
- [ ] Remove hardcoded values

#### **Action/Task Refactoring Checklist**

- [ ] Split large task into smaller tasks
- [ ] Use builder pattern for configuration
- [ ] Extract repeated interactions
- [ ] Improve naming clarity
- [ ] Add inline comments for complex logic

#### **Test Data Refactoring Checklist**

- [ ] Move hardcode data to CSV/JSON
- [ ] Consolidate duplicate test data files
- [ ] Extract constants for common values
- [ ] Verify data format valid
- [ ] Update test to use file-based data

---

## ✅ Refactoring Checklist

Before commit:
- [ ] All tests run locally and pass
- [ ] No functional change - refactor only
- [ ] Code more readable/maintainable
- [ ] No dead code remains
- [ ] Naming consistent and clear
- [ ] Run tests batch - no regression
- [ ] Commit message clear: "Refactor: [what]"

Commit message example:
```
Refactor: Consolidate login page locators

- Removed duplicate @FindBy for login button
- Extracted common fillFormAndSubmit method
- Renamed submit method to submitLoginForm
- All tests pass
```

---

## 🏷️ Tags

- `refactoring`
- `code-quality`
- `improvement`
- `serenity`

