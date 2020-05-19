package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.adminportal.domain.BillingAddress;

@Transactional
public interface BillingAddressRepository extends CrudRepository<BillingAddress, Long> {

}
