package com.vendingmachine.product.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private int amountAvailable;
    private int cost;
    private String productName;
    private Long sellerId;

    public Product(int amountAvailable, int cost, String productName, Long sellerId) {
        this.amountAvailable = amountAvailable;
        this.cost = cost;
        this.productName = productName;
        this.sellerId = sellerId;
    }
}
