package com.vp.amazonreviewsapp.controller;

import com.vp.amazonreviewsapp.exception.DataProcessingException;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.model.dto.mapper.ItemMapper;
import com.vp.amazonreviewsapp.model.dto.request.ReviewRequestDto;
import com.vp.amazonreviewsapp.model.dto.response.ReviewResponseDto;
import com.vp.amazonreviewsapp.service.ReviewService;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/reviews")
public class ReviewsController {

    private final ReviewService reviewService;
    private final UserService<User> userService;
    private final ItemMapper<Review, ReviewRequestDto, ReviewResponseDto> reviewMapper;

    @PostMapping
    public ReviewResponseDto createReview(ReviewRequestDto review) {
        return reviewMapper.toDto(reviewService.add(reviewMapper.toEntity(review)));
    }

    @GetMapping
    public List<ReviewResponseDto> getUsersReviews(Authentication auth) {
        return reviewService.getByUser(userService.getByProfileName(getProfileName(auth)))
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ReviewResponseDto updateReview(ReviewRequestDto review) {
        return reviewMapper.toDto(reviewService.add(reviewMapper.toEntity(review)));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReview(@PathVariable Long id) {
        try {
            reviewService.delete(id);
        } catch (DataProcessingException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
    }

    private String getProfileName(Authentication auth) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        return principal.getUsername();
    }
}
