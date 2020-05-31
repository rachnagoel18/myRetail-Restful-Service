package com.example.demo.services;

import com.example.demo.model.PriceDetails;
import com.example.demo.model.Product;

import java.util.List;

public interface PriceDetailsService {

    List<PriceDetails> listAll();

    PriceDetails getById(Integer id);

    PriceDetails saveOrUpdate(PriceDetails price);

    void delete(Integer id);

}
