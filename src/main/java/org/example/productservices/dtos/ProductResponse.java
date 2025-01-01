package org.example.productservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private long id;
    private String title;
    private double price;
    private String description;
    private  String category;
    private String image;
}
