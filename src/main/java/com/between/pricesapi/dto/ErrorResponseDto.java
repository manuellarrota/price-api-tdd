package com.between.pricesapi.dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorResponseDto {
    private final Integer status;
    private final String timeStamp;
    private final String message;
    private final String trace;


    public ErrorResponseDto(Integer status, String timeStamp, String message, String trace) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.message = message;
        this.trace = trace;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" + "status=" + status + ", message='" + message
                + '\'' + ", timeStamp='" + timeStamp + '\'' + ", trace='" + trace + '\'' + '}';
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
