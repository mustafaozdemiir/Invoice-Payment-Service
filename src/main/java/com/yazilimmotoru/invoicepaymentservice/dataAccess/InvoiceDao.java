package com.yazilimmotoru.invoicepaymentservice.dataAccess;

import com.yazilimmotoru.invoicepaymentservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface InvoiceDao extends JpaRepository<Invoice,Long> {

    @Query(value = "select * from invoices where id=:invoiceId and customer_id=:customerId",nativeQuery = true)
    Invoice getByCustomerIdAndInvoiceId(long invoiceId, long customerId);



}
