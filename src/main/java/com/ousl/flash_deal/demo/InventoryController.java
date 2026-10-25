package com.ousl.flash_deal.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private ProductRepository productRepository;

    // 1. View all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. During the flash sale, someone buys an item. (Checkout/Buy)
    @PostMapping("/buy/{id}")
    public ResponseEntity<String> buyProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            
            // Checking if the items are in stock
            if (product.getStockQuantity() > 0) {
                // If the item is in stock, the stock level is reduced by 1
                product.setStockQuantity(product.getStockQuantity() - 1);
                productRepository.save(product);
                
                return ResponseEntity.ok("Success! You bought " + product.getName() + ". Remaining stock: " + product.getStockQuantity());
            } else {
                return ResponseEntity.badRequest().body("Sorry! " + product.getName() + " is OUT OF STOCK! Flash sale ended.");
            }
        }
        return ResponseEntity.notFound().build();
    }
}