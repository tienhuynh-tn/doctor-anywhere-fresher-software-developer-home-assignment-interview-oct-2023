package com.tienhuynhtn.exception;

import com.tienhuynhtn.enums.DoctorAnywhereErrorCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private DoctorAnywhereErrorCodeEnum doctorAnywhereErrorCodeEnum;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(DoctorAnywhereErrorCodeEnum doctorAnywhereErrorCodeEnum, String errorMessage) {
        super(errorMessage);
        this.doctorAnywhereErrorCodeEnum = doctorAnywhereErrorCodeEnum;
    }
}