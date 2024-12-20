package org.example.productservices.repositories;

import org.example.productservices.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findById(long id);
    Category findByTitle(String title);
}
