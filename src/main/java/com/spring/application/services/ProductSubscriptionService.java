package com.spring.application.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.spring.application.models.BusinessProfile;
import com.spring.application.models.ProductSubscription;
import com.spring.application.models.UserProfile;
import com.spring.application.models.enumeration.Product;
import com.spring.application.services.miscellaneous.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductSubscriptionService {

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private BusinessProfileService businessProfileService;

    @Autowired
    private ValidateService validateService;

    public ProductSubscription newSubscription(ProductSubscription productSubscription, String id){
        UserProfile userProfile = mapper.load(UserProfile.class, id);

        // user has previously subscribed to any of the product
        productSubscription.setUserProfile(id);
        // search for its subscribed product. search on its sort key
        ProductSubscription existingProductSubscription = mapper.load(ProductSubscription.class, null, id);
        // already subscribed to any of the product
        // just append the new product to list of products
        if(existingProductSubscription != null) {
            existingProductSubscription.setProducts(productSubscription.getProducts()); // update the subscribed products
            mapper.save(existingProductSubscription);
            return existingProductSubscription;
        }
         BusinessProfile businessProfile = businessProfileService.create(productSubscription.getBusinessProfile());
         // set business profile
         productSubscription.setBusinessProfile(businessProfile);
         mapper.save(productSubscription);
         return productSubscription;

    }


    public ProductSubscription updateSubscription(ProductSubscription productSubscription, String id){
        UserProfile userProfile = mapper.load(UserProfile.class, id);

        // user has previously subscribed to any of the product
        productSubscription.setUserProfile(id);
        // search for its subscribed product to update it
        ProductSubscription productSubscribed = mapper.load(ProductSubscription.class,
                productSubscription.getId());

        // update this product subscription
        List<Product> productList = productSubscribed.getProducts();
        // rest api for validation
        for( Product product: productList)
        {
            //todo: create a class to store product name and it's corresponding validate api
            boolean isValidated = validateService.validateApi("http://localhost:8080/validate/");
            if(!isValidated)
            {
                //validation failed
                return null;
            }
        }
        productSubscribed.setProducts(productSubscription.getProducts()); // update the subscribed products
        // update business profile and address
        BusinessProfile updatedBusinessProfile = businessProfileService.update(productSubscribed.getBusinessProfile(),
                productSubscription.getBusinessProfile());
        productSubscribed.setBusinessProfile(updatedBusinessProfile);

         mapper.save(productSubscribed);
         return productSubscribed;
        }



}
