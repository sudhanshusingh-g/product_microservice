package org.example.productservices.services;

import org.example.productservices.dtos.FakeStoreResponseDto;
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
public class FakeStoreService implements ProductService{
    private RestTemplate restTemplate;
    private WebClient webClient;

    public FakeStoreService(RestTemplate restTemplate,WebClient webClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreResponseDto fakeStoreResponseDto=convertToDto(product);
        fakeStoreResponseDto = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreResponseDto, FakeStoreResponseDto.class);
        return convertToProduct(fakeStoreResponseDto);
    }

    private FakeStoreResponseDto convertToDto(Product product) {
        FakeStoreResponseDto fakeStoreResponseDto = new FakeStoreResponseDto();
        fakeStoreResponseDto.setId(product.getId());
        fakeStoreResponseDto.setTitle(product.getName());
        fakeStoreResponseDto.setDescription(product.getDescription());
        fakeStoreResponseDto.setPrice(product.getPrice());
        fakeStoreResponseDto.setImage(product.getImage());
        Category category = product.getCategory();

        if (category != null) {
            fakeStoreResponseDto.setCategory(product.getCategory().getTitle());
        } else {
            fakeStoreResponseDto.setCategory(null);
        }

        return fakeStoreResponseDto;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponseDto[] fakeStoreResponseDtos=
        restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreResponseDto[].class);
        List<Product> products=new ArrayList<>();
        for(FakeStoreResponseDto fakeStoreResponseDto:fakeStoreResponseDtos){
            products.add(convertToProduct(fakeStoreResponseDto));
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        try {
            FakeStoreResponseDto fakeStoreResponseDto= restTemplate.getForObject
                    ("https://fakestoreapi.com/products/"+id, FakeStoreResponseDto.class);
            if (fakeStoreResponseDto == null) {
                throw new ProductNotFound("Product with id " + id + " not found");
            }
            return convertToProduct(fakeStoreResponseDto);
        }
        catch (HttpClientErrorException.NotFound e){
            throw new ProductNotFound("Product with "+id+" not found");
        }

    }

    public  Product convertToProduct(FakeStoreResponseDto dto) {

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setId(0L);
        category.setTitle(dto.getCategory());

        product.setCategory(category);

        return product;
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
        if(id != product.getId()) {
            throw  new IllegalArgumentException("Product does not exist");
        }
       restTemplate.put("https://fakestoreapi.com/products/"+id, convertToDto(product));
        return product;
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
