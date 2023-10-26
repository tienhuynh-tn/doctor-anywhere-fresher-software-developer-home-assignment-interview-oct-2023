package com.tienhuynhtn.basemodels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tienhuynhtn.constant.DateTimeConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse {
    @JsonFormat(pattern = DateTimeConstant.dd_MM_yyyy__HH_mm_ss, timezone = DateTimeConstant.TIME_ZONE)
    private ZonedDateTime timestamp;
    private int status;
    private String message;
    private int errorCode;
    private List<String> errors;
}
