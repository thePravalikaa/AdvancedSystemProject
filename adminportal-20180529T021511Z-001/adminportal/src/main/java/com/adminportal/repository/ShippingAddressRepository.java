package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.adminportal.domain.ShippingAddress;

@Transactional
public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
	
}
