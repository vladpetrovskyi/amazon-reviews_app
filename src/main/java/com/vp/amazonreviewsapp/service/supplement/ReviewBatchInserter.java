package com.vp.amazonreviewsapp.service.supplement;

import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.repository.ReviewRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewBatchInserter implements BatchInserter<Review> {

    private final ReviewRepository reviewRepository;

    @Async("taskExecutor")
    public CompletableFuture<List<Review>> insertBatch(List<Review> reviews) {
        return CompletableFuture.completedFuture(reviewRepository.saveAll(reviews));
    }
}
