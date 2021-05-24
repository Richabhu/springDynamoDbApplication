package com.spring.application.controller;

import com.spring.application.models.BusinessProfile;
import com.spring.application.models.ProductSubscription;
import com.spring.application.models.UserProfile;
import com.spring.application.responses.BusinessProfileResponse;
import com.spring.application.responses.UserProfileResponse;
import com.spring.application.responses.common.StatusResponse;
import com.spring.application.responses.common.enumeration.SuccessCodes;
import com.spring.application.services.BusinessProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/business/")
public class BusinessController {


    protected BusinessProfileResponse createResponse(BusinessProfile businessProfile) {
        BusinessProfileResponse response = new BusinessProfileResponse();
        response.setBusinessProfileEntity(businessProfile);
        return response;
    }
        @Autowired
        private BusinessProfileService businessProfileService;

}
