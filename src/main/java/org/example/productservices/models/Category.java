package org.example.productservices.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String title;
//    @OneToMany
//    private List<Product> products;
}
//         By default, if relationship of
//        Outer table -> Inner table has one then Early fetching is done
//        Outer table -> Inner Table has list then Lazy fetching is done