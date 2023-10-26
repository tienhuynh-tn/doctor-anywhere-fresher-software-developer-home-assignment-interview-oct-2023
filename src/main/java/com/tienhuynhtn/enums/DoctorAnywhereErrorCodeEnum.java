package com.tienhuynhtn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum DoctorAnywhereErrorCodeEnum {

    // 400 - BAD REQUEST
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, 400000, "Mật khẩu không hợp lệ"),

    // 401 - UNAUTHENTICATED
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, 401000, "Chưa xác thực người dùng"),

    // 403 - UNAUTHORIZED
    UNAUTHORIZED(HttpStatus.FORBIDDEN, 403000, "Không có quyền truy cập"),

    // 404 - NOT FOUND
    NOT_FOUND_TASK(HttpStatus.NOT_FOUND, 404000, "Không tìm thấy công việc"),
    NOT_FOUND_ACCOUNT(HttpStatus.BAD_REQUEST, 400001, "Không tìm thấy tài khoản"),

    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
