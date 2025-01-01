package org.example.productservices.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    private Category category;
    private String image;
    private int rating;
}
