package com.yazilimmotoru.invoicepaymentservice.business.concretes;

import com.yazilimmotoru.invoicepaymentservice.business.abstracts.CustomerService;
import com.yazilimmotoru.invoicepaymentservice.dataAccess.CustomerDao;
import com.yazilimmotoru.invoicepaymentservice.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CustomerManager implements CustomerService {
    private CustomerDao customerDao;

    @Autowired
    public CustomerManager(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public ResponseEntity<?> findAll() { // It brings all the customers.
        try{
            return new ResponseEntity<>(customerDao.findAll(),HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Customer not found!",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> findByCustomerId(Long id) { // Brings the customer with the relevant id.
        try {
            return new ResponseEntity<>(this.customerDao.findByCustomerId(id),HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Customer not found!",HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<?> save(Customer customer) { // New customer registers.
        try{
            return new ResponseEntity<>(this.customerDao.save(customer),HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<>("Customer not created!",HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<?> deleteById(Long id) { // Deletes the customer with the corresponding id.
        try{
            this.customerDao.deleteById(id);
            return new ResponseEntity<>("Deleted by "+id,HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Customer not deleted!",HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<?> update(Long customerId,String name,String surname) { // Updates customer information.
        try{ // Finds the relevant customer by id. Adds and updates new information.
        Customer customer;
        customer=this.customerDao.getById(customerId);
        if (Objects.nonNull(name)&&!name.isEmpty()){
            customer.setName(name);
        }
        if (Objects.nonNull(surname)&&!surname.isEmpty()){
            customer.setSurname(surname);
        }
        return  new ResponseEntity<>(this.customerDao.save(customer), HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<>("Customer not updated!",HttpStatus.BAD_REQUEST);
        }
    }
}
