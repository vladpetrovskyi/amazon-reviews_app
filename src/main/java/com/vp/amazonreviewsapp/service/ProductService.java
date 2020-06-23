package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.Product;
import java.util.List;
import java.util.Set;

public interface ProductService {
    Product getById(String id);

    List<Product> addAll(List<Product> products);

    Product add(Product product);

    void addAll(Iterable<Product> products);

    List<String> getMostCommentedProducts(Long limit);
}
