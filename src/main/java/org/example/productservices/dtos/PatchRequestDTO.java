package org.example.productservices.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchRequestDTO {
    private String name;
    private String description;
    private double price;
    private String image;
}
