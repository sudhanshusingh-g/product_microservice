package org.example.productservices.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    @ManyToOne
    private Category category;
    private String image;
    private int rating;
}
