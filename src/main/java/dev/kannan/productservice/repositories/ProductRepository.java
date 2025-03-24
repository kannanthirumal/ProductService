package dev.kannan.productservice.repositories;

import dev.kannan.productservice.models.Category;
import dev.kannan.productservice.models.Product;
import dev.kannan.productservice.repositories.projections.ProductWithTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long productId);

    @Override
    List<Product> findAll();

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByCategory_TitleLike(String categoryTitleLike);

    List<Product> findAllByCategory_Id(Long categoryId);

    List<Product> findByTitleStartingWithAndIdGreaterThanAndPriceLessThan(String titleStartingWith, Long id, Double price);

//    custom HQL query
    @Query("SELECT p.title AS title, p.description AS description FROM Product p WHERE p.category.title = :title")
    List<ProductWithTitleAndDescription> getProductWithTitleAndDescription(@Param("title") String title);

//    custom SQL query
//    @Query(value = "SELECT * FROM products p WHERE p.id = :productId", nativeQuery = true)
//    List<ProductWithTitleAndDescription> getAllProductsWithTitleAndDescription(@Param("productId") Long productId);

    Product save(Product product);

    @Override
    void deleteById(Long productId);
}
