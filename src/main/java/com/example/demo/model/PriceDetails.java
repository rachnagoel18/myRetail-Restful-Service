package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.io.Serializable;

@Document(collection = "prices")
public class PriceDetails implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @JsonIgnore
    private int id;
    private  double value;
    private  String currency_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PriceDetails(int id, double value, String currency_code) {
        this.id = id;
        this.value = value;
        this.currency_code = currency_code;
    }

    public PriceDetails() {
    }

    @Override
    public String toString() {
        return "current_price{" +
                " value=" + value +
                ", currency_code='" + currency_code + '\'' +
                '}';
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
