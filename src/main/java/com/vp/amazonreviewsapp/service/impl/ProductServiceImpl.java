package com.vp.amazonreviewsapp.service.impl;

import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.repository.ProductRepository;
import com.vp.amazonreviewsapp.service.ProductService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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
    public List<Product> addAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void addAll(Iterable<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public List<String> getMostCommentedProducts(Long limit) {
        return productRepository.findAll()
                .stream()
                .sorted((p1, p2) -> p2.getReviews().size() - p1.getReviews().size())
                .map(Product::getProductId)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
