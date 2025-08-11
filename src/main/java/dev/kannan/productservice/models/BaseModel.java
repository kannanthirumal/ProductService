package dev.kannan.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date createdAt;
    private Date lastModifiedAt;
    private boolean isDeleted;
}

/**
 * Redis storage in Java requires objects to be serializable by default:
 * Spring Data Redis's default serializer depends on Java serialization.
 * If your model does not implement Serializable, runtime serialization exceptions occur.
 */
