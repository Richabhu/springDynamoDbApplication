package com.spring.application.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.spring.application.models.Address;
import com.spring.application.models.BusinessProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessProfileService {

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private AddressService addressService;

    public BusinessProfile create(BusinessProfile businessProfile)
    {
        Address legalAddress = businessProfile.getLegalAddress();
        mapper.save(legalAddress);
        businessProfile.setLegalAddress(legalAddress);

        Address businessAddress = businessProfile.getBusinessAddress();
        mapper.save(businessAddress);
        businessProfile.setBusinessAddress(businessAddress);

        mapper.save(businessProfile);
        return businessProfile;
    }

    public BusinessProfile update(BusinessProfile existingBusinessProfile, BusinessProfile updatedProfile){

            // update legal address
            Address existingLegalAddress = mapper.load(Address.class, existingBusinessProfile.getLegalAddress().getId());
            addressService.update(existingLegalAddress, updatedProfile.getLegalAddress());

            // update business address
            Address existingBusinessAddress = mapper.load(Address.class, existingBusinessProfile.getBusinessAddress().getId());
            addressService.update(existingBusinessAddress, updatedProfile.getBusinessAddress());


            // updating the remaining field
            existingBusinessProfile.setCompanyName(updatedProfile.getCompanyName());
            existingBusinessProfile.setEmail(updatedProfile.getEmail());
            existingBusinessProfile.setLegalName(updatedProfile.getLegalName());
            existingBusinessProfile.setTax(updatedProfile.getTax());
            existingBusinessProfile.setWebsite(updatedProfile.getWebsite());

            return existingBusinessProfile;

    }
}