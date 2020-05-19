package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.adminportal.domain.Payment;

@Transactional
public interface PaymentServiceRepository extends CrudRepository<Payment, Long> {

}
