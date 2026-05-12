# OPENCART TEST CASES

**URL:** http://103.245.237.118:8081/opencart/administrator/  
**Phạm vi:** Trang Login — OpenCart Admin Panel  
**Tác giả:** Auto-generated  
**Ngày tạo:** 2026-05-12  

---

## Mục lục

| ID | Tên Test Case | Loại | Mức độ |
|----|--------------|------|--------|
| [AUTO-1](#auto-1) | Login thành công với credentials hợp lệ | Positive | Critical |
| [AUTO-2](#auto-2) | Login thất bại — password sai | Negative | High |
| [AUTO-3](#auto-3) | Login thất bại — username sai | Negative | High |
| [AUTO-4](#auto-4) | Login thất bại — cả username và password đều sai | Negative | High |
| [AUTO-5](#auto-5) | Login thất bại — username để trống | Negative | High |
| [AUTO-6](#auto-6) | Login thất bại — password để trống | Negative | High |
| [AUTO-7](#auto-7) | Login thất bại — cả 2 trường để trống | Negative | Medium |
| [AUTO-8](#auto-8) | Kiểm tra hiển thị đầy đủ các thành phần trang Login | UI | High |
| [AUTO-9](#auto-9) | Password field ẩn ký tự nhập | UI | Medium |
| [AUTO-10](#auto-10) | Chuyển ngôn ngữ giao diện sang Tiếng Việt | UI | Low |
| [AUTO-11](#auto-11) | Login thất bại — username chứa ký tự đặc biệt / SQL Injection | Security | Medium |
| [AUTO-12](#auto-12) | Kiểm tra không có cơ chế khóa tài khoản sau nhiều lần sai | Security | Medium |
| [AUTO-13](#auto-13) | Hover vào trường Username | UI | Low |
| [AUTO-14](#auto-14) | Hover vào trường Password | UI | Low |
| [AUTO-15](#auto-15) | Hover vào button Login | UI | Low |
| [AUTO-16](#auto-16) | Đăng nhập bằng phím Enter tại trường Password | UI | High |
| [AUTO-17](#auto-17) | Đăng nhập bằng phím Enter tại trường Username | UI | Medium |

---

## LOGIN PAGE TEST CASES

---

### AUTO-1

**Title:** Login thành công với credentials hợp lệ

**Mô tả:** Xác minh rằng admin có thể đăng nhập thành công khi nhập đúng username và password.

**Preconditions:**
- Trình duyệt đang mở tại URL: `http://103.245.237.118:8081/opencart/administrator/`
- Chưa đăng nhập (hiển thị trang Login)

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Xác nhận trang Login đang hiển thị (tiêu đề "Please enter your login details.")
3. Nhập `admin` vào trường **Username**
4. Nhập `admin` vào trường **Password**
5. Click button **Login**

**Expected Result:**
- Hệ thống redirect sang trang Dashboard
- URL chứa `route=common/dashboard&user_token=...`
- Tiêu đề trang (page title) là `Dashboard`
- Không có thông báo lỗi nào xuất hiện

---

### AUTO-2

**Title:** Login thất bại — password sai

**Mô tả:** Xác minh rằng hệ thống từ chối đăng nhập khi password không đúng với username hợp lệ.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Nhập `admin` vào trường **Username**
3. Nhập `wrongpassword` vào trường **Password**
4. Click button **Login**

**Expected Result:**
- Hệ thống **không** chuyển sang trang Dashboard
- Trang Login được hiển thị lại
- Xuất hiện thông báo lỗi: **"No match for Username and/or Password."**
- Trường Username và Password được reset (trống)

---

### AUTO-3

**Title:** Login thất bại — username sai

**Mô tả:** Xác minh rằng hệ thống từ chối đăng nhập khi username không tồn tại trong hệ thống.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Nhập `nonexistentuser` vào trường **Username**
3. Nhập `admin` vào trường **Password**
4. Click button **Login**

**Expected Result:**
- Hệ thống **không** chuyển sang trang Dashboard
- Trang Login được hiển thị lại
- Xuất hiện thông báo lỗi: **"No match for Username and/or Password."**
- Thông báo lỗi không tiết lộ cụ thể username hay password sai (bảo mật)

---

### AUTO-4

**Title:** Login thất bại — cả username và password đều sai

**Mô tả:** Xác minh rằng hệ thống từ chối đăng nhập khi cả username lẫn password đều không đúng.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Nhập `fakeuser` vào trường **Username**
3. Nhập `fakepassword` vào trường **Password**
4. Click button **Login**

**Expected Result:**
- Hệ thống **không** chuyển sang trang Dashboard
- Trang Login được hiển thị lại
- Xuất hiện thông báo lỗi: **"No match for Username and/or Password."**

---

### AUTO-5

**Title:** Login thất bại — username để trống

**Mô tả:** Xác minh rằng form validation ngăn submit khi trường Username bị bỏ trống.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Để trống trường **Username**
3. Nhập `admin` vào trường **Password**
4. Click button **Login**

**Expected Result:**
- Form **không** được submit lên server
- Trình duyệt hiển thị thông báo HTML5 validation trên trường Username: **"Please fill out this field."**
- Trang không reload, không có redirect
- Focus được đặt vào trường Username

---

### AUTO-6

**Title:** Login thất bại — password để trống

**Mô tả:** Xác minh rằng form validation ngăn submit khi trường Password bị bỏ trống.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Nhập `admin` vào trường **Username**
3. Để trống trường **Password**
4. Click button **Login**

**Expected Result:**
- Form **không** được submit lên server
- Trình duyệt hiển thị thông báo HTML5 validation trên trường Password: **"Please fill out this field."**
- Trang không reload, không có redirect
- Focus được đặt vào trường Password

---

### AUTO-7

**Title:** Login thất bại — cả 2 trường để trống

**Mô tả:** Xác minh rằng form validation ngăn submit khi cả Username và Password đều bị bỏ trống.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Để trống trường **Username**
3. Để trống trường **Password**
4. Click button **Login**

**Expected Result:**
- Form **không** được submit lên server
- Trình duyệt hiển thị thông báo HTML5 validation trên trường **Username** trước (field đầu tiên có `required`): **"Please fill out this field."**
- Trang không reload, không có redirect

---

### AUTO-8

**Title:** Kiểm tra hiển thị đầy đủ các thành phần trang Login

**Mô tả:** Xác minh rằng tất cả các thành phần UI trên trang Login hiển thị đúng và đầy đủ.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Quan sát toàn bộ giao diện trang Login

**Expected Result:**
- Page title (tab trình duyệt): `Administration`
- Logo OpenCart hiển thị ở header (góc trên bên trái)
- Card login có tiêu đề: `Please enter your login details.` (có icon khóa)
- Trường **Username** hiển thị với:
  - Label: "Username"
  - Icon user ở đầu input
  - Placeholder text: "Username"
- Trường **Password** hiển thị với:
  - Label: "Password"
  - Icon khóa ở đầu input
  - Placeholder text: "Password"
- Button **Login** màu xanh (primary) với icon chìa khóa, nằm góc phải dưới form
- Dropdown **Language** hiển thị ở góc phải trên form, mặc định là `English`
- Footer hiển thị: `OpenCart © 2009-2026 All Rights Reserved.`

---

### AUTO-9

**Title:** Password field ẩn ký tự nhập

**Mô tả:** Xác minh rằng các ký tự nhập vào trường Password được hiển thị dưới dạng ký tự ẩn (dấu chấm hoặc dấu hoa thị), không hiển thị dưới dạng plain text.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Click vào trường **Password**
3. Nhập chuỗi `testpassword123`
4. Quan sát nội dung hiển thị trong ô Password

**Expected Result:**
- Các ký tự trong trường Password **không** hiển thị dưới dạng chữ thường (plain text)
- Mỗi ký tự được hiển thị dưới dạng dấu chấm `●` hoặc dấu `*`
- Thuộc tính `type` của input là `password` (kiểm tra trong DevTools)

---

### AUTO-10

**Title:** Chuyển ngôn ngữ giao diện sang Tiếng Việt

**Mô tả:** Xác minh rằng dropdown Language hoạt động đúng, cho phép chuyển giao diện trang Login sang Tiếng Việt.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Giao diện đang hiển thị ngôn ngữ English (mặc định)

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Click vào dropdown **Language** (hiển thị cờ + "English") ở góc phải trên form
3. Trong danh sách dropdown, chọn **Tiếng Việt**
4. Quan sát giao diện sau khi chọn

**Expected Result:**
- Dropdown đóng lại
- Trang Login reload hoặc cập nhật ngôn ngữ
- Dropdown Language hiển thị cờ Việt Nam + `Tiếng Việt`
- Các nhãn (label) và placeholder trong form được dịch sang Tiếng Việt
- URL được cập nhật với tham số `code=vi-vn`

---

### AUTO-11

**Title:** Login thất bại — username chứa ký tự đặc biệt / SQL Injection

**Mô tả:** Xác minh rằng hệ thống xử lý an toàn khi username chứa ký tự đặc biệt hoặc chuỗi SQL Injection, không để lộ lỗi SQL hoặc bypass authentication.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Nhập chuỗi SQL injection vào trường **Username**: `' OR '1'='1`
3. Nhập `anything` vào trường **Password**
4. Click button **Login**
5. Lặp lại với payload: `admin'--`

**Expected Result:**
- Hệ thống **không** bị bypass authentication (không đăng nhập thành công)
- Không hiển thị lỗi SQL hoặc stack trace trên giao diện
- Thông báo lỗi hiển thị là: **"No match for Username and/or Password."** (thông báo chuẩn)
- Trang Login được hiển thị lại bình thường

---

### AUTO-12

**Title:** Kiểm tra không có cơ chế khóa tài khoản sau nhiều lần đăng nhập sai

**Mô tả:** Xác minh hành vi của hệ thống khi thực hiện nhiều lần đăng nhập sai liên tiếp. Ghi nhận xem có cơ chế account lockout hay CAPTCHA không.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập
- Tài khoản `admin` đang hoạt động bình thường

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Nhập `admin` vào **Username**, nhập `wrong1` vào **Password**, click **Login**
3. Nhập `admin` vào **Username**, nhập `wrong2` vào **Password**, click **Login**
4. Nhập `admin` vào **Username**, nhập `wrong3` vào **Password**, click **Login**
5. Nhập `admin` vào **Username**, nhập `wrong4` vào **Password**, click **Login**
6. Nhập `admin` vào **Username**, nhập `wrong5` vào **Password**, click **Login**
7. Sau 5 lần sai, thử đăng nhập với đúng credentials: username `admin`, password `admin`

**Expected Result (Hành vi quan sát được):**
- Sau mỗi lần sai: hiển thị thông báo lỗi **"No match for Username and/or Password."**
- Sau 5 lần sai: hệ thống **không** hiển thị CAPTCHA và **không** khóa tài khoản
- Lần thứ 7 (đúng credentials): đăng nhập **thành công**, redirect về Dashboard

> **Ghi chú (Bug/Risk):** Hệ thống hiện tại **KHÔNG có cơ chế account lockout** sau nhiều lần đăng nhập sai. Đây là rủi ro bảo mật — có thể bị tấn công brute-force. Nên bổ sung giới hạn số lần đăng nhập sai và CAPTCHA.

---

### AUTO-13

**Title:** Hover vào trường Username

**Mô tả:** Xác minh rằng trường Username thay đổi trạng thái hiển thị (visual feedback) khi người dùng di chuột vào.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập, chưa click vào bất kỳ field nào

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Di chuột (hover) vào ô input **Username** — chưa click

**Expected Result:**
- Border của input Username thay đổi sang màu xám đậm hơn: `border: 1px solid #b9b9b9`, viền trên `#a0a0a0`
- Xuất hiện hiệu ứng inset box-shadow nhẹ: `inset 0 1px 2px rgba(0,0,0,.1)`
- Con trỏ chuột chuyển thành dạng text cursor (`text`)
- Trường **không** bị focus (chưa có viền xanh dương) — hover ≠ focus

---

### AUTO-14

**Title:** Hover vào trường Password

**Mô tả:** Xác minh rằng trường Password thay đổi trạng thái hiển thị (visual feedback) khi người dùng di chuột vào.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập, chưa click vào bất kỳ field nào

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Di chuột (hover) vào ô input **Password** — chưa click

**Expected Result:**
- Border của input Password thay đổi sang màu xám đậm hơn: `border: 1px solid #b9b9b9`, viền trên `#a0a0a0`
- Xuất hiện hiệu ứng inset box-shadow nhẹ: `inset 0 1px 2px rgba(0,0,0,.1)`
- Con trỏ chuột chuyển thành dạng text cursor (`text`)
- Trường **không** bị focus (chưa có viền xanh dương) — hover ≠ focus
- Các ký tự trong field vẫn bị ẩn (nếu đã nhập trước đó)

---

### AUTO-15

**Title:** Hover vào button Login

**Mô tả:** Xác minh rằng button Login thay đổi màu nền (visual feedback) khi người dùng di chuột vào.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Di chuột (hover) vào button **Login** — chưa click

**Expected Result:**
- Màu nền button thay đổi từ xanh mặc định sang xanh đậm hơn: `background-color: #1a7bb0`
- Border color thay đổi sang: `border-color: #1874a6` (và `#0b3349` theo Bootstrap token)
- Màu chữ và icon vẫn là `#ffffff`
- Con trỏ chuột chuyển thành `pointer`
- Có hiệu ứng chuyển màu mượt (CSS transition từ Bootstrap)
- Button **không** bị click khi chỉ hover

---

### AUTO-16

**Title:** Đăng nhập bằng phím Enter tại trường Password

**Mô tả:** Xác minh rằng người dùng có thể submit form đăng nhập bằng cách nhấn phím **Enter** khi đang focus tại trường Password (không cần click button Login).

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Nhập `admin` vào trường **Username**
3. Click vào trường **Password**, nhập `admin`
4. Nhấn phím **Enter** (không click button Login)

**Expected Result:**
- Form được submit (hành vi tương đương click button Login)
- Hệ thống redirect sang trang Dashboard
- URL chứa `route=common/dashboard&user_token=...`
- Tiêu đề trang là `Dashboard`

---

### AUTO-17

**Title:** Đăng nhập bằng phím Enter tại trường Username

**Mô tả:** Xác minh rằng nhấn **Enter** khi đang focus tại trường Username (sau khi đã điền cả 2 trường) cũng trigger submit form thành công.

**Preconditions:**
- Trình duyệt đang mở tại URL trang Login
- Chưa đăng nhập

**Test Steps:**
1. Mở trình duyệt, truy cập URL: `http://103.245.237.118:8081/opencart/administrator/`
2. Click vào trường **Username**, nhập `admin`
3. Click vào trường **Password**, nhập `admin`
4. Click lại vào trường **Username** (để focus trở lại Username)
5. Nhấn phím **Enter**

**Expected Result:**
- Form được submit
- Hệ thống redirect sang trang Dashboard
- URL chứa `route=common/dashboard&user_token=...`
- Tiêu đề trang là `Dashboard`

---

*File này được cập nhật theo từng module. Xem thêm tại `PROJECT_PROGRESS.md`.*
