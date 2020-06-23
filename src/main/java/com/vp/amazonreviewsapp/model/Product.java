package com.vp.amazonreviewsapp.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String productId;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;
}
