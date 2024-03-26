package com.pure.test;

import com.pure.types.annotation.EsDataId;
import com.pure.types.annotation.EsDataIndex;
import lombok.Data;

@Data
@EsDataIndex(name = "products")
public class Product {

    @EsDataId
    private String sku;
    private String name;
    private Double price;


    public Product(String sku, String name, Double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }
}
