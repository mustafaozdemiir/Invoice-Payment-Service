package com.yazilimmotoru.invoicepaymentservice.business.abstracts;

import com.yazilimmotoru.invoicepaymentservice.entities.Payment;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PaymentService {
    ResponseEntity<Payment> getById(long paymentId);
    ResponseEntity<?> getByCustomerId(long customerId);
    ResponseEntity<?> findAll();
    ResponseEntity<?> save(long customerId,Payment payment);
    ResponseEntity<?> update(long paymentId,double totalAmount);
    ResponseEntity<?> deleteById(long paymentId);
    ResponseEntity<?> deleteByCustomerId(long customerId);



}
