package com.vp.amazonreviewsapp.service.impl;

import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.repository.ProductRepository;
import com.vp.amazonreviewsapp.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> addAll(Iterable<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
