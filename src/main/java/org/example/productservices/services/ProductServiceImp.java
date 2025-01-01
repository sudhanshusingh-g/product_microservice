package org.example.productservices.services;

import org.example.productservices.dtos.AddProductResponse;
import org.example.productservices.dtos.ProductResponse;
import org.example.productservices.dtos.PatchRequestDTO;
import org.example.productservices.exceptions.ProductNotFound;
import org.example.productservices.models.Category;
import org.example.productservices.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreService")
public class ProductServiceImp implements ProductService{
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    public ProductServiceImp(RestTemplate restTemplate, WebClient webClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @Override
    public Product createProduct(String title,double price,String description,String category,String image) {
        AddProductResponse productResponse=webClient.post()
                .uri("https://fakestoreapi.com/products")
                .retrieve()
                .bodyToMono(AddProductResponse.class)
                .block();
        assert productResponse != null;

        Product product=new Product();
        product.setId(productResponse.getId());

        return product;
    }



    @Override
    public List<Product> getAllProducts() {
        ProductResponse[] fakeStoreResponse=
        restTemplate.getForObject("https://fakestoreapi.com/products", ProductResponse[].class);
        List<Product> products=new ArrayList<>();
        assert fakeStoreResponse != null;
        for(ProductResponse response:fakeStoreResponse){
            products.add(convertToProduct(response));
        }
        return products;
    }

    private Product convertToProduct(ProductResponse fakeStoreResponseDto) {
        Product product = new Product();
        product.setId(fakeStoreResponseDto.getId());
        product.setName(fakeStoreResponseDto.getTitle());
        product.setDescription(fakeStoreResponseDto.getDescription());
        product.setPrice(fakeStoreResponseDto.getPrice());
        product.setImage(fakeStoreResponseDto.getImage());

        // Map category if it's available
        if (fakeStoreResponseDto.getCategory() != null) {
            Category category = new Category();
            category.setTitle(fakeStoreResponseDto.getCategory());
            product.setCategory(category);
        }

        return product;
    }


    @Override
    public Product getProductById(Long id) {
        ProductResponse response=
                webClient.get()
                        .uri("https://fakestoreapi.com/products/"+id)
                        .retrieve()
                        .bodyToMono(ProductResponse.class)
                        .block();
        assert response != null;
        return convertToProduct(response);

    }


    @Override
    public void deleteProductById(Long id) {
        try {
            getProductById(id);
            restTemplate.delete("https://fakestoreapi.com/products/"+id);
        }
        catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFound("Product with id " + id + " not found");
        }

    }

    @Override
    public Product updateProduct(Long id,Product product) {
        return null;
    }

    public Product patchProduct(Long id,Product product){
        if(id != product.getId()) {
            throw  new IllegalArgumentException("Product does not exist");
        }
        PatchRequestDTO patchRequestDTO=webClient.patch()
                .uri("https://fakestoreapi.com/products/"+id)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(PatchRequestDTO.class)
                .block();
        if (patchRequestDTO != null) {
            product.setName(patchRequestDTO.getName());
            product.setDescription(patchRequestDTO.getDescription());
            product.setPrice(patchRequestDTO.getPrice());
            product.setImage(patchRequestDTO.getImage());
        }
        return product;
    }

}
