# CLAUDE.md — Hướng dẫn cho Claude Code

## Tổng quan Project

**SerenityProject** là project giáo trình/demo học Serenity BDD dành cho người mới bắt đầu.  
Ngôn ngữ: Java 17 | Build tool: Maven | Framework: Serenity BDD + Cucumber + JUnit 5.

## Mục đích

Project này được dùng để:
1. Học và thực hành Serenity BDD từ cơ bản đến nâng cao
2. Làm demo/ví dụ minh họa cho giáo trình trong `README.md`
3. Thực hành bài tập ở Module 12 của giáo trình

## Cấu trúc Project (khi đầy đủ)

```
SerenityPrject/
├── pom.xml                              ← Maven config + dependencies
├── README.md                            ← Giáo trình dạng slide
├── CLAUDE.md                            ← File này
├── PROJECT_PROGRESS.md                  ← Bảng tiến độ dự án (modules, code samples, bài tập)
└── src/
    ├── main/java/                        ← (trống, project test only)
    └── test/
        ├── java/com/example/
        │   ├── runners/                  ← CucumberTestRunner
        │   ├── stepdefinitions/          ← Step Definitions (Cucumber)
        │   ├── pages/                    ← Page Objects (Serenity)
        │   ├── actions/                  ← Action Classes (@Steps/@Step)
        │   └── utils/                    ← Helper classes (DatabaseHelper, OpenCartApiHelper)
        └── resources/
            ├── serenity.conf             ← Serenity configuration
            ├── features/                 ← Gherkin feature files
            └── testdata/                 ← CSV, JSON test data files
```

## Các lệnh thường dùng

```bash
# Chạy tất cả test
mvn verify

# Chạy theo tag
mvn verify -Dcucumber.filter.tags="@smoke"

# Chạy trên môi trường staging
mvn verify -Denvironment=staging

# Chạy headless (không mở browser)
mvn verify -Dwebdriver.chrome.switches="--headless=new"

# Xem report (sau khi chạy)
# Windows: start target/site/serenity/index.html
# Mac/Linux: open target/site/serenity/index.html

# Clean build
mvn clean verify
```

## Stack kỹ thuật

| Thành phần | Version | Mục đích |
|-----------|---------|---------|
| Java | 17 | Ngôn ngữ lập trình |
| Maven | 3.8+ | Build tool |
| Serenity BDD | 5.3.8 | Test framework chính |
| Cucumber | 7.34.2 | BDD / Feature files |
| JUnit | 6.0.3 | Test runner |
| Selenium | (bundled) | UI automation |
| REST Assured | (bundled) | API testing |
| MySQL JDBC | 8.0.33 | Kết nối Database (Module 12) |

## Conventions

### Đặt tên file
- Feature files: `src/test/resources/features/<domain>/<feature_name>.feature`
- Page Objects: `src/test/java/com/example/pages/<PageName>Page.java`
- Step Definitions: `src/test/java/com/example/stepdefinitions/<Domain>Steps.java`
- Actions (Screenplay): `src/test/java/com/example/actions/<ActionName>.java`

### Tags chuẩn
| Tag | Ý nghĩa |
|-----|---------|
| `@smoke` | Test smoke (chạy nhanh, test cơ bản) |
| `@regression` | Test hồi quy đầy đủ |
| `@ui` | Test giao diện web |
| `@api` | Test API |
| `@wip` | Work in progress — chưa hoàn thiện |
| `@skip` | Bỏ qua — không chạy |
| `@debug` | Dùng khi debug thủ công |
| `@integration` | Test tích hợp nhiều layer (Web + API + DB) |
| `@e2e` | End-to-end flow test |
| `@db` | Test có truy vấn Database |
| `@cleanup` | Hook sẽ tự dọn dẹp test data trong DB sau khi chạy |

### Nguyên tắc code
- Step Definition **không gọi Page Object trực tiếp** — phải qua Action class
- Action class chứa `@Step` methods; inject vào Step Def qua `@Steps` field
- Flow chuẩn: `Cucumber step → @Steps ActionClass → @Step method → PageObject`
- Page Object extend `PageObject`, dùng `@FindBy` + `WebElementFacade`
- Không dùng `Thread.sleep()` — dùng `waitUntilVisible()` hoặc `withTimeoutOf()`
- Test data không hardcode trong step — đọc từ `testdata/` hoặc `Examples:`

### @Steps / @Step Pattern
```java
// 1. Action class — chứa business steps, inject PageObject tự động
public class LoginAction {
    LoginPage loginPage;                       // Serenity tự inject

    @Step("Enter username '{0}'")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }
}

// 2. Step Definition — inject Action qua @Steps
public class LoginSteps {
    @Steps LoginAction loginAction;            // Serenity tạo proxy

    @When("enter username {string}")
    public void enterUsername(String username) {
        loginAction.enterUsername(username);   // @Step được ghi vào report
    }
}
```
- Import: `net.serenitybdd.annotations.Steps` và `net.serenitybdd.annotations.Step`
- `'{0}'` trong `@Step` = placeholder cho tham số đầu tiên (hiển thị trong report)
- **Không dùng `new ActionClass()`** — phải dùng `@Steps` để Serenity tạo proxy

## Môi trường

Cấu hình trong `src/test/resources/serenity.conf`:
- `default`: môi trường local / dev
- `staging`: môi trường staging
- `production`: môi trường production

Chọn môi trường khi chạy: `mvn verify -Denvironment=staging`

## Báo cáo test

Sau khi chạy `mvn verify`, report được generate tại:
```
target/site/serenity/index.html
```

## Lưu ý khi làm việc với project này

- Project đang ở giai đoạn học tập — code ví dụ trong README.md là tham khảo
- Khi tạo code mới, đặt đúng package `com.example.*`
- Luôn cập nhật `PROJECT_PROGRESS.md` khi hoàn thiện module/code sample/bài tập mới
- Khi thêm dependency mới vào `pom.xml`, ghi rõ version cụ thể
- Dùng `.claude/skills/` để định nghĩa workflow cho từng loại task (create-test, fix-fail, refactor, review-code, v.v.)
