package com.spring.application.responses.common.enumeration;

import com.spring.application.responses.common.StatusCode;

public enum SuccessCodes implements StatusCode {
    DATA_RETRIEVED_SUCCESSFULLY(101, "Data retrieved Successfully"),
    CREATED(201, "Created Successfully"),
    OK(200, "Success");

    Integer code;
    String message;

    private SuccessCodes(Integer code, String message) {
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