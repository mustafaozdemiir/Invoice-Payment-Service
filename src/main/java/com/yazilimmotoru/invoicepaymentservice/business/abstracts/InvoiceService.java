package com.yazilimmotoru.invoicepaymentservice.business.abstracts;

import com.yazilimmotoru.invoicepaymentservice.entities.Invoice;
import org.springframework.http.ResponseEntity;



public interface InvoiceService {
    ResponseEntity<Invoice> getById(long invoiceId);
    ResponseEntity<?> findAll();
    ResponseEntity<?> save(long customerId,Invoice invoice);
    ResponseEntity<?> update(long invoiceId,double amount,boolean status);
    ResponseEntity<?> getByCustomerIdAndInvoiceId(long invoiceId, long customerId);
    ResponseEntity<?> pay(long invoiceId,long customerId);

    ResponseEntity<?> deleteById(long id);

}
