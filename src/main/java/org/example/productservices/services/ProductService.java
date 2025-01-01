package org.example.productservices.services;

import org.example.productservices.models.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(String title,double price,String description,String category,String image);
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public void deleteProductById(Long id);
    public Product updateProduct(Long id,Product product);
    public Product patchProduct(Long id,Product product);
}
