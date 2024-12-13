package org.example.productservices.services;

import org.example.productservices.dtos.FakeStoreResponseDto;
import org.example.productservices.models.Category;
import org.example.productservices.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class FakeStoreService implements ProductService{
    private RestTemplate restTemplate;
    private WebClient webClient;

    public FakeStoreService(RestTemplate restTemplate,WebClient webClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreResponseDto fakeStoreResponseDto= restTemplate.getForObject
                ("https://fakestoreapi.com/products/"+id, FakeStoreResponseDto.class);
        return convertToProduct(fakeStoreResponseDto);
    }

    public  Product convertToProduct(FakeStoreResponseDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("FakeStoreResponseDto cannot be null");
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        Category category = new Category();
        category.setId(0);
        category.setTitle(dto.getCategory());

        product.setCategory(category);

        return product;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }
}
