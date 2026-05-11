@login
Feature: Login — OpenCart Admin Panel
  Kiểm tra toàn bộ chức năng đăng nhập trang quản trị OpenCart.
  URL: http://103.245.237.118:8081/opencart/administrator/

  Background:
    Given Go to login page

  # ================================================================
  # POSITIVE CASES
  # ================================================================

  @AUTO-1 @SMOKE @REGRESSION
  Scenario: AUTO-1 — Login thành công với credentials hợp lệ
    When I login with user "admin" and password "admin"
    Then I see Dashboard page

  # ================================================================
  # NEGATIVE — INVALID CREDENTIALS
  # ================================================================

  @AUTO-2 @AUTO-3 @AUTO-4 @REGRESSION
  Scenario Outline: AUTO-2/3/4 — Login thất bại với credentials không hợp lệ
    When I login with user "<username>" and password "<password>"
    Then I see error message "No match for Username and/or Password."

    Examples: AUTO-2 — Password sai (username hợp lệ)
      | username | password      |
      | admin    | wrongpassword |

    Examples: AUTO-3 — Username không tồn tại trong hệ thống
      | username        | password |
      | nonexistentuser | admin    |

    Examples: AUTO-4 — Cả username và password đều sai
      | username | password     |
      | fakeuser | fakepassword |

  # ================================================================
  # NEGATIVE — EMPTY FIELDS
  # ================================================================

  @AUTO-5 @AUTO-6 @AUTO-7 @REGRESSION
  Scenario Outline: AUTO-5/6/7 — Login thất bại khi để trống trường bắt buộc
    When I login with user "<username>" and password "<password>"
    Then I see required field warning "Please fill out this field."

    Examples: AUTO-5 — Username để trống
      | username | password |
      |          | admin    |

    Examples: AUTO-6 — Password để trống
      | username | password |
      | admin    |          |

    Examples: AUTO-7 — Cả hai trường để trống
      | username | password |
      |          |          |

  # ================================================================
  # UI VERIFICATION
  # ================================================================

  @AUTO-8 @REGRESSION
  Scenario: AUTO-8 — Kiểm tra hiển thị đầy đủ các thành phần trang Login
    Then I see the page title is "Administration"
    And I see the OpenCart logo in the header
    And I see the card header text "Please enter your login details."
    And I see the Username field with placeholder "Username"
    And I see the Password field with placeholder "Password"
    And I see the Login button is visible and enabled
    And I see the language switcher showing "English"
    And I see the footer text "OpenCart © 2009-2026 All Rights Reserved."

  @AUTO-9 @REGRESSION
  Scenario: AUTO-9 — Password field ẩn ký tự nhập
    When I enter "testpassword123" into the Password field
    Then the Password field masks the characters
    And the Password input has type attribute "password"

  @AUTO-10 @REGRESSION
  Scenario: AUTO-10 — Chuyển ngôn ngữ giao diện sang Tiếng Việt
    When I click the language switcher
    And I select language "Tiếng Việt"
    Then the language switcher displays "Tiếng Việt"

  # ================================================================
  # HOVER STATES
  # ================================================================

  @AUTO-13 @AUTO-14 @REGRESSION
  Scenario Outline: AUTO-13/14 — Hover trên input field thay đổi border style
    When I hover over the "<field>" input field
    Then the input border changes to hover style
    And the cursor on the input field is a text cursor
    And the input field is not focused

    Examples: AUTO-13/14 — Các input field trên form Login
      | field    |
      | Username |
      | Password |

  @AUTO-15 @REGRESSION
  Scenario: AUTO-15 — Hover vào button Login thay đổi màu nền
    When I hover over the Login button
    Then the Login button background changes to hover color "#1a7bb0"
    And the cursor on the Login button changes to pointer
    And the Login button is not clicked

  # ================================================================
  # KEYBOARD NAVIGATION
  # ================================================================

  @AUTO-16 @AUTO-17 @REGRESSION
  Scenario Outline: AUTO-16/17 — Đăng nhập bằng phím Enter
    When enter username "admin"
    And enter password "admin"
    And I focus on the "<field>" field
    And I press the Enter key
    Then I see Dashboard page

    Examples: AUTO-16/17 — Nhấn Enter từ các field khác nhau
      | field    |
      | Password |
      | Username |

  # ================================================================
  # SECURITY
  # ================================================================

  @AUTO-11 @REGRESSION
  Scenario Outline: AUTO-11 — Login thất bại với SQL Injection payload
    When I login with user "<username>" and password "<password>"
    Then I am not redirected to the Dashboard
    And no SQL error or stack trace is shown on the page
    And I see error message "No match for Username and/or Password."

    Examples: SQL Injection payloads
      | username      | password |
      | ' OR '1'='1  | anything |
      | admin'--      | anything |

  @AUTO-12 @REGRESSION
  Scenario: AUTO-12 — Không có cơ chế khóa tài khoản sau nhiều lần đăng nhập sai
    When I attempt to login with wrong password 5 times for user "admin"
    Then after each attempt the error message is displayed
    And the account is not locked out
    And no CAPTCHA challenge is shown
    When I login with user "admin" and password "admin"
    Then I see Dashboard page
