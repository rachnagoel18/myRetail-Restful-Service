package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private int id;
    private String name;
    @JsonProperty(value = "current_price")
    private PriceDetails priceInfo;
    public int getId() {
        return id;
    }

    public Product() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(int id, String name, PriceDetails priceInfo) {
        this.id = id;
        this.name = name;
        this.priceInfo = priceInfo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                   priceInfo +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceDetails getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(PriceDetails priceInfo) {
        this.priceInfo = priceInfo;
    }

}
