package com.spring.application.responses;

import com.spring.application.models.BusinessProfile;
import com.spring.application.responses.common.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessProfileResponse extends BaseResponse {
    private BusinessProfile businessProfileEntity;
}