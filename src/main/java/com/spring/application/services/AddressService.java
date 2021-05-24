package com.spring.application.services;

import com.spring.application.models.Address;

public class AddressService {

    public void update(Address existingAddress, Address updatedAddress){
        existingAddress.setLine1(updatedAddress.getLine1());
        existingAddress.setLine2(updatedAddress.getLine2());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setState(updatedAddress.getState());
        existingAddress.setCountry(updatedAddress.getCountry());
        existingAddress.setZip(updatedAddress.getZip());
    }
}
