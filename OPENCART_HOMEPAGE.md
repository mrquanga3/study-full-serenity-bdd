# OPENCART TEST CASES

**URL:** http://103.245.237.118:8081/opencart/administrator/  
**Phạm vi:** Trang HomePage (Dashboard) — OpenCart Admin Panel  
**Tác giả:** Auto-generated  
**Ngày tạo:** 2026-05-13  

---

## Mục lục

| ID | Tên Test Case | Loại | Mức độ |
|----|--------------|------|--------|
| [AUTO-1](#auto-1) | Truy cập HomePage thành công sau khi login | Positive | Critical |
| [AUTO-2](#auto-2) | Kiểm tra hiển thị đầy đủ thành phần chính của Dashboard | UI | High |
| [AUTO-3](#auto-3) | KPI Tile: TOTAL ORDERS mở trang Orders qua View more | Functional | High |
| [AUTO-4](#auto-4) | KPI Tile: TOTAL SALES mở trang Orders qua View more | Functional | High |
| [AUTO-5](#auto-5) | KPI Tile: TOTAL CUSTOMERS mở trang Customers qua View more | Functional | High |
| [AUTO-6](#auto-6) | KPI Tile: PEOPLE ONLINE mở trang Online Report qua View more | Functional | High |
| [AUTO-7](#auto-7) | Sales Analytics đổi filter sang Week | Functional | Medium |
| [AUTO-8](#auto-8) | Sales Analytics có đủ 4 filter Today/Week/Month/Year | UI | Medium |
| [AUTO-9](#auto-9) | Panel World Map hiển thị bản đồ SVG | UI | Medium |
| [AUTO-10](#auto-10) | Panel Recent Activity hiển thị trạng thái rỗng | Functional | Medium |
| [AUTO-11](#auto-11) | Panel Latest Orders hiển thị danh sách và mở chi tiết đơn | Functional | High |
| [AUTO-12](#auto-12) | Nút Developer Setting mở modal Developer Settings | Functional | Medium |
| [AUTO-13](#auto-13) | Toggle menu trái bằng nút hamburger | UI | Medium |
| [AUTO-14](#auto-14) | Mở submenu Catalog và điều hướng vào Categories | Functional | High |
| [AUTO-15](#auto-15) | Dropdown user hiển thị đúng menu shortcut | UI | Medium |
| [AUTO-16](#auto-16) | Logout từ HomePage quay về trang Login | Functional | Critical |

---

## HOMEPAGE (DASHBOARD) TEST CASES

---

### AUTO-1

**Title:** Truy cập HomePage thành công sau khi login

**Mô tả:** Xác minh rằng người dùng admin được chuyển đúng vào Dashboard sau khi đăng nhập hợp lệ.

**Preconditions:**
- Tài khoản hợp lệ: `admin/admin`
- Trình duyệt mở tại trang Login admin

**Test Steps:**
1. Truy cập `http://103.245.237.118:8081/opencart/administrator/`
2. Nhập `admin` vào Username
3. Nhập `admin` vào Password
4. Click **Login**

**Expected Result:**
- Redirect sang trang Dashboard
- URL chứa `route=common/dashboard&user_token=`
- Page title là `Dashboard`
- Heading H1 hiển thị `Dashboard`

---

### AUTO-2

**Title:** Kiểm tra hiển thị đầy đủ thành phần chính của Dashboard

**Mô tả:** Xác minh layout chính của HomePage hiển thị đầy đủ các khu vực quan trọng.

**Preconditions:**
- Đã đăng nhập thành công vào trang Dashboard

**Test Steps:**
1. Quan sát giao diện trang Dashboard

**Expected Result:**
- Có breadcrumb `Home > Dashboard`
- Có 4 KPI tile đầu trang: `TOTAL ORDERS`, `TOTAL SALES`, `TOTAL CUSTOMERS`, `PEOPLE ONLINE`
- Có 4 panel chính: `World Map`, `Sales Analytics`, `Recent Activity`, `Latest Orders`
- Footer hiển thị `OpenCart © 2009-2026 All Rights Reserved. Version 4.0.2.3`

---

### AUTO-3

**Title:** KPI Tile: TOTAL ORDERS mở trang Orders qua View more

**Mô tả:** Xác minh link View more tại tile TOTAL ORDERS điều hướng đúng.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Tại tile `TOTAL ORDERS`, click link **View more...**

**Expected Result:**
- Chuyển sang trang Orders
- URL chứa `route=sale/order&user_token=`
- Page title là `Orders`

---

### AUTO-4

**Title:** KPI Tile: TOTAL SALES mở trang Orders qua View more

**Mô tả:** Xác minh link View more tại tile TOTAL SALES điều hướng đúng.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Tại tile `TOTAL SALES`, click link **View more...**

**Expected Result:**
- Chuyển sang trang Orders
- URL chứa `route=sale/order&user_token=`

---

### AUTO-5

**Title:** KPI Tile: TOTAL CUSTOMERS mở trang Customers qua View more

**Mô tả:** Xác minh link View more tại tile TOTAL CUSTOMERS điều hướng đúng.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Tại tile `TOTAL CUSTOMERS`, click link **View more...**

**Expected Result:**
- Chuyển sang trang Customers
- URL chứa `route=customer/customer&user_token=`

---

### AUTO-6

**Title:** KPI Tile: PEOPLE ONLINE mở trang Online Report qua View more

**Mô tả:** Xác minh link View more tại tile PEOPLE ONLINE điều hướng đúng.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Tại tile `PEOPLE ONLINE`, click link **View more...**

**Expected Result:**
- Chuyển sang trang Online Report
- URL chứa `route=report/online&user_token=`

---

### AUTO-7

**Title:** Sales Analytics đổi filter sang Week

**Mô tả:** Xác minh filter `Week` trong panel Sales Analytics hoạt động và cập nhật trạng thái active.

**Preconditions:**
- Đang ở trang Dashboard
- Panel `Sales Analytics` hiển thị

**Test Steps:**
1. Tại panel `Sales Analytics`, click `Week`
2. Quan sát trạng thái filter sau click

**Expected Result:**
- `Week` được highlight với class `active`
- Các filter còn lại (`Today`, `Month`, `Year`) không active
- Có request dữ liệu biểu đồ tới endpoint chứa `route=extension/opencart/dashboard/chart.chart&range=week`

---

### AUTO-8

**Title:** Sales Analytics có đủ 4 filter Today/Week/Month/Year

**Mô tả:** Xác minh panel Sales Analytics hiển thị đầy đủ các lựa chọn kỳ thời gian.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Quan sát khu vực filter của panel `Sales Analytics`

**Expected Result:**
- Hiển thị đúng 4 option: `Today`, `Week`, `Month`, `Year`
- Có biểu đồ canvas hiển thị bên dưới filter

---

### AUTO-9

**Title:** Panel World Map hiển thị bản đồ SVG

**Mô tả:** Xác minh widget World Map render bản đồ thế giới thành công.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Quan sát panel `World Map`

**Expected Result:**
- Panel có tiêu đề `World Map`
- Khu vực map có container id `vmap`
- Bên trong `vmap` có phần tử `svg` (bản đồ được render)

---

### AUTO-10

**Title:** Panel Recent Activity hiển thị trạng thái rỗng

**Mô tả:** Xác minh dashboard hiển thị đúng trạng thái khi chưa có activity gần đây.

**Preconditions:**
- Đang ở trang Dashboard
- Hệ thống chưa có dữ liệu activity mới

**Test Steps:**
1. Quan sát panel `Recent Activity`

**Expected Result:**
- Panel hiển thị dòng `No results!`
- Không phát sinh lỗi UI hoặc layout vỡ

---

### AUTO-11

**Title:** Panel Latest Orders hiển thị danh sách và mở chi tiết đơn

**Mô tả:** Xác minh widget Latest Orders hiển thị thông tin đơn gần nhất và có thể mở màn chi tiết.

**Preconditions:**
- Đang ở trang Dashboard
- Có ít nhất 1 đơn hàng trong hệ thống

**Test Steps:**
1. Quan sát bảng `Latest Orders`
2. Xác nhận các cột: `Order ID`, `Customer`, `Status`, `Date Added`, `Total`, `Action`
3. Tại dòng đầu tiên, click icon **View** ở cột Action

**Expected Result:**
- Bảng hiển thị dữ liệu đơn hàng
- Click View chuyển sang trang chi tiết đơn
- URL chứa `route=sale/order.info&user_token=` và có tham số `order_id=`

---

### AUTO-12

**Title:** Nút Developer Setting mở modal Developer Settings

**Mô tả:** Xác minh nút bánh răng cạnh tiêu đề Dashboard mở popup cấu hình developer.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Click nút bánh răng (tooltip: `Developer Setting`) gần heading Dashboard
2. Quan sát popup hiển thị
3. Click nút đóng popup

**Expected Result:**
- Hiển thị modal tiêu đề `Developer Settings`
- Modal có các dòng cache: `Theme`, `SASS` cùng action refresh
- Có thể đóng modal thành công và quay lại Dashboard bình thường

---

### AUTO-13

**Title:** Toggle menu trái bằng nút hamburger

**Mô tả:** Xác minh nút menu ở header có thể bật/tắt trạng thái sidebar.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Click nút hamburger ở header
2. Quan sát sidebar trái (`#column-left`)
3. Click lại lần nữa

**Expected Result:**
- Class của sidebar thay đổi giữa trạng thái có `active` và không `active`
- Sidebar vẫn hiển thị menu ổn định, không vỡ layout

---

### AUTO-14

**Title:** Mở submenu Catalog và điều hướng vào Categories

**Mô tả:** Xác minh nhóm menu Catalog mở được submenu và điều hướng đúng trang con.

**Preconditions:**
- Đang ở trang Dashboard
- Sidebar menu đang hiển thị

**Test Steps:**
1. Click menu `Catalog`
2. Xác nhận submenu `#collapse-1` chuyển sang trạng thái hiển thị (`collapse show`)
3. Click mục `Categories`

**Expected Result:**
- Submenu Catalog mở thành công
- Điều hướng sang trang Categories
- URL chứa `route=catalog/category&user_token=`
- Page title là `Categories`

---

### AUTO-15

**Title:** Dropdown user hiển thị đúng menu shortcut

**Mô tả:** Xác minh menu người dùng ở header hiển thị đủ shortcut hỗ trợ quản trị.

**Preconditions:**
- Đang ở trang Dashboard

**Test Steps:**
1. Click dropdown user `John, Doe (admin)` trên header
2. Quan sát danh sách item

**Expected Result:**
- Dropdown hiển thị các item:
  - `Your Profile`
  - `Your Store`
  - `OpenCart Homepage`
  - `Documentation`
  - `Support Forum`
- Mỗi item có link hợp lệ (href không rỗng)

---

### AUTO-16

**Title:** Logout từ HomePage quay về trang Login

**Mô tả:** Xác minh người dùng có thể đăng xuất thành công từ Dashboard.

**Preconditions:**
- Đã đăng nhập vào Dashboard

**Test Steps:**
1. Click `Logout` ở header

**Expected Result:**
- Chuyển về trang Login admin
- URL chứa `route=common/login`
- Page title là `Administration`
- Không còn truy cập được Dashboard nếu không đăng nhập lại

---

*File này được cập nhật theo từng module. Xem thêm tại `PROJECT_PROGRESS.md`.*
