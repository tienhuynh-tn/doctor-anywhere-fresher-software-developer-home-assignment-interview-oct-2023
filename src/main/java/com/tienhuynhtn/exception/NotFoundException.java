package com.tienhuynhtn.exception;

import com.tienhuynhtn.enums.DoctorAnywhereErrorCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private DoctorAnywhereErrorCodeEnum doctorAnywhereErrorCodeEnum;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(DoctorAnywhereErrorCodeEnum doctorAnywhereErrorCodeEnum, String errorMessage) {
        super(errorMessage);
        this.doctorAnywhereErrorCodeEnum = doctorAnywhereErrorCodeEnum;
    }
}