package com.yazilimmotoru.invoicepaymentservice.api.controllers;

import com.yazilimmotoru.invoicepaymentservice.business.abstracts.CustomerService;
import com.yazilimmotoru.invoicepaymentservice.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("findAll")
    public ResponseEntity<?> findAll(){
            return this.customerService.findAll();
    }

    @GetMapping("findById")
    public ResponseEntity<?> findById(@RequestParam Long id){
        return this.customerService.findByCustomerId(id);
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Customer customer){
            return this.customerService.save(customer);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam long id,@RequestParam(required = false) String name,@RequestParam(required = false) String surname){
           return this.customerService.update(id,name,surname);
    }

    @DeleteMapping("deleteById")
    public ResponseEntity<?> deleteById(@RequestParam long id){
        return this.customerService.deleteById(id);
    }

}
