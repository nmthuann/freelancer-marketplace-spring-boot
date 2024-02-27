package com.nmt.freelancermarketplacespringboot.common.errors.exceptions;

import lombok.Getter;

@Getter
public enum AuthException {
    PASSWORD_WRONG("Bạn nhập sai mật khẩu."),
    LOGIN_INVALID("Email hoặc Password của bạn không hợp lệ."),
    LOGIN_FAILED("Đăng nhập thất bại"),
    EMAIL_EXIST("Email đã tồn tại."),
    VERIFY_MAIL_FAILED("Xác thực email thất bại."),
    SEND_MAIL_FAILED("Gửi mail thất bại."),
    REGISTER_EMPLOYEE_FAILED("Đăng ký Tài khoản nhân viên thất bại.");

    private final String message;

    AuthException(String message){
        this.message = message;
    }

}
