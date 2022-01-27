package com.vendingmachine.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long productId;
    private int amountAvailable;
    private int cost;
    private String productName;
    private Long sellerId;
}
