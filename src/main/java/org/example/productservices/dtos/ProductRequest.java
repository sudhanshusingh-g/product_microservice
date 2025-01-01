package org.example.productservices.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
