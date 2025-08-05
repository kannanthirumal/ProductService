package dev.kannan.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String imageUrl;

    //"CascadeType.PERSIST" -> when you persist a Product, JPA will also persist its associated Category if that category isn't already present in the database.
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
