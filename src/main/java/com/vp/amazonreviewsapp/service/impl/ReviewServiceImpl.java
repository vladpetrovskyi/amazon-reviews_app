package com.vp.amazonreviewsapp.service.impl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.repository.ReviewRepository;
import com.vp.amazonreviewsapp.service.ReviewService;
import com.vp.amazonreviewsapp.service.supplement.BatchInserter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BatchInserter<Review> batchInserter;
    private List<String> sortedWords;

    @Override
    public List<Review> addAll(List<Review> reviews) {
        sortedWords = null;
        List<CompletableFuture<List<Review>>> futures = new ArrayList<>();
        for (List<Review> reviewList : Lists.partition(reviews, 1000)) {
            futures.add(batchInserter.insertBatch(reviewList));
        }
        return futures.stream()
                .flatMap(f -> f.join().parallelStream())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getWordsSortedByUsage() {
        if (sortedWords == null) {
            Multiset<String> words = reviewRepository.getAllText().join()
                    .parallelStream()
                    .flatMap(text ->
                            Arrays.stream(text.toLowerCase()
                                    .replaceAll("[^a-z'\\-]", " ")
                                    .split("\\s+")))
                    .collect(Collectors.toCollection(HashMultiset::create));

            sortedWords = words.entrySet().parallelStream()
                    .sorted(Comparator.comparingInt(
                            (ToIntFunction<Multiset.Entry<String>>) Multiset.Entry::getCount)
                            .reversed()
                            .thenComparing(Multiset.Entry::getElement))
                    .map(Multiset.Entry::getElement)
                    .collect(Collectors.toList());
        }
        return sortedWords;
    }
}
