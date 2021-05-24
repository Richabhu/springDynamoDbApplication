package com.spring.application.dto;

import com.spring.application.models.BusinessProfile;
import com.spring.application.models.UserProfile;
import com.spring.application.models.enumeration.Product;

import java.util.List;

public class ProductSubscriptionDto {

    private String id;

    private UserProfile user;
    private BusinessProfile businessProfile;
    private List<Product> products;

}
