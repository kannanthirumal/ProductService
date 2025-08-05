package dev.kannan.productservice.repositories;

import dev.kannan.productservice.Projections.ProductProjection;
import dev.kannan.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Product save(Product product); -> save where?
     * save in products table coz -> this repository belongs to the Product model
     * as we have passed "Product" model in it -> "JpaRepository<Product, Long>"
     */

    Product save(Product product);

    /**
     * Find All? -> find what?
     * find all the products from the products table -> coz this repository belongs to the Product model
     */
    List<Product> findAll();

    Optional<Product> findById(long id);

    /**
     * see the below JPA query
     * List<Product> findByCategory_TitleAndCategory_Description(String categoryTitle, String categoryDescription);
     *
     * findByCategory_Title: For each Product, this first finds its associated `Category` object.
     * The underscore (`_`) then acts as a command to "go into" that Category and filter by its `title`.
     *
     * AndCategory_Description: The `And` adds a second rule. This also tells JPA to "go into" the
     * Product's associated `Category` object and filter by its `description`.
     *
     */
    List<Product> findByCategory_TitleAndCategory_Description(String categoryTitle, String categoryDescription);

    /**
     * More declared queries
     */
    List<Product> findByCategory_Id(long id);
    List<Product> findByTitleStartingWith(String startingWith);
    List<Product> findByTitleStartingWithAndIdEquals(String startingWith, long id);
    List<Product> findByTitleStartingWithAndIdEqualsAndPriceLessThan(String startingWith, long id, double price);

    /**
     * HQl Query
     * added aliases as it should match the projections
     * just working on the Product model and ORM will deal with the tables
     */
    @Query("select p.id as id, p.title as title from Product p where p.category.title = :categoryName")
    List<ProductProjection> getIdAndTitleOfAllProductsWithCategoryTitle(@Param("categoryName") String categoryTitle); //it can be anything and hql will handle this behind the scenes, i used this to make the method descriptive

    /**
     * SQL Query
     * directly working on the products table
     */
    @Query(value = "SELECT p.id as id, p.title as title FROM products p JOIN categories c ON p.category_id = c.id WHERE c.title = :categoryName", nativeQuery = true)
    List<ProductProjection> getIdAndTitleOfAllProductsWithCategoryTitleSql(@Param("categoryName") String categoryTitle); //it can be anything and hql will handle this behind the scenes, i used this to make the method descriptive

}
