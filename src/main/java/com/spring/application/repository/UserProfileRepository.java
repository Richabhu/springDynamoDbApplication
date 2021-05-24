package com.spring.application.repository;

import com.spring.application.models.UserProfile;
import org.hibernate.validator.constraints.pl.REGON;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, String> {
}

