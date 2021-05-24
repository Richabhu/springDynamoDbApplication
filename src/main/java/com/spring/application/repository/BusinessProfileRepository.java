package com.spring.application.repository;

import com.spring.application.models.BusinessProfile;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface BusinessProfileRepository extends CrudRepository<BusinessProfile, String> {
}
