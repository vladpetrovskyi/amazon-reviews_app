package com.vp.amazonreviewsapp.util.parser.supplement;

import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.model.Role;
import com.vp.amazonreviewsapp.model.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TimeZone;
import org.springframework.stereotype.Component;

@Component
public class EntryToEntityMapper {

    public User getUserFromEntry(String[] entry, Set<Role> role) {
        User user = new User();
        user.setUserId(entry[2]);
        user.setProfileName(entry[3]);
        user.setRoles(role);
        return user;
    }

    public Product getProductFromEntry(String[] entry) {
        Product product = new Product();
        product.setProductId(entry[1]);
        return product;
    }

    public Review getReviewFromEntry(String[] entry, User user, Product product) {
        Review review = new Review();
        review.setId(Long.parseLong(entry[0]));
        review.setProduct(product);
        review.setUser(user);
        review.setHelpfulnessNumerator(Integer.parseInt(entry[4]));
        review.setHelpfulnessDenominator(Integer.parseInt(entry[5]));
        review.setScore(Integer.parseInt(entry[6]));
        review.setTime(LocalDateTime.ofInstant(
                Instant.ofEpochSecond(Long.parseLong(entry[7])),
                TimeZone.getDefault().toZoneId()));
        review.setSummary(entry[8]);
        review.setText(entry[9]);
        return review;
    }
}
