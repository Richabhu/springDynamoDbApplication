package com.spring.application.responses.common;


import com.spring.application.responses.common.enumeration.StatusType;
import com.spring.application.responses.common.enumeration.SuccessCodes;
import lombok.Data;

import java.io.Serializable;

@Data
public class StatusResponse implements Serializable {
    private Integer statusCode;
    private String statusMessage = "";
    private StatusType statusType;
    private Integer totalCount;

    public StatusResponse(StatusCode statusCode, StatusType statusType, Integer totalCount) {
        this.statusType = StatusType.SUCCESS;
        this.statusCode = statusCode.getCode();
        this.statusMessage = statusCode.getMessage();
        this.statusType = statusType;
        if (statusCode instanceof SuccessCodes) {
            this.statusType = StatusType.SUCCESS;
        } else {
            this.statusType = StatusType.ERROR;
        }

        this.totalCount = totalCount;
    }

    public StatusResponse(StatusCode statusCode, Integer totalCount) {
        this.statusType = StatusType.SUCCESS;
        this.statusCode = statusCode.getCode();
        this.statusMessage = statusCode.getMessage();
        if (statusCode instanceof SuccessCodes) {
            this.statusType = StatusType.SUCCESS;
        } else {
            this.statusType = StatusType.ERROR;
        }

        this.totalCount = totalCount;
    }
}
