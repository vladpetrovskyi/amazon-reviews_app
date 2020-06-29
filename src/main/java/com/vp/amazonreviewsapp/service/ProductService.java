package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product getById(String id);

    List<Product> addAll(Iterable<Product> products);

    Page<Product> findAll(Pageable pageable);

    List<Product> findAll();
}
