package com.vp.amazonreviewsapp.controller;

import com.vp.amazonreviewsapp.model.dto.AwsUserResponseDto;
import com.vp.amazonreviewsapp.model.dto.ProductResponseDto;
import com.vp.amazonreviewsapp.service.AwsUserService;
import com.vp.amazonreviewsapp.service.ProductService;
import com.vp.amazonreviewsapp.service.ReviewService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StatisticsController {

    private final ReviewService reviewService;
    private final ProductService productService;
    private final AwsUserService awsUserService;

    @GetMapping("/words")
    public Page<String> getMostUsedWords(
            @RequestParam(required = false, defaultValue = "25") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<String> mostUsedWords = reviewService.getWordsSortedByUsage();
        List<String> wordsSublist = mostUsedWords.subList((page - 1) * limit, (page * limit) - 1);
        return new PageImpl<>(wordsSublist, PageRequest.of(page, limit), mostUsedWords.size());
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getMostCommentedProducts(
            @RequestParam(required = false, defaultValue = "25") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, limit,
                Sort.by(Sort.Order.desc("reviewsSize"), Sort.Order.asc("productId")));
        return productService.findAll(pageable).map(
                product -> new ProductResponseDto(product.getProductId()));
    }

    @GetMapping("/aws-users")
    public Page<AwsUserResponseDto> getMostActiveUsers(
            @RequestParam(required = false, defaultValue = "25") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, limit,
                Sort.by(Sort.Order.desc("reviewsSize"), Sort.Order.asc("userId")));
        return awsUserService.findAll(pageable).map(
                user -> new AwsUserResponseDto(user.getUserId(), user.getProfileName()));
    }
}
