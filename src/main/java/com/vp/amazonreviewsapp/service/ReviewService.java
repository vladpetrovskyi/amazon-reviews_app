package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.exception.DataProcessingException;
import com.vp.amazonreviewsapp.model.GenericUser;
import com.vp.amazonreviewsapp.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> addAll(List<Review> reviewSet);

    List<String> getWordsSortedByUsage();

    void delete(Long id) throws DataProcessingException;

    Review add(Review review);

    List<Review> getByUser(GenericUser user);
}
