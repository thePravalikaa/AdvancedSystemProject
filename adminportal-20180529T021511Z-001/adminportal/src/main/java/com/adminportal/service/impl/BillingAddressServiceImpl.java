package com.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.BillingAddress;
import com.adminportal.repository.BillingAddressRepository;
import com.adminportal.service.BillingAddressService;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {

	@Autowired
	private BillingAddressRepository billingAddressRepository;

	@Override
	public void removeBillingAddress(BillingAddress billingAddress) {
		billingAddressRepository.delete(billingAddress);

	}

}
