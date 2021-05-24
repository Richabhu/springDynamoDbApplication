package com.spring.application.services;

import com.spring.application.models.Address;
import com.spring.application.models.BusinessProfile;
import com.spring.application.repository.AddressRepository;
import com.spring.application.repository.BusinessProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessProfileService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    BusinessProfileRepository businessProfileRepository;

    @Autowired
    AddressService addressService;


//    @HystrixCommand(fallbackMethod = "defaultGreeting")
//    public String getGreeting() {
//        return new RestTemplate()
//                .getForObject("http://localhost:8080/validate", String.class);
//    }
//
//    private String defaultGreeting() {
//        return "Hello User!";
//    }


    public BusinessProfile create(BusinessProfile businessProfile)
    {
        Address legalAddress = businessProfile.getLegalAddress();
        businessProfile.setLegalAddress(addressRepository.save(legalAddress));

        Address businessAddress = businessProfile.getBusinessAddress();
        businessProfile.setBusinessAddress(addressRepository.save(businessAddress));

        return businessProfileRepository.save(businessProfile);
    }

    public BusinessProfile update(BusinessProfile existingBusinessProfile, BusinessProfile updatedProfile){

            // update legal address
            Optional<Address> legalAddr = addressRepository.findById(existingBusinessProfile.getLegalAddress().getId());
            if(legalAddr.isPresent())
            {
                Address existingLegalAddress = legalAddr.get();
                addressService.update(existingLegalAddress, updatedProfile.getLegalAddress());
            }
            // update business address
            Optional<Address> businessAddr = addressRepository.findById(existingBusinessProfile.getBusinessAddress().getId());
            if(businessAddr.isPresent()){
                Address existingBusinessAddress = businessAddr.get();
                addressService.update(existingBusinessAddress, updatedProfile.getBusinessAddress());
            }

            // updating the remaining field
            existingBusinessProfile.setCompanyName(updatedProfile.getCompanyName());
            existingBusinessProfile.setEmail(updatedProfile.getEmail());
            existingBusinessProfile.setLegalName(updatedProfile.getLegalName());
            existingBusinessProfile.setTax(updatedProfile.getTax());
            existingBusinessProfile.setWebsite(updatedProfile.getWebsite());

            return existingBusinessProfile;

    }
}