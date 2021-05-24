package com.spring.application.controller;

import com.spring.application.models.BusinessProfile;
import com.spring.application.models.UserProfile;
import com.spring.application.responses.BusinessProfileResponse;
import com.spring.application.responses.UserProfileResponse;
import com.spring.application.responses.common.StatusResponse;
import com.spring.application.responses.common.enumeration.SuccessCodes;
import com.spring.application.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserProfileController {


    @Autowired
    UserProfileService userProfileService;

    protected UserProfileResponse createResponse(UserProfile userProfile) {
        UserProfileResponse response = new UserProfileResponse();
        response.setUserProfileEntity(userProfile);
        return response;
    }

    @RequestMapping(
            value = {"create"},
            method = {RequestMethod.POST}
    )
    public UserProfileResponse create(@RequestBody UserProfile input) throws Exception {

        UserProfileResponse response = this.createResponse(null);
        UserProfile result = userProfileService.create(input);

        response = this.createResponse(result);
        response.setStatus(new StatusResponse(SuccessCodes.OK, 1));
        return response;

    }



}
