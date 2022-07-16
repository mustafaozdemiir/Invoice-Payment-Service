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
import java.util.List;
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
    public ResponseEntity<Payment> getById(long paymentId) {
        return new ResponseEntity<>(this.paymentDao.getById(paymentId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getByCustomerId(long customerId) {
        try {
            return new ResponseEntity<>(this.paymentDao.getByCustomerId(customerId), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Payment not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(paymentDao.findAll(), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>("Payment not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> save(long customerId, Payment payment) {
        try {
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
    public ResponseEntity<?> update(long paymentId, double totalAmount) {
        try {
            Payment payment;
            payment = this.paymentDao.getById(paymentId);
            if (Objects.nonNull(totalAmount)) {
                payment.setTotalAmount(totalAmount);
                payment.setDate(LocalDate.now());
            }
            return new ResponseEntity<>(this.paymentDao.save(payment), HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<>("Payment not updated!",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteById(long paymentId) {
        try {
            this.paymentDao.deleteById(paymentId);
            return new ResponseEntity<>("Payment deleted. "+paymentId, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Payment not deleted!",HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> deleteByCustomerId(long customerId) {
        try {
            this.paymentDao.deleteByCustomerId(customerId);
            return new ResponseEntity<>("Payment deleted.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Payment not deleted! CustomerId=> " + customerId , HttpStatus.BAD_REQUEST);
        }
    }


}
