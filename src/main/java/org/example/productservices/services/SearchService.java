package org.example.productservices.services;

import org.example.productservices.models.Product;
import org.example.productservices.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<Product> searchProducts(String name, Pageable pageable) {
        if (name == null || name.isEmpty()) {
            return productRepository.findAll(pageable);
        }

        // Otherwise, search by name
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
