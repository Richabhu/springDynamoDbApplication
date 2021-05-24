package com.spring.application.services;

import com.spring.application.models.Address;
import com.spring.application.models.BusinessProfile;
import com.spring.application.models.ProductSubscription;
import com.spring.application.models.UserProfile;
import com.spring.application.models.enumeration.Product;
import com.spring.application.repository.AddressRepository;
import com.spring.application.repository.BusinessProfileRepository;
import com.spring.application.repository.ProductSubscriptionRepository;
import com.spring.application.repository.UserProfileRepository;
import com.spring.application.services.miscellaneous.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSubscriptionService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    ProductSubscriptionRepository productSubscriptionRepository;

    @Autowired
    BusinessProfileRepository businessProfileRepository;

    @Autowired
    BusinessProfileService businessProfileService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ValidateService validateService;

    public ProductSubscription newSubscription(ProductSubscription productSubscription, String id){
        Optional<UserProfile> user = userProfileRepository.findById(id);

        // user has previously subscribed to any of the product
        if(user.isPresent())
        {
            UserProfile userProfile = user.get();
            productSubscription.setUserProfile(userProfile);
            // search for its subscribed product to update it
            Optional<ProductSubscription> existingProductSubscription = productSubscriptionRepository.findByUser(userProfile);

            // already subscribed to any of the product
            // just append the new product to list of products
            if(existingProductSubscription.isPresent())
            {
                ProductSubscription productSubscribed = existingProductSubscription.get();
                productSubscribed.setProducts(productSubscription.getProducts()); // update the subscribed products
                return productSubscriptionRepository.save(productSubscribed);
            }

            // new product subscription
            // create its business profile
            BusinessProfile businessProfile = businessProfileService.create(productSubscription.getBusinessProfile());
            // set business profile
            productSubscription.setBusinessProfile(businessProfile);
            return productSubscriptionRepository.save(productSubscription);

        }
        return null;

    }


    public ProductSubscription updateSubscription(ProductSubscription productSubscription, String id){
        Optional<UserProfile> user = userProfileRepository.findById(id);

        // user has previously subscribed to any of the product
        if(user.isPresent())
        {
            UserProfile userProfile = user.get();
            productSubscription.setUserProfile(userProfile);
            // search for its subscribed product to update it
            Optional<ProductSubscription> existingProductSubscription = productSubscriptionRepository.findByUser(userProfile);

            if(existingProductSubscription.isPresent())
            {
                // update this product subscription
                ProductSubscription productSubscribed = existingProductSubscription.get();
                List<Product> productList = productSubscribed.getProducts();

                // rest api for validation
                for( Product product: productList)
                {
                    //todo: create a class to store product name and it's corresponding validate api
                    boolean isValidated = validateService.validateApi("", 0);
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

                return productSubscriptionRepository.save(productSubscribed);
            }
            // user has not subscribed to any product previously
            return null;
        }
        // user is not present
        return null;

    }


}
