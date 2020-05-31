package com.example.demo.services;

import com.example.demo.model.PriceDetails;
import com.example.demo.model.Product;
import com.example.demo.repository.PriceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceDetailsServiceImpl implements PriceDetailsService{

    private PriceDetailsRepository priceDetailsRepository;

    @Autowired
    public PriceDetailsServiceImpl(PriceDetailsRepository priceDetailsRepository) {
        this.priceDetailsRepository = priceDetailsRepository;
    }

    @Override
    public List<PriceDetails> listAll() {
        List<PriceDetails> products = new ArrayList<>();
        priceDetailsRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public PriceDetails getById(Integer id) {
        return priceDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public PriceDetails saveOrUpdate(PriceDetails priceDetails) {
        priceDetailsRepository.save(priceDetails);
        return priceDetails;
    }

    @Override
    public void delete(Integer id) {
        priceDetailsRepository.deleteById(id);
    }
}