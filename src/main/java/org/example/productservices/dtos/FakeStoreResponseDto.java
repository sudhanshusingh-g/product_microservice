package org.example.productservices.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productservices.models.Category;
import org.example.productservices.models.Product;

@Getter
@Setter
public class FakeStoreResponseDto {
    private long id;
    private String title;
    private double price;
    private String description;
    private  String category;
    private String image;
}
