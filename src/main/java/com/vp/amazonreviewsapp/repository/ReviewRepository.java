package com.vp.amazonreviewsapp.repository;

import com.vp.amazonreviewsapp.model.GenericUser;
import com.vp.amazonreviewsapp.model.Review;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Async("taskExecutor")
    @Query("select r.text from Review r")
    CompletableFuture<Set<String>> getAllText();

    List<Review> getAllByUser(GenericUser user);
}
