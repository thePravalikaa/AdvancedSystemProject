package com.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Payment;
import com.adminportal.repository.PaymentServiceRepository;
import com.adminportal.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentServiceRepository paymentServiceRepository;

	@Override
	public void removePaymentService(Payment payment) {
		paymentServiceRepository.delete(payment);

	}

}
