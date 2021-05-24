package com.spring.application.responses;

import com.spring.application.models.UserProfile;
import com.spring.application.responses.common.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfileResponse extends BaseResponse {
    private UserProfile userProfileEntity;
}
