package com.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    private Integer customerId;
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
}
