package com.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;
    private String emailId;
    private String gender;
    private Long phoneNumber;
    private String address;
    private String password;
}
