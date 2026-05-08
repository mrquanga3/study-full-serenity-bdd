# PROJECT_PROGRESS.md — Tiến độ Hoàn thiện Course Serenity BDD

> Bảng theo dõi tiến độ phát triển giáo trình & code samples.  
> Cập nhật: `[_]` Chưa bắt đầu | `[~]` Đang phát triển | `[x]` Đã hoàn thiện

---

## 📚 Module Progress

| # | Chủ đề | Status | README.md | Code Sample | Bài Tập | Ngày cập nhật | Ghi chú |
|---|--------|--------|-----------|-------------|---------|---------------|---------|
| 1 | BDD & Serenity Fundamentals | `[_]` | - | - | - | | |
| 2 | Setup & Configuration | `[_]` | - | - | - | | |
| 3 | Gherkin & Feature Files | `[_]` | - | - | - | | |
| 4 | Test Runner (CucumberTestRunner) | `[_]` | - | - | - | | |
| 5 | Step Definitions | `[_]` | - | - | - | | |
| 6 | Page Object Pattern | `[_]` | - | - | - | | |
| 7 | UI Testing nâng cao | `[_]` | - | - | - | | |
| 8 | API Testing (REST Assured) | `[_]` | - | - | - | | |
| 9 | Screenplay Pattern | `[_]` | - | - | - | | |
| 10 | Serenity Reports | `[_]` | - | - | - | | |
| 11 | Best Practices & CI/CD | `[_]` | - | - | - | | |

---

## 🎯 Checklist Hoàn thiện Project

### Nội dung Giáo trình
- `[ ]` Module 1-11 README đầy đủ (slides format)
- `[ ]` Mỗi module có learning objectives rõ ràng
- `[ ]` Mỗi module có key concepts + diagrams
- `[ ]` Mỗi module có code example + explanation
- `[ ]` Mỗi module có common mistakes section
- `[ ]` Mỗi module có bài tập thực hành với requirements

### Code Samples & Minh họa
- `[ ]` Module 1: BDD concept diagram + comparison chart
- `[ ]` Module 2: Project setup script (init project từ đầu)
- `[ ]` Module 3: Feature file samples (cơ bản + advanced)
- `[ ]` Module 4: Working CucumberTestRunner example
- `[ ]` Module 5: Step definition patterns + best practice examples
- `[ ]` Module 6: Page Object library (Login, Dashboard, Product pages)
- `[ ]` Module 7: Advanced UI actions (dropdown, upload, scroll, iframe, alert)
- `[ ]` Module 8: API test samples (GET, POST, PUT, DELETE, auth)
- `[ ]` Module 9: Screenplay pattern tasks + actors
- `[ ]` Module 10: Serenity report configuration
- `[ ]` Module 11: CI/CD GitHub Actions workflow

### Bài Tập Thực Hành
- `[ ]` Bài 1: Google Search (module 3-6)
  - `[ ]` Feature file
  - `[ ]` Step definitions
  - `[ ]` Page object
  - `[ ]` Chạy được, pass
  
- `[ ]` Bài 2: JSONPlaceholder API (module 8)
  - `[ ]` Feature file
  - `[ ]` Step definitions
  - `[ ]` API helper
  - `[ ]` Chạy được, pass

- `[ ]` Bài 3: SauceDemo E-commerce (module 1-11 tổng hợp)
  - `[ ]` Full feature flows
  - `[ ]` Page objects (Login, Product, Cart, Checkout)
  - `[ ]` Both UI + API tests
  - `[ ]` Screenplay pattern
  - `[ ]` Chạy được, pass

### Documentation & Configuration
- `[ ]` README.md: Giáo trình hoàn chỉnh (tất cả 11 modules)
- `[ ]` CLAUDE.md: Updated conventions & structure
- `[ ]` pom.xml: Dependencies & plugins chính xác
- `[ ]` serenity.conf: Cấu hình hoàn chỉnh
- `[ ]` .gitignore: Bỏ qua target/, reports/

### Quality & Testing
- `[ ]` Tất cả code samples chạy được (mvn verify pass)
- `[ ]` Serenity report generate đúng
- `[ ]` README code examples syntax highlighting đúng
- `[ ]` Link trong README tới code samples hoạt động
- `[ ]` Tất cả bài tập có passing tests

### CI/CD & Deployment
- `[ ]` GitHub Actions workflow setup
- `[ ]` Tests run on CI pass
- `[ ]` Report upload to artifact (nếu cần)
- `[ ]` Branch protection rules

---

## 📋 Nội dung Chi tiết từng Module

### Module 1: BDD & Serenity Fundamentals

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] What is BDD?
- [ ] BDD vs Manual Testing comparison
- [ ] Three Amigos (BA, Dev, QA)
- [ ] Serenity BDD architecture diagram
- [ ] Serenity vs Plain Selenium
- [ ] Advantages of Serenity

**Code Samples:**
- [ ] Concept diagram (BDD cycle)
- [ ] Architecture diagram
- [ ] Comparison table

**Exercise:**
- [ ] N/A (Concept learning only)

---

### Module 2: Setup & Configuration

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] Prerequisites (Java 17, Maven, IDE)
- [ ] Step-by-step Maven project setup
- [ ] pom.xml configuration explained
- [ ] serenity.conf explained
- [ ] Directory structure
- [ ] First test run

**Code Samples:**
- [ ] pom.xml template
- [ ] serenity.conf template
- [ ] Project structure screenshot

**Exercise:**
- [ ] Setup project from scratch
- [ ] Run `mvn verify` successfully

---

### Module 3: Gherkin & Feature Files

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] Gherkin syntax (Given-When-Then)
- [ ] Feature, Scenario, Steps
- [ ] Background, Scenario Outline
- [ ] DataTables, DocStrings
- [ ] Tags and filtering

**Code Samples:**
- [ ] login.feature (basic)
- [ ] product_search.feature (advanced with Scenario Outline)
- [ ] Examples with DataTable

**Exercise:**
- [ ] Write feature files for Google Search

---

### Module 4: Test Runner

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] JUnit 5 + Cucumber setup
- [ ] CucumberTestRunner annotations
- [ ] Running tests from IDE
- [ ] Running tests from CLI

**Code Samples:**
- [ ] CucumberTestRunner.java complete
- [ ] pom.xml with junit-platform-suite

**Exercise:**
- [ ] Create working test runner

---

### Module 5: Step Definitions

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] Step annotation (@Given, @When, @Then)
- [ ] Cucumber Expressions
- [ ] Parameter types
- [ ] Hooks (@Before, @After)
- [ ] Tag-specific hooks

**Code Samples:**
- [ ] LoginSteps.java with examples
- [ ] Hooks with setup/teardown
- [ ] DataTable handling

**Exercise:**
- [ ] Write steps for Google Search

---

### Module 6: Page Object Pattern

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] PageObject base class
- [ ] @FindBy locators
- [ ] WebElementFacade
- [ ] Serenity assertions
- [ ] Serenity waits
- [ ] Page components (reusable)

**Code Samples:**
- [ ] LoginPage.java
- [ ] DashboardPage.java
- [ ] NavbarComponent.java (reusable)
- [ ] Best practices

**Exercise:**
- [ ] Create Page Objects for Google Search

---

### Module 7: UI Testing Advanced

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] WebDriver configuration
- [ ] Dropdown/Select handling
- [ ] File upload
- [ ] Scroll & JavaScript
- [ ] iframe & windows
- [ ] Alerts & popups
- [ ] @Step annotations

**Code Samples:**
- [ ] SerenityUtils helper class
- [ ] Advanced actions examples
- [ ] Dropdown handling example
- [ ] File upload example

**Exercise:**
- [ ] Extend Google Search with advanced interactions

---

### Module 8: API Testing (REST Assured)

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] REST Assured + Serenity integration
- [ ] GET, POST, PUT, DELETE, PATCH
- [ ] Request/Response handling
- [ ] Assertions with Hamcrest
- [ ] Authentication (Basic, Bearer, OAuth)
- [ ] Token management

**Code Samples:**
- [ ] ApiHelper.java with CRUD examples
- [ ] Auth examples (Basic, Bearer, API Key)
- [ ] Response parsing (jsonPath)

**Exercise:**
- [ ] JSONPlaceholder API testing

---

### Module 9: Screenplay Pattern

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] Actor, Ability, Task concepts
- [ ] Performable vs Interaction
- [ ] Questions & verification
- [ ] Target (locators in Screenplay)
- [ ] Fluent API style

**Code Samples:**
- [ ] Actor setup with BrowseTheWeb
- [ ] Task implementation (LoginTask)
- [ ] Question implementation
- [ ] Step using Screenplay

**Exercise:**
- [ ] Refactor Google Search to Screenplay pattern

---

### Module 10: Serenity Reports

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] Serenity HTML report structure
- [ ] Understanding report sections
- [ ] Report configuration
- [ ] Screenshots configuration
- [ ] Custom report data

**Code Samples:**
- [ ] serenity.conf report settings
- [ ] Serenity.recordReportData() example
- [ ] Report screenshots

**Exercise:**
- [ ] View & analyze reports

---

### Module 11: Best Practices & CI/CD

**Status:** `[_]` Chưa bắt đầu

**README Content:**
- [ ] Test organization & naming
- [ ] Page Object best practices
- [ ] Flaky test prevention
- [ ] Test data management
- [ ] Parallel execution
- [ ] CI/CD pipeline (GitHub Actions)
- [ ] Debugging failed tests

**Code Samples:**
- [ ] GitHub Actions workflow
- [ ] Parallel execution config
- [ ] Test data structure
- [ ] Logging strategy

**Exercise:**
- [ ] Setup CI/CD pipeline

---

## 📅 Changelog

| Ngày | Thay đổi |
|------|----------|
| 2026-05-08 | Tạo PROJECT_PROGRESS.md (từ SKILL.md cũ) |
| | Định nghĩa checklist hoàn thiện project |
| | Phân chia chi tiết theo 11 modules |

---

## 📝 Ghi Chú

### Tiêu chuẩn "Đã hoàn thiện"
- README module: có full content + examples + exercise
- Code sample: chạy được, test pass, có comment
- Bài tập: có requirements rõ ràng, có setup/teardown, tất cả tests pass

### Người phát triển
- **Project Lead:** Quang, NGUYEN DANG
- **Email:** dangquangk53a3@gmail.com

---

*Last Updated: 2026-05-08*
