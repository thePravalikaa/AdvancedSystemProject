package com.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.ShippingAddress;
import com.adminportal.repository.ShippingAddressRepository;
import com.adminportal.service.ShippingAddressService;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

	@Autowired
	private ShippingAddressRepository shippingAddressRepository;

	@Override
	public void removeShippingAddress(ShippingAddress shippingAddress) {

		shippingAddressRepository.delete(shippingAddress);
	}

}
