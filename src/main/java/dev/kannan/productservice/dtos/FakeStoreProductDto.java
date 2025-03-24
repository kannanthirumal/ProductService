package dev.kannan.productservice.dtos;

import dev.kannan.productservice.models.Category;
import dev.kannan.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;

    public Product convertToProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        Category productCategory = new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);

        return product;
    }
}
