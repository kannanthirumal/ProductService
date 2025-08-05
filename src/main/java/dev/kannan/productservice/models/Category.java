package dev.kannan.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity // marks this class as a JPA entity to be mapped to a database table
public class Category extends BaseModel{
    private String title;
    private String description;

    //in (mappedBy = "category") 'category' is the field name in Product that owns this relationship
    //'CascadeType.REMOVE' -> When a category is deleted, delete all the products that are associated with this category
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Product> products;
    // @JsonIgnore -> Prevents this field from being included in JSON output
    // Helps avoid circular reference issues or very large responses when viewing results in Postman
}
