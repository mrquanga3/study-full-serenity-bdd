# 🔧 fix-fail

**Mô tả:** Fix test fail, debug flakiness, và troubleshoot các vấn đề liên quan tới test.

---

## 🎯 Khi nào sử dụng?

- Test fail - cần tìm root cause
- Test flaky - timeout hoặc element not found không ổn định
- Assertion fail - expected vs actual không match
- Test data issue - data không hợp lệ
- Environment issue - config không đúng

---

## 🔑 Keywords

- `fix fail`
- `test fail`
- `flaky`
- `debug test`
- `assertion fail`
- `timeout`
- `element not found`

---

## 📋 Hướng dẫn chi tiết

### 1. **Xác định Root Cause**

Khi test fail, kiểm tra theo thứ tự:

```
1. Đọc error message → Console log
2. Kiểm tra screenshot → Thấy UI hiện tại như thế nào?
3. Review test code → Logic có đúng không?
4. Kiểm tra test data → Data có hợp lệ?
5. Kiểm tra environment → URL, credentials, config?
6. Kiểm tra selector → @FindBy còn valid?
7. Kiểm tra wait strategy → Có timeout không?
```

**Ví dụ error messages:**
```
java.util.NoSuchElementException: Unable to locate element
→ Element locator sai hoặc element chưa load

org.openqa.selenium.TimeoutException
→ Element không xuất hiện trong timeout

AssertionError: Expected 'John', but got 'Jane'
→ Test data hoặc logic sai

StaleElementReferenceException
→ Element bị reload, cần refresh reference
```

---

### 2. **Loại Lỗi Thường Gặp & Cách Fix**

#### **A. Element Not Found / Timeout**

**Triệu chứng:**
```
NoSuchElementException
TimeoutException
ElementNotVisibleException
```

**Nguyên nhân phổ biến:**
- [ ] Element locator (@FindBy) sai sau UI change
- [ ] Element chưa load - cần wait
- [ ] Element bị ẩn sau modal/overlay
- [ ] Implicit wait quá ngắn

**Cách fix:**
```java
// ❌ Sai: Không wait
element.click();

// ✅ Đúng: Dùng explicit wait
element.waitUntilVisible().click();

// ✅ Đúng: Custom timeout
element.waitUntilVisible().withTimeoutOf(Duration.ofSeconds(10));

// ✅ Đúng: Chờ clickable (không chỉ visible)
element.waitUntilClickable().click();
```

**Debug tips:**
```java
// 1. Thêm screenshot
Serenity.takeScreenshot();

// 2. Thêm log
System.out.println("About to click element...");

// 3. Kiểm tra locator
browser.findElement(By.id("myId")).isDisplayed();

// 4. Thử XPath khác
// XPath: //button[contains(text(), 'Login')]
// CSS: button.login-btn
```

---

#### **B. Assertion Fail**

**Triệu chứng:**
```
AssertionError: Expected 'value1', but was 'value2'
```

**Nguyên nhân phổ biến:**
- [ ] Expected value sai
- [ ] Test data không match application behavior
- [ ] Element value chưa update (timing issue)
- [ ] Application bug (bukan test bug)

**Cách fix:**
```java
// ❌ Sai: Assertion không rõ ràng
assert actualValue.equals(expectedValue);

// ✅ Đúng: Có message mô tả
assertThat("User name should display", 
  actualValue, equalTo(expectedValue));

// ✅ Đúng: Wait trước assertion
element.waitUntilVisible();
assertThat("Success message shows", 
  element.getText(), equalTo("Success"));
```

**Debug tips:**
- Print actual value: `System.out.println("Actual: " + actualValue);`
- Verify test data: CSV/JSON có đúng không?
- Check application state: Database, API response match?

---

#### **C. Flaky Test (Không ổn định)**

**Triệu chứng:**
- Test sometimes pass, sometimes fail (without code change)
- Lỗi theo timing: pass khi chạy single, fail khi chạy batch

**Nguyên nhân phổ biến:**
- [ ] Race condition: element updated sau action
- [ ] Network issue: API response chậm
- [ ] Timing issue: không đủ time cho element load
- [ ] Async operation: JavaScript execution chưa hoàn tất
- [ ] Database state: test data chưa ready

**Cách fix:**

```java
// ❌ Sai: Hardcode sleep
Thread.sleep(3000);
element.click();

// ✅ Đúng: Explicit wait
element.waitUntilClickable().click();

// ✅ Đúng: Wait for condition
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOf(element));

// ✅ Đúng: Polling wait
element.waitUntilPresent().withTimeoutOf(Duration.ofSeconds(10));
```

**Flaky prevention:**
- Luôn dùng explicit wait, không Thread.sleep()
- Increase timeout nếu test runs on slow CI
- Kiểm tra async operation (AJAX, JavaScript)
- Isolate test data - không share state giữa tests

---

#### **D. Test Data Issue**

**Triệu chứng:**
```
FileNotFoundException: testdata/invalid.csv
NullPointerException: CSV missing column
```

**Nguyên nhân phổ biến:**
- [ ] File path sai
- [ ] CSV/JSON format invalid
- [ ] Missing required field
- [ ] Data không match application validation

**Cách fix:**

```java
// ❌ Sai: Hardcode data
String email = "test@example.com";

// ✅ Đúng: Load từ file
@CsvSource({
  "valid@test.com,password123,success",
  "invalid@test.com,wrongpass,error"
})
void testLogin(String email, String password, String expected) { }

// ✅ Đúng: Load từ JSON
ObjectMapper mapper = new ObjectMapper();
TestData data = mapper.readValue(new File("testdata/login.json"), TestData.class);
```

**Verify data:**
- [ ] CSV/JSON syntax valid (use online validator)
- [ ] All required fields present
- [ ] Data format match application expectation (phone, email format)
- [ ] No extra spaces/special characters

---

### 3. **Systematic Debugging Process**

```
Step 1: RUN & REPRODUCE
├─ Run test local, thấy error
├─ Check console output, screenshot, report
└─ Verify error consistent (repro được)

Step 2: ISOLATE PROBLEM
├─ Loại trừ environment (local vs CI)
├─ Loại trừ test data
├─ Loại trừ test code vs application code
└─ Narrow down tới specific step

Step 3: ANALYZE ROOT CAUSE
├─ Check locator valid?
├─ Check element timing?
├─ Check test data valid?
├─ Check application behavior?
└─ Check configuration?

Step 4: FIX & VERIFY
├─ Apply fix (selector, wait, data, config)
├─ Run test single - confirm pass
├─ Run test batch - confirm pass
└─ Check no regression (other tests pass)
```

---

### 4. **Debugging Tools & Techniques**

**Serenity Tools:**
```java
// Take screenshot
Serenity.takeScreenshot();

// Log info
Serenity.recordReportData().withTitle("Debug Info").andContents("value");

// Get driver
WebDriver driver = Serenity.getDriver();

// Check element
System.out.println(element.getText());
System.out.println(element.isVisible());
```

**Browser DevTools:**
- F12 → Elements → Inspect selector
- Console → Test XPath: `$x("//button[text()='Login']")`
- Network → Check API calls, response time

**IDE Debugging:**
- Set breakpoint → Step through code
- Watch variable → Check value at each step
- Debug mode → Run test with breakpoint

---

### 5. **Serenity Best Practice**

**For Reliability:**
- ✓ Dùng `@Screenshots(onFailure = true)` capture screen on fail
- ✓ Dùng `@Retry(count = 2)` retry flaky test
- ✓ Increase implicit wait nếu needed
- ✓ Handle dynamic waits, không hardcode

**For Maintainability:**
- ✓ Clear error messages - giúp debug nhanh
- ✓ Consistent Page Object methods - giúp reuse
- ✓ Centralized locators - dễ update khi UI change
- ✓ Documented test data - clear expectation

---

## ✅ Checklist khi fix test fail

- [ ] Đã reproduce được lỗi locally
- [ ] Đã xác định root cause (locator? timing? data?)
- [ ] Đã fix issue (selector, wait, data, config)
- [ ] Đã run test single - confirm pass
- [ ] Đã run test batch - confirm no regression
- [ ] Đã update code comment nếu fix cách khác thường
- [ ] Đã update PROJECT_PROGRESS.md ghi lại issue & fix

---

## 🏷️ Tags

- `debugging`
- `fix`
- `troubleshooting`
- `serenity`

