package com.yazilimmotoru.invoicepaymentservice.api.controllers;

import com.yazilimmotoru.invoicepaymentservice.business.abstracts.InvoiceService;
import com.yazilimmotoru.invoicepaymentservice.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice/")
public class InvoiceController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("findall")
    public ResponseEntity<?> findAll() {
        return this.invoiceService.findAll();
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestParam long customerId, @RequestBody Invoice invoice) {
        return this.invoiceService.save(customerId, invoice);
    }

    @GetMapping("getByCustomerIdAndInvoiceId")
    public ResponseEntity<?> getByCustomerIdAndInvoiceId(@RequestParam long invoiceId, @RequestParam long customerId) {
        return this.invoiceService.getByCustomerIdAndInvoiceId(invoiceId, customerId);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam long id, @RequestParam(required = false) double amount, @RequestParam boolean status) {
        return this.invoiceService.update(id, amount, status);
    }

    @PutMapping("pay")
    public ResponseEntity<?> pay(@RequestParam long invoiceId, @RequestParam long customerId) {
            return this.invoiceService.pay(invoiceId, customerId);
    }

    @DeleteMapping("deleteById")
    public  ResponseEntity<?> deleteById(@RequestParam long id){
        return this.invoiceService.deleteById(id);
    }

}
