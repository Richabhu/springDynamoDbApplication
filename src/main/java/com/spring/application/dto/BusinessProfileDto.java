package com.spring.application.dto;

import com.spring.application.models.Address;
import com.spring.application.models.enumeration.Tax;

public class BusinessProfileDto {
    private String id;

    private String companyName;
    private String legalName;
    private com.spring.application.models.Address businessAddress;
    private Address legalAddress;
    private Tax tax;
    private String email;
    private String website;
}
