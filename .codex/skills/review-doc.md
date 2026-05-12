# 📋 review-doc

**Mô tả:** Review tài liệu, tutorials, hướng dẫn - kiểm tra clarity, accuracy, completeness.

---

## 🎯 Khi nào sử dụng?

- Review README.md (giáo trình)
- Kiểm tra CLAUDE.md (hướng dẫn project)
- Validate SKILL.md / PROJECT_PROGRESS.md
- Review tutorial/hướng dẫn học tập
- Audit documentation quality

---

## 🔑 Keywords

- `review doc`
- `doc review`
- `check tutorial`
- `validate guide`
- `review guide`

---

## 📋 Hướng dẫn chi tiết

### 1. **README.md (Giáo trình) Review**

#### **Content Completeness**
Mỗi module nên có:

- ✓ **Learning Objectives** (1-3 objectives)
  ```markdown
  ## Module X: Topic
  
  **Learning Objectives:**
  - Hiểu được [concept]
  - Có thể [action]
  - Biết cách [implement]
  ```

- ✓ **Key Concepts** (definitions, diagrams, comparisons)
  ```markdown
  ### Key Concepts
  - **Concept 1**: Definition
  - **Concept 2**: Definition
  ```

- ✓ **Code Examples** (working code snippets)
  ```markdown
  ### Example 1: Basic Usage
  ```java
  // code
  ```
  ```

- ✓ **Explanation** (why, how, when to use)
  ```markdown
  **Explanation:**
  This code does X because...
  ```

- ✓ **Exercise** (practice task)
  ```markdown
  **Exercise:**
  Your task: ...
  ```

#### **Code Example Quality**
```java
// ✓ Good: Complete, runnable, explained
/**
 * Example: Login with Serenity
 * Demonstrates: Page Object pattern, waits
 */
@Given("user is on login page")
public void openLoginPage() {
  loginPage.open();  // PageObject method
  loginPage.waitForPageLoad();  // Explicit wait
}

// ❌ Bad: Incomplete, no explanation
public void login() {
  // code fragment
}

// ❌ Bad: Wrong pattern
@Given("user login")
public void login() {
  driver.findElement(...).click();  // Direct Selenium!
}
```

#### **Link Verification**
- ✓ Code ref links: point to actual file in repo
- ✓ External links: verify not broken (404)
- ✓ Internal links: use relative paths `./path/to/file.md`
- ✓ Image links: path relative, images exist

**Check:**
```bash
# Broken link test
curl -I https://external-link.com  # Check 200 OK
cat ./file.md  # Verify local file exists
```

#### **Structure & Formatting**
```markdown
✓ Good: Clear hierarchy
# Module 1: Title
## Section
### Subsection
- List item

❌ Bad: Inconsistent
# Title
### Subsection (skip ##)
- list
  * nested

# Same level heading (ambiguous)
## Another section
```

**Checklist:**
- [ ] Headings: H1 (#) for module, H2 (##) for section, H3 (###) for subsection
- [ ] Lists: consistent bullets or numbers
- [ ] Code blocks: have language tag (`java`, `gherkin`, `bash`)
- [ ] Tables: aligned, header bold
- [ ] Emphasis: `*italic*` or `**bold**` used appropriately

---

### 2. **CLAUDE.md Review**

#### **Content Accuracy**
```markdown
✓ Project Overview: Correct, current
✓ Project Structure: Match actual folder structure
✓ Commands: Actually work when executed
✓ Conventions: Match code in repository
✓ Stack: Versions accurate (match pom.xml)
```

**How to verify:**
```bash
# Run commands locally
mvn verify  # Should work
mvn verify -Dcucumber.filter.tags="@smoke"  # Should work

# Check file structure
ls src/test/java/com/example/  # Should exist

# Verify versions
grep -A 5 "<artifactId>serenity-bdd</artifactId>" pom.xml
```

#### **Completeness**
- [ ] Project overview describes purpose clearly
- [ ] Setup instructions complete (prerequisites included)
- [ ] Project structure shows all main folders
- [ ] All commands documented (mvn verify, filter, environment)
- [ ] All conventions explained (naming, tags, structure)
- [ ] Stack kỹ thuật table current
- [ ] Environment sections documented
- [ ] Troubleshooting tips included

#### **Clarity**
```markdown
✓ Clear: Step-by-step instructions
   1. Install Java 17
   2. Install Maven
   3. Clone project
   4. Run mvn verify

❌ Unclear: Vague instructions
   "Setup project" (how?)
   "Run tests" (which command?)
```

---

### 3. **PROJECT_PROGRESS.md Review**

#### **Module Progress Table**
```markdown
✓ All 11 modules listed
✓ Status clear: [_] / [~] / [x]
✓ Each has: README, Code Sample, Exercise columns
✓ Date updated column filled
✓ Notes explain blockers/status
```

**Verification:**
- [ ] Count modules: should have 11 (or match plan)
- [ ] Status clear and consistent
- [ ] No empty rows
- [ ] Dates reasonable (not future or too old)

#### **Checklist Section**
- [ ] All 5 sections present (Content, Code Samples, Exercises, Docs, Quality)
- [ ] Items specific, not vague ("Tất cả X được Y" → be specific what X and Y)
- [ ] Checklist items actionable (not just "done")
- [ ] No typos or incomplete items

**Example:**
```markdown
❌ Bad: "Tất cả modules hoàn thiện"
✓ Good: "Module 1-11 README đầy đủ (slides format)"
```

---

### 4. **Content Accuracy Review**

#### **Code Examples Must Work**
```java
// ❌ Bad: This won't compile
@FindBy(id = "email)  // Missing closing quote!
private WebElement emailField;

// ✓ Good: This compiles and runs
@FindBy(id = "email")
private WebElement emailField;
```

**How to verify:**
```bash
# Copy code from docs
# Create test file
# Compile: javac TestFile.java
# If error → doc is wrong
```

#### **Commands Must Work**
```bash
# ❌ Bad: Won't work
mvn verfiy  # typo: should be "verify"

# ✓ Good: Works
mvn verify
```

**How to verify:**
```bash
# Run command locally
mvn verify
echo $?  # Should be 0 (success)
```

#### **Path References Must Match**
```markdown
❌ Bad: "File at src/main/java/..."
(but project is test-only, no main/)

✓ Good: "File at src/test/java/..."
(matches actual project structure)
```

---

### 5. **Language & Style Review**

#### **Language Quality (Tiếng Việt)**
- ✓ Spelling: đúng chính tả, không typo
- ✓ Grammar: ngữ pháp đúng
- ✓ Terminology: consistent (không "test" lúc tiếng Anh, lúc không dịch)
- ✓ Tone: professional, clear, not too casual

**Examples:**
```markdown
❌ Bad: "Cái này là để làm test cái feature"
✓ Good: "Phần này được sử dụng để kiểm thử feature"

❌ Bad: "tạo Feature files để testing"
✓ Good: "Tạo Feature files để kiểm thử"
```

#### **Consistency**
```markdown
✓ Terminology consistent:
  - "Page Object" (không "Page" hoặc "Page Obj")
  - "Step Definition" (không "Step" hoặc "Definition")
  - "Feature file" (không "Feature" hoặc "file feature")

✓ Formatting consistent:
  - Code variables: `variable` (backticks)
  - File names: `pom.xml` (backticks)
  - Class names: `LoginPage` (backticks)
  - Commands: `mvn verify` (backticks)
```

---

### 6. **Presentation Quality**

#### **Readability**
```markdown
✓ Good: Clear whitespace, not dense
# Module 1
**Mô tả:** ...

## Section 1
**Giải thích:** ...

❌ Bad: Dense, hard to read
# Module 1**Mô tả:** ... ## Section 1 **Giải thích:** ...
```

#### **Visual Aids**
- ✓ Diagrams: ASCII art or clear descriptions
- ✓ Tables: aligned, readable
- ✓ Code blocks: syntax highlighting, language tag
- ✓ Emphasis: `**bold**` for important, `*italic*` for emphasis

**Table example:**
```markdown
✓ Good: Aligned, clear header
| Column 1 | Column 2 |
|----------|----------|
| Value 1  | Value 2  |

❌ Bad: Misaligned
| Column 1 | Column 2 |
| Value 1 | Value 2 |
```

---

### 7. **Completeness Checklist**

For **README.md (Giáo trình):**
- [ ] All 11 modules have learning objectives
- [ ] Each module has key concepts section
- [ ] Each module has working code examples
- [ ] Each module has explanation of code
- [ ] Each module has exercise/practice task
- [ ] Code examples follow Serenity best practices
- [ ] Links to code sample files work
- [ ] All external links valid
- [ ] Images exist and render
- [ ] Language is clear, grammar correct

For **CLAUDE.md:**
- [ ] Project overview accurate
- [ ] Project structure matches reality
- [ ] Setup instructions complete
- [ ] All commands listed and work
- [ ] Conventions documented and match code
- [ ] Stack versions accurate (match pom.xml)
- [ ] Environment config explained
- [ ] Notes are helpful, current

For **PROJECT_PROGRESS.md:**
- [ ] All 11 modules listed
- [ ] Status column filled (not empty)
- [ ] Each module has README/Code/Exercise columns
- [ ] Checklist sections complete
- [ ] Module detail sections filled

For **General:**
- [ ] Spelling & grammar correct
- [ ] No outdated information
- [ ] Links work (internal & external)
- [ ] Terminology consistent
- [ ] Code examples work
- [ ] Commands work
- [ ] Formatting professional

---

## ✅ Documentation Review Checklist

When reviewing documentation:

```
□ Content
  ├─ Complete: has all sections needed
  ├─ Accurate: matches actual code/commands
  ├─ Current: no outdated info
  └─ Examples: code snippets work

□ Structure
  ├─ Headings: proper hierarchy
  ├─ Lists: consistent formatting
  ├─ Links: all working
  └─ Tables: aligned, readable

□ Language
  ├─ Spelling: no typos
  ├─ Grammar: correct Vietnamese
  ├─ Terminology: consistent
  └─ Tone: professional, clear

□ Accessibility
  ├─ Clear: easy to understand
  ├─ Logical: flows well
  ├─ Searchable: terms discoverable
  └─ Visual: uses formatting aids

□ Maintenance
  ├─ No outdated links
  ├─ No hardcoded versions (reference actual)
  ├─ No broken references
  └─ Changelog updated
```

---

## 📝 Review Feedback Format

```
## Issue: [Category]
**Severity:** Critical / High / Medium / Low
**Location:** Section name / Line number

**Problem:**
[Describe what's wrong]

**Current:**
[Quote the problematic text]

**Suggested:**
[Propose fix]

**Why:**
[Explain why it matters]
```

**Example:**
```
## Issue: Broken Code Example
**Severity:** High
**Location:** Module 6 / Page Object Review

**Problem:**
Code example has typo - won't compile

**Current:**
@FindBy(id = "email)  // Missing quote!

**Suggested:**
@FindBy(id = "email")

**Why:**
Readers will copy this code and get compilation error, hurting learning
```

---

## 🏷️ Tags

- `documentation-review`
- `quality-assurance`
- `tutorial`
- `giáo-trình`

