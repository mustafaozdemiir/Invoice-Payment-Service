package com.yazilimmotoru.invoicepaymentservice.dataAccess;

import com.yazilimmotoru.invoicepaymentservice.entities.Customer;
import com.yazilimmotoru.invoicepaymentservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {
    @Query(value = "select * from customers where id=:customerId",nativeQuery = true)
    Customer findByCustomerId(long customerId);
}
