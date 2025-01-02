package org.example.productservices.services;

import org.example.productservices.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class SelfProductService implements ProductService {
    @Override
    public Product createProduct(String title, double price, String description, String category, String image) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product patchProduct(Long id, Product product) {
        return null;
    }
}
