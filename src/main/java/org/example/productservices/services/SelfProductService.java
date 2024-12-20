package org.example.productservices.services;

import org.example.productservices.models.Category;
import org.example.productservices.models.Product;
import org.example.productservices.repositories.CategoryRepo;
import org.example.productservices.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService{

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    public SelfProductService(ProductRepo productRepo,CategoryRepo categoryRepo) {
        this.categoryRepo=categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Product createProduct(Product product) {
        Category category=categoryRepo.findByTitle(product.getCategory().getTitle());
        if(category==null){
            category=product.getCategory();
            categoryRepo.save(category);
        }
        product.setCategory(category);
        return productRepo.save(product);
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
