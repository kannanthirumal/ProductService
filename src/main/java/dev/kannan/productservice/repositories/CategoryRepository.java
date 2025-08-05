package dev.kannan.productservice.repositories;

import dev.kannan.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository<Category, long> -> this repo is to talk  to Category class/model & primary key id of Category class is of type long
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title); //it's a JPA method
    /**
     * Internally JPA probably writes a query like this
     * "Select * from Category where title like 'title'"
     * and then convert the row into category object and return
     */

    Category findById(long id);
}
