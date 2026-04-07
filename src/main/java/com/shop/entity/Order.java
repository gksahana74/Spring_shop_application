package com.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Integer customerId;
    private String customerName;
    private String deliveryAddress;
    private Double totalAmount;
    private String status;  // PENDING, CONFIRMED, DELIVERED
    private LocalDateTime orderDate;
}
