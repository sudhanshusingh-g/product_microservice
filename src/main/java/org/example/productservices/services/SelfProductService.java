package org.example.productservices.services;

import org.example.productservices.models.Category;
import org.example.productservices.models.Product;
import org.example.productservices.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.example.productservices.repositories.ProductRepository;

import java.util.List;

@Service("self_product_service")
//@Primary
public class SelfProductService implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(String title, double price, String description, String category, String image) {
        Product product = new Product();
        product.setName(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);
        Category categoryObj = new Category();
        categoryObj.setTitle(category);
        categoryRepository.save(categoryObj);
        product.setCategory(categoryObj);
        return productRepository.save(product);
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
