package com.spring.application.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.spring.application.models.UserProfile;
import com.spring.application.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserProfileService {

//    UserProfileRepository userProfileRepository;
//
//    @Autowired
//    public UserProfileService(UserProfileRepository userProfileRepository) {
//        this.userProfileRepository = userProfileRepository;
//    }

    @Autowired
    private DynamoDBMapper mapper;


    public UserProfile create(UserProfile userProfile){

        mapper.save(userProfile);
         return userProfile;
    }


}
