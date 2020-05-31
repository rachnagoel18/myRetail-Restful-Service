package com.example.demo.services;

import com.example.demo.model.PriceDetails;
import com.example.demo.model.Product;

import java.io.IOException;

public interface ProductService {
    public Product getProductDetails(int id) throws IOException;
    public Product updateProductDetails(int id, Product newProduct) throws Exception;
}
