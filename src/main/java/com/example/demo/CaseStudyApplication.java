package com.example.demo;

import com.example.demo.model.PriceDetails;
import com.example.demo.repository.PriceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CaseStudyApplication implements CommandLineRunner {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Autowired
	PriceDetailsRepository priceDetailsRepository;
	@Autowired
	RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(CaseStudyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		priceDetailsRepository.save(new PriceDetails(1,100, "$"));
		priceDetailsRepository.save(new PriceDetails(2,200, "$"));
		priceDetailsRepository.save(new PriceDetails(13860428, 13.49,"USD"));
		priceDetailsRepository.save(new PriceDetails(50513417,299.98, "currencyCode"));
		priceDetailsRepository.save(new PriceDetails(49102103,299.99, "currencyCode"));
	}
}
