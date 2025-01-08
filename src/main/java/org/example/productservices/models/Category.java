package org.example.productservices.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String title;
}
//         By default, if relationship of
//        Outer table -> Inner table has one then Early fetching is done
//        Outer table -> Inner Table has list then Lazy fetching is done