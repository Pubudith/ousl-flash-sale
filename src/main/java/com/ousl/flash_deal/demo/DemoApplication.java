package com.ousl.flash_deal.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // A few items are added to database as soon as app runs
    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product("iPhone 15 Pro", 1200.00, 50)); // 50 phones
            productRepository.save(new Product("MacBook Air M2", 1500.00, 10)); // 10 laptops
            productRepository.save(new Product("Sony Headphones", 300.00, 5));  // 5 headphones
            System.out.println("Dummy Data Loaded Successfully!");
        };
    }
}
