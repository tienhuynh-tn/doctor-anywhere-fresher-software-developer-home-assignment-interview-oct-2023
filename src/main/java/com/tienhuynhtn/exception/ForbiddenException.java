package com.tienhuynhtn.exception;

import com.tienhuynhtn.enums.DoctorAnywhereErrorCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    private DoctorAnywhereErrorCodeEnum doctorAnywhereErrorCodeEnum;

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(DoctorAnywhereErrorCodeEnum doctorAnywhereErrorCodeEnum, String errorMessage) {
        super(errorMessage);
        this.doctorAnywhereErrorCodeEnum = doctorAnywhereErrorCodeEnum;
    }
}
