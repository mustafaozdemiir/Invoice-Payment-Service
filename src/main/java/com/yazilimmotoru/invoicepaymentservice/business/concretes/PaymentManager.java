package com.yazilimmotoru.invoicepaymentservice.business.concretes;

import com.yazilimmotoru.invoicepaymentservice.business.abstracts.CustomerService;
import com.yazilimmotoru.invoicepaymentservice.business.abstracts.PaymentService;
import com.yazilimmotoru.invoicepaymentservice.dataAccess.PaymentDao;
import com.yazilimmotoru.invoicepaymentservice.entities.Customer;
import com.yazilimmotoru.invoicepaymentservice.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private CustomerService customerService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, CustomerService customerService) {
        this.paymentDao = paymentDao;
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<Payment> getById(long paymentId) { // It brings payment related to id.
        return new ResponseEntity<>(this.paymentDao.getById(paymentId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getByCustomerId(long customerId) { // It brings payment related to CustomerId.
        try {
            return new ResponseEntity<>(this.paymentDao.getByCustomerId(customerId), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Payment not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> findAll() { // It fetches all generated payments.
        try {
            return new ResponseEntity<>(paymentDao.findAll(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Payment not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> save(long customerId, Payment payment) { // Registering a new payment.
        try { // A new payment is created by finding the customer related to the customerId. The created payment is added to the customer.
            ResponseEntity<Customer> customerResponseEntity = (ResponseEntity<Customer>) customerService.findByCustomerId(customerId);
            payment.setCustomer(customerResponseEntity.getBody());
            Payment payment1 = this.paymentDao.save(payment);
            customerResponseEntity.getBody().setPayment(payment1);
            customerService.update(customerId, customerResponseEntity.getBody().getName(), customerResponseEntity.getBody().getSurname());
            return new ResponseEntity<>(payment1, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Payment not created!", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> update(long paymentId, double totalAmount) { // The payment update does its job.
        try { // It brings the payment in the relevant id and updates it by adding the incoming value.
            Payment payment;
            payment = this.paymentDao.getById(paymentId);
            if (Objects.nonNull(totalAmount)) {
                payment.setTotalAmount(totalAmount);
                payment.setDate(LocalDate.now());
            }
            return new ResponseEntity<>(this.paymentDao.save(payment), HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>("Payment not updated!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteById(long paymentId) { // Deleting with paymentId.
        try {
            this.paymentDao.deleteById(paymentId);
            return new ResponseEntity<>("Payment deleted. " + paymentId, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Payment not deleted!", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> deleteByCustomerId(long customerId) { // Deleting with customerId.
        try {
            this.paymentDao.deleteByCustomerId(customerId);
            return new ResponseEntity<>("Payment deleted.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Payment not deleted! CustomerId=> " + customerId, HttpStatus.BAD_REQUEST);
        }
    }


}
