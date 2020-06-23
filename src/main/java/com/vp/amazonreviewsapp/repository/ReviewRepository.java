package com.vp.amazonreviewsapp.repository;

import com.vp.amazonreviewsapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
