package org.example.productservices.controllers;

import org.example.productservices.dtos.PaginatedResponse;
import org.example.productservices.models.Product;
import org.example.productservices.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private SearchService searchService;
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }
    @GetMapping
    public ResponseEntity<PaginatedResponse> searchProducts(
            @RequestParam(required = false)String name,
            @PageableDefault(size = 10,sort = "name") Pageable pageable
    ){
        Page<Product> products = searchService.searchProducts(name, pageable);
        PaginatedResponse response=new PaginatedResponse();
        response.setProducts(products.getContent());
        response.setPageSize(response.getPageSize());
        response.setPageNumber(response.getPageNumber()+1);
        return ResponseEntity.ok(response);
    }
}
