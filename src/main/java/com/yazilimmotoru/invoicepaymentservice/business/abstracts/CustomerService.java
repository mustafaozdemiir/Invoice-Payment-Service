package com.yazilimmotoru.invoicepaymentservice.business.abstracts;

import com.yazilimmotoru.invoicepaymentservice.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<?> findAll();
    ResponseEntity<?> findByCustomerId(Long id);
    ResponseEntity<?> save(Customer customer);
    ResponseEntity<?> update(Long id,String name,String surname);
    ResponseEntity<?> deleteById(Long id);
}
