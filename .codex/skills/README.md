# SerenityProject Skills Directory

Thư mục này chứa các skill định nghĩa sẵn cho Serenity BDD Project, giúp Claude Code tuân theo convention và best practice của dự án.

---

## 📚 Danh sách Skills

Các file `.md` để dễ đọc & chỉnh sửa.

| Skill | File | Mô tả |
|-------|------|-------|
| 📝 **update-doc** | [update-doc.md](./update-doc.md) | Cập nhật tài liệu: README, CLAUDE, PROJECT_PROGRESS |
| ✨ **create-test** | [create-test.md](./create-test.md) | Tạo test mới: feature, step, page object, action |
| 🔧 **fix-fail** | [fix-fail.md](./fix-fail.md) | Debug & fix test fail, flaky test, assertion issue |
| ♻️ **refactor** | [refactor.md](./refactor.md) | Refactor code, consolidate, improve structure |
| 👀 **review-code** | [review-code.md](./review-code.md) | Review test code quality & best practices |
| 📋 **review-doc** | [review-doc.md](./review-doc.md) | Review documentation clarity & accuracy |

---

## 🎯 Cách sử dụng

### Gọi skill khi request

```
<Nội dung request> — skill-name
```

**Ví dụ:**
```
Viết tutorial cho Module 5 — update-doc
Tạo feature test cho login — create-test
Fix lỗi timeout test — fix-fail
Consolidate selector duplikat — refactor
Review code quality — review-code
Review tài liệu module 3 — review-doc
```

### Hoặc tìm thông tin chi tiết

Mỗi skill file có:
- ✓ Mô tả ngắn gọn
- ✓ Khi nào sử dụng?
- ✓ Keywords trigger
- ✓ Hướng dẫn chi tiết (step-by-step)
- ✓ Checklist & best practices
- ✓ Ví dụ cụ thể

---

## 🔍 Quick Reference

### update-doc
Khi: Cập nhật README.md, CLAUDE.md, PROJECT_PROGRESS.md  
File: [update-doc.md](./update-doc.md)  
Keywords: `update doc`, `update readme`, `update tutorial`

### create-test
Khi: Tạo feature, step definition, page object, screenplay action  
File: [create-test.md](./create-test.md)  
Keywords: `create test`, `new feature`, `add step`

### fix-fail
Khi: Test fail, flaky test, timeout, assertion error  
File: [fix-fail.md](./fix-fail.md)  
Keywords: `fix fail`, `test fail`, `flaky`, `debug test`

### refactor
Khi: Consolidate code, extract method, clean up, improve  
File: [refactor.md](./refactor.md)  
Keywords: `refactor`, `improve code`, `optimize`, `consolidate`

### review-code
Khi: Review test code quality, convention, security  
File: [review-code.md](./review-code.md)  
Keywords: `review code`, `code review`, `check quality`

### review-doc
Khi: Review documentation, accuracy, clarity, completeness  
File: [review-doc.md](./review-doc.md)  
Keywords: `review doc`, `doc review`, `check tutorial`

---

## 💡 Best Practices

1. **Chỉ rõ skill** khi request → Claude hiểu context tốt hơn
2. **Đọc file skill** để xem hướng dẫn chi tiết trước khi làm
3. **Follow checklist** trong mỗi skill file
4. **Update PROJECT_PROGRESS.md** khi hoàn thiện task
5. **Run test** trước & sau refactor

---

## 📝 Thêm skill mới

Khi cần skill khác:

1. **Tạo file Markdown:**
   ```
   .claude/skills/skill-name.md
   ```

2. **Cấu trúc cơ bản:**
   ```markdown
   # Skill Name
   
   **Mô tả:** One-line description
   
   ## 🎯 Khi nào sử dụng?
   - Use case 1
   - Use case 2
   
   ## 🔑 Keywords
   - keyword1
   - keyword2
   
   ## 📋 Hướng dẫn chi tiết
   ...
   
   ## ✅ Checklist
   ...
   
   ## 🏷️ Tags
   - tag1
   - tag2
   ```

3. **Update README.md** (file này) thêm skill vào bảng

---

## 📋 Project Structure

```
.claude/skills/
├── README.md                ← File này
├── update-doc.md           ← Skill file
├── create-test.md
├── fix-fail.md
├── refactor.md
├── review-code.md
└── review-doc.md
```

---

**Last Updated:** 2026-05-08  
**Project:** SerenityProject (Serenity BDD Learning)  
**Status:** All 6 skills available in Markdown format ✓
