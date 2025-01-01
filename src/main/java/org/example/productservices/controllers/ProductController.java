package org.example.productservices.controllers;

import org.example.productservices.dtos.ProductResponse;
import org.example.productservices.models.Product;
import org.example.productservices.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(@Qualifier("fakeStoreService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id){
        return convertToDTO(productService.getProductById(id));
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){

        List<Product> products= productService.getAllProducts();
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private ProductResponse convertToDTO(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setTitle(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImage());

        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getTitle());
        }

        return dto;
    }


    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }
    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.patchProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }
}
