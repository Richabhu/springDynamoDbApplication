package com.spring.application.repository;

import com.spring.application.models.ProductSubscription;
import com.spring.application.models.UserProfile;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableScan
@Repository
public interface ProductSubscriptionRepository extends CrudRepository<ProductSubscription, String> {

    Optional<ProductSubscription> findByUser(UserProfile user);
}

