package com.vp.amazonreviewsapp.service.impl;

import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.repository.ReviewRepository;
import com.vp.amazonreviewsapp.service.ReviewService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final ReviewRepository reviewRepository;

    @Override
    public void addAll(List<Review> reviews) {
        int counter = 0;
        while (counter < reviews.size() && reviews.size() >= 1000) {
            reviewRepository.saveAll(reviews.subList(counter, counter + 1000));
            counter += 1000;
            if (counter % 10000 == 0) {
                LOGGER.info("Added new 10000 entries batch.");
            }
        }
        reviewRepository.saveAll(
                reviews.subList(counter > 1000 ? counter - 1000 : 0, reviews.size()));
    }

    @Override
    public List<String> getWordsSortedByUsage() {
        List<String> words = reviewRepository.findAll().stream()
                .flatMap(review ->
                        Arrays.stream(
                                review.getText()
                                        .replaceAll("\\W", " ")
                                        .toLowerCase()
                                        .split(" ")))
                .collect(Collectors.toList());
        Iterator<String> wordsIterator = words.iterator();
        Map<String, Integer> wordOccurrences = new HashMap<>();
        while (wordsIterator.hasNext()) {
            String word = wordsIterator.next();
            wordOccurrences.put(word, wordOccurrences.getOrDefault(word, 0) + 1);
            wordsIterator.remove();
        }

        return new ArrayList<>(sortByValue(wordOccurrences).keySet());
    }

    private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
