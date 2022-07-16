package com.yazilimmotoru.invoicepaymentservice.business.concretes;

import com.yazilimmotoru.invoicepaymentservice.business.abstracts.CustomerService;
import com.yazilimmotoru.invoicepaymentservice.business.abstracts.InvoiceService;
import com.yazilimmotoru.invoicepaymentservice.business.abstracts.PaymentService;
import com.yazilimmotoru.invoicepaymentservice.dataAccess.InvoiceDao;
import com.yazilimmotoru.invoicepaymentservice.entities.Customer;
import com.yazilimmotoru.invoicepaymentservice.entities.Invoice;
import com.yazilimmotoru.invoicepaymentservice.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InvoiceManager implements InvoiceService {

    private InvoiceDao invoiceDao;
    private CustomerService customerService;
    private PaymentService paymentService;

    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao, CustomerService customerService, PaymentService paymentService) {
        this.invoiceDao = invoiceDao;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    @Override
    public ResponseEntity<Invoice> getById(long invoiceId) {
        return new ResponseEntity<Invoice>(this.invoiceDao.getById(invoiceId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(invoiceDao.findAll(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Invoice not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> save(long customerId, Invoice invoice) {
        try {
            Customer customer = (Customer) customerService.findByCustomerId(customerId).getBody();
            invoice.setCustomer(customer);
            return new ResponseEntity<>(this.invoiceDao.save(invoice), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Invoice not created! " , HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> update(long invoiceId, double amount, boolean status) {
        try {
            Invoice invoice;
            invoice = getById(invoiceId).getBody();
            if (Objects.nonNull(amount) && amount > 0) {
                invoice.setAmount(amount);
            }
            if (Objects.nonNull(status)) {
                invoice.setStatus(status);
            }
            return new ResponseEntity<>(this.invoiceDao.save(invoice), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Invoice not updated!", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> getByCustomerIdAndInvoiceId(long invoiceId, long customerId) {
        try {
            return new ResponseEntity<>(this.invoiceDao.getByCustomerIdAndInvoiceId(invoiceId, customerId), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Unpaid invoice not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> pay(long invoiceId, long customerId) {
        try {
            Invoice invoice;
            invoice = this.invoiceDao.getByCustomerIdAndInvoiceId(invoiceId, customerId);
            if (!invoice.isStatus()) {
                update(invoiceId, invoice.getAmount(), true);
                Payment payment = (Payment) this.paymentService.getByCustomerId(customerId).getBody();
                payment.setTotalAmount(payment.getTotalAmount() + invoice.getAmount());
                this.paymentService.update(payment.getId(), payment.getTotalAmount());
                return new ResponseEntity<>(invoice, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("The invoice has already been paid.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Invoice not found!", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> deleteById(long id) {
        try{
            this.invoiceDao.deleteById(id);
            return new ResponseEntity<>("Invoice deleted",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Invoice not deleted!",HttpStatus.BAD_REQUEST);
        }

    }

}
