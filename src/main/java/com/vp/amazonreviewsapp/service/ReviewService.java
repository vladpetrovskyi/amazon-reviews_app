package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.Review;
import java.util.List;
import java.util.Set;

public interface ReviewService {
    Review add(Review review);

    List<Review> getAll();

    void addAll(List<Review> reviewSet);

    List<String> getMostUsedWords(Long limit);
}
