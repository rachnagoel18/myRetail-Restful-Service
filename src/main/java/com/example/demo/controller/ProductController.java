package com.example.demo.controller;

import com.example.demo.model.PriceDetails;
import com.example.demo.model.Product;
import com.example.demo.services.PriceDetailsService;
import com.example.demo.services.ProductService;
import com.example.demo.services.ProductServiceImpl;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService productServiceImpl;
    public ProductController(ProductService service) {
        this.productServiceImpl = service;
    }
    private final Logger logger = Logger.getLogger(ProductController.class.getName());

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductDetails(@PathVariable Integer id) throws HTTPException, MongoException, IOException {
        logger.info("Inside  get controller method");
       return productServiceImpl.getProductDetails(id);

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public Product updatePriceDetails(@PathVariable Integer id, @RequestBody Product product) throws Exception {
        logger.info("Inside update controller method");
        return productServiceImpl.updateProductDetails(id, product);
    }
}
