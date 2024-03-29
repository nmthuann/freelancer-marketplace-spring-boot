package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.users;

import lombok.Getter;

@Getter
public enum AuthExceptionMessages {
    PASSWORD_WRONG("Bạn nhập sai mật khẩu."),
    LOGIN_INVALID("Email hoặc Password của bạn không hợp lệ."),
    LOGIN_FAILED("Đăng nhập thất bại"),
    EMAIL_EXIST("Email đã tồn tại."),
    VERIFY_MAIL_FAILED("Xác thực email thất bại."),
    SEND_MAIL_FAILED("Gửi mail thất bại."),
    REGISTER_EMPLOYEE_FAILED("Đăng ký Tài khoản nhân viên thất bại."),
    USERNAME_NOT_FOUND("User not found with username: "),
    PASSWORD_NOT_EMPTY("Password is required"),
    EMAIL_INVALID("Invalid email format"),
    PASSWORD_TOO_SHORT("Password must be at least 8 characters long")
    ;


    private final String message;

    AuthExceptionMessages(String message){
        this.message = message;
    }

}


