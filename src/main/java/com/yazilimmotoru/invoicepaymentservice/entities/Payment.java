package com.yazilimmotoru.invoicepaymentservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double totalAmount;
    private LocalDate date;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id",nullable = false,referencedColumnName = "id")
    private Customer customer;


}
