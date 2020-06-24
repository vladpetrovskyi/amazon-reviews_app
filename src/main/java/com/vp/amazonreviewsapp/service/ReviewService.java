package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.Review;
import java.util.List;

public interface ReviewService {
    void addAll(List<Review> reviewSet);

    List<String> getWordsSortedByUsage();
}
