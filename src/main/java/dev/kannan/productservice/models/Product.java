package dev.kannan.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String imageUrl;
    private Category category;
}

/*
 * @NoArgsConstructor:
 *      - Automatically generates a constructor with no parameters.
 *      - Useful when you need an empty/default constructor.
 * @AllArgsConstructor:
 *      - Automatically generates a constructor with parameters for all fields in the class.
 *      Useful for quickly creating objects with all fields initialized.
 * */
