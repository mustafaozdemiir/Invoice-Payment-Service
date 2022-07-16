package com.yazilimmotoru.invoicepaymentservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE,mappedBy = "customer",orphanRemoval = true)
    private Payment payment;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.MERGE,orphanRemoval = true)
    private List<Invoice> invoices;





}
