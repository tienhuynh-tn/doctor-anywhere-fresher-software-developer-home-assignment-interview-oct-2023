package com.tienhuynhtn.exception;

import com.tienhuynhtn.enums.DoctorAnywhereErrorCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthenticatedException extends RuntimeException {

    private DoctorAnywhereErrorCodeEnum doctorAnywhereErrorCodeEnum;

    public UnauthenticatedException(String message) {
        super(message);
    }

    public UnauthenticatedException(DoctorAnywhereErrorCodeEnum doctorAnywhereErrorCodeEnum, String errorMessage) {
        super(errorMessage);
        this.doctorAnywhereErrorCodeEnum = doctorAnywhereErrorCodeEnum;
    }
}