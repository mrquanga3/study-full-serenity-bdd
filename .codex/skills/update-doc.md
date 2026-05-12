# 📝 update-doc

**Mô tả:** Cập nhật tài liệu, README, CLAUDE.md, và các hướng dẫn.

---

## 🎯 Khi nào sử dụng?

- Cập nhật giáo trình (README.md)
- Cập nhật hướng dẫn project (CLAUDE.md)
- Thêm/update bảng theo dõi tiến độ (PROJECT_PROGRESS.md)
- Viết hướng dẫn học tập mới
- Cập nhật documentation

---

## 🔑 Keywords

- `update doc`
- `update readme`
- `update tutorial`
- `update guide`
- `edit doc`

---

## 📋 Hướng dẫn chi tiết

### 1. **README.md (Giáo trình)**

Khi cập nhật README.md:

- ✓ Giữ cấu trúc slide format
- ✓ Khi thêm module mới, đánh số thứ tự rõ ràng
- ✓ Cập nhật link tới code ví dụ (nếu có)
- ✓ Thêm "Bài tập" section ở cuối mỗi module
- ✓ Mỗi module có: Learning objectives, Key concepts, Code examples, Exercises
- ✓ Code example phải chạy được (test chắc chắn pass)
- ✓ Link hình ảnh: dùng path tương đối

### 2. **CLAUDE.md (Hướng dẫn dự án)**

Khi cập nhật CLAUDE.md:

- ✓ Cập nhật Cấu trúc Project nếu thêm folder/file mới
- ✓ Cập nhật lệnh thường dùng nếu thêm scenario mới
- ✓ Cập nhật Convention nếu thay đổi naming pattern
- ✓ Cập nhật Stack kỹ thuật nếu thêm dependency
- ✓ Ghi chú phải rõ ràng, hữu ích cho người phát triển tiếp theo

### 3. **PROJECT_PROGRESS.md (Bảng theo dõi tiến độ)**

Khi cập nhật PROJECT_PROGRESS.md:

- ✓ Ghi lại module/feature/bài tập mới được triển khai
- ✓ Cập nhật status: `[_]` Chưa bắt đầu | `[~]` Đang phát triển | `[x]` Đã hoàn thiện
- ✓ Ghi ngày cập nhật
- ✓ Thêm ghi chú nếu có issue/blockers

### 4. **Quy chuẩn viết tài liệu**

**Ngôn ngữ:**
- Dùng tiếng Việt cho nội dung giáo trình
- Naming English, giải thích tiếng Việt
- Chuẩn chính tả, ngữ pháp rõ ràng

**Formatting:**
- Heading: H1 (#), H2 (##), H3 (###)
- Code block: ` ```java ` với language tag
- List: `-` hoặc `*` với consistent
- Table: align rõ ràng, header bold

**Ví dụ (Examples):**
- Phải là working code
- Có comment giải thích
- Test được chạy, confirm pass trước commit

**Link:**
- Link ngoài: `[Text](https://example.com)`
- Link nội: `[Text](./path/to/file.md)`
- Verify link không broken

### 5. **Cập nhật file nào → Update những file liên quan**

Nếu cập nhật | Cần update thêm
---|---
README.md (thêm module) | PROJECT_PROGRESS.md + CLAUDE.md
CLAUDE.md (convention) | README.md examples + CODE comments
PROJECT_PROGRESS.md | Changelog section
Code sample | README.md + inline comments

---

## ✅ Checklist trước khi commit

- [ ] Content đầy đủ, rõ ràng
- [ ] Spelling & grammar đúng (tiếng Việt)
- [ ] Code example chạy được, test pass
- [ ] Link valid, path correct
- [ ] Formatting consistent, dễ đọc
- [ ] Cập nhật file liên quan nếu cần
- [ ] Ghi lại changelog/commit message

---

## 🏷️ Tags

- `documentation`
- `tutorial`
- `giáo-trình`

