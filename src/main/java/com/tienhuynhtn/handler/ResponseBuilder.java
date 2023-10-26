package com.tienhuynhtn.handler;

import com.tienhuynhtn.basemodels.BaseErrorResponse;
import com.tienhuynhtn.basemodels.BaseResponse;
import com.tienhuynhtn.util.DateTimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {

    public static <T> ResponseEntity<BaseResponse<T>> generateResponse(String message, HttpStatus httpStatus, T responseObj) {
        BaseResponse response = new BaseResponse(message, httpStatus.value(), responseObj);

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<BaseErrorResponse> generateErrorResponse(String message, HttpStatus httpStatus, int errorCode, List<String> errors) {
        BaseErrorResponse errorResponse = new BaseErrorResponse(
                DateTimeUtil.getZoneDateTimeNow(),
                httpStatus.value(),
                message,
                errorCode,
                errors
        );
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
