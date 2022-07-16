package com.yazilimmotoru.invoicepaymentservice.api.controllers;

import com.yazilimmotoru.invoicepaymentservice.business.abstracts.PaymentService;
import com.yazilimmotoru.invoicepaymentservice.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment/")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("getByCustomerId")
    public ResponseEntity<?> getByCustomerId(@RequestParam long customerId) {
        return this.paymentService.getByCustomerId(customerId);
    }

    @GetMapping("findAll")
    public ResponseEntity<?> findAll() {
        return this.paymentService.findAll();
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestParam long customerId, @RequestBody Payment payment) {
        return this.paymentService.save(customerId, payment);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam long id, @RequestParam double totalAmount) {
        return this.paymentService.update(id, totalAmount);
    }

    @DeleteMapping("deleteById")
    public ResponseEntity<?> deleteById(@RequestParam long id) {
            return this.paymentService.deleteById(id);
    }

    @DeleteMapping("deleteByCustomerId")
    public ResponseEntity<?> deleteByCustomerId(@RequestParam long customerId) {
        return this.paymentService.deleteByCustomerId(customerId);
    }
}
