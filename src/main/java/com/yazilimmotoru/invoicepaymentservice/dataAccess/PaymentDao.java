package com.yazilimmotoru.invoicepaymentservice.dataAccess;

import com.yazilimmotoru.invoicepaymentservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {

    @Query(value = "select * from payments where customer_id=:customerId",nativeQuery = true)
    Payment getByCustomerId(long customerId);

    @Modifying
    @Transactional
    @Query(value = "delete from payments where customer_id=:customerId",nativeQuery = true)
    void deleteByCustomerId(long customerId);
}
