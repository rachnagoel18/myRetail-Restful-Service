package com.example.demo.repository;

import com.example.demo.model.PriceDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDetailsRepository extends MongoRepository<PriceDetails,Integer> {
}
