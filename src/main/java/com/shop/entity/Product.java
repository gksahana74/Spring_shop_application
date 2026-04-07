package com.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String description;
    private String category;
    private Double price;
    private Integer stock;

    @Lob
    private byte[] productImage;
}
