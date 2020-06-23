package com.vp.amazonreviewsapp.repository;

import com.vp.amazonreviewsapp.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
