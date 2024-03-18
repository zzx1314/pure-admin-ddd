package com.pure.test;

import lombok.Data;

@Data
public class Product {
    private String sku;
    private String name;
    private Double price;


    public Product(String sku, String name, Double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }
}
