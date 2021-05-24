package com.spring.application.repository;

import com.spring.application.models.Address;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface AddressRepository  extends CrudRepository<Address, String> {
}
