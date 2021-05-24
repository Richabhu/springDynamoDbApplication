package com.spring.application.responses.common.enumeration;


import com.spring.application.responses.common.StatusCode;

public enum ErrorCodes implements StatusCode {
    GENERIC_ERROR_OCCURRED(101, "Error Occurred!"),
    NOT_FOUND(102, "Data not found"),
    BAD_REQUEST(400, "Internal Server Error");

    Integer code;
    String message;

    private ErrorCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
