package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.Product;
import java.util.List;

public interface ProductService {
    Product getById(String id);

    void addAll(Iterable<Product> products);

    List<Product> getProductsSortedByMentions();
}
