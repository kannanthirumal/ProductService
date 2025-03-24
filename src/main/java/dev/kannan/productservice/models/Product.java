package dev.kannan.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseModel{
    private String title;
    private Double price;
    private String description;
    private String imageUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST})
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
