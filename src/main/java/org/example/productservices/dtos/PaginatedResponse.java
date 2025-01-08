package org.example.productservices.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productservices.models.Product;

import java.util.List;

@Getter
@Setter
public class PaginatedResponse {
    private List<Product> products;
    private int pageNumber;
    private int pageSize;
}
