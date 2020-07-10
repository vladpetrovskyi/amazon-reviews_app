package com.vp.amazonreviewsapp.model.dto.mapper.impl;

import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.model.dto.mapper.ItemMapper;
import com.vp.amazonreviewsapp.model.dto.request.ReviewRequestDto;
import com.vp.amazonreviewsapp.model.dto.response.ReviewResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements ItemMapper<Review, ReviewRequestDto, ReviewResponseDto> {
    @Override
    public Review toEntity(ReviewRequestDto dto) {
        Review review = new Review();
        review.setId(dto.getId());
        User user = new User();
        user.setProfileName(dto.getProfileName());
        review.setUser(user);
        review.setText(dto.getText());
        review.setSummary(dto.getSummary());
        review.setTime(dto.getTime());
        Product product = new Product();
        product.setProductId(dto.getProductId());
        review.setProduct(product);
        return review;
    }

    @Override
    public ReviewResponseDto toDto(Review entity) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProduct().getProductId());
        dto.setHelpfulnessDenominator(entity.getHelpfulnessDenominator());
        dto.setHelpfulnessNumerator(entity.getHelpfulnessNumerator());
        dto.setScore(entity.getScore());
        dto.setSummary(entity.getSummary());
        dto.setText(entity.getText());
        dto.setTime(entity.getTime());
        if (entity.getUser().getClass().equals(User.class)) {
            dto.setProfileName(entity.getUser().getProfileName());
        }
        return null;
    }
}
