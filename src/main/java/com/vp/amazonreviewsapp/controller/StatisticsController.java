package com.vp.amazonreviewsapp.controller;

import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.service.ProductService;
import com.vp.amazonreviewsapp.service.ReviewService;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StatisticsController {

    private final ReviewService reviewService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/words")
    public Page<String> getMostUsedWords(
            @RequestParam(required = false, defaultValue = "25") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<String> mostUsedWords = reviewService.getWordsSortedByUsage();
        return new PageImpl<>(mostUsedWords, PageRequest.of(page, limit), mostUsedWords.size());
    }

    @GetMapping("/products")
    public Page<Product> getMostCommentedProducts(
            @RequestParam(required = false, defaultValue = "25") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<Product> mostCommentedProducts = productService.getProductsSortedByMentions();
        return new PageImpl<>(
                mostCommentedProducts, PageRequest.of(page, limit), mostCommentedProducts.size());
    }

    @GetMapping("/users")
    public Page<User> getMostActiveUsers(
            @RequestParam(required = false, defaultValue = "25") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<User> mostActiveUsers = userService.getMostActiveUsers();
        return new PageImpl<>(
                mostActiveUsers, PageRequest.of(page, limit), mostActiveUsers.size());
    }
}
