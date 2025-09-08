package com.hackerrank.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SortedProducts {

    @JsonProperty("barcode")
    private String barcode;

    @JsonProperty("price")
    private int price;

    public SortedProducts(String barcode , int price) {
        this.barcode = barcode;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
