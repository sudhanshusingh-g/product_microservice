package org.example.productservices.services;

import org.example.productservices.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{
    private final WebClient webClient;
    public CategoryServiceImp(WebClient webClient) {
        this.webClient = webClient;
    }
    @Override
    public List<Category> getAllCategories() {
        String[] categories=webClient.get()
                .uri("https://fakestoreapi.com/products/categories")
                .retrieve()
                .bodyToMono(String[].class)
                .block();
        List<Category> categoriesList=new ArrayList<>();
        for(String categoryResponse:categories){
            Category category=new Category();
            category.setTitle(categoryResponse);
            categoriesList.add(category);
        }
        return categoriesList;
    }
}
