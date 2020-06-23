package com.vp.amazonreviewsapp.controller;

import com.vp.amazonreviewsapp.service.ProductService;
import com.vp.amazonreviewsapp.service.ReviewService;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
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
    public List<String> getMostUsedWords(@RequestParam Long limit) {
        return reviewService.getMostUsedWords(limit);
    }

    @GetMapping("/products")
    public List<String> getMostCommentedProducts(@RequestParam Long limit) {
        return productService.getMostCommentedProducts(limit);
    }

    @GetMapping("/users")
    public List<String> getMostActiveUsers(@RequestParam Long limit) {
        return userService.getMostActiveUsers(limit);
    }
}
