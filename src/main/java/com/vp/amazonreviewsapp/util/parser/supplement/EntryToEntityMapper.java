package com.vp.amazonreviewsapp.util.parser.supplement;

import com.vp.amazonreviewsapp.model.AwsUser;
import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.model.Role;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TimeZone;
import org.springframework.stereotype.Component;

@Component
public class EntryToEntityMapper {

    private static final int REVIEW_ID = 0;
    private static final int PRODUCT_ID = 1;
    private static final int USER_ID = 2;
    private static final int PROFILE_NAME = 3;
    private static final int HELPFULNESS_NUMERATOR = 4;
    private static final int HELPFULNESS_DENOMINATOR = 5;
    private static final int SCORE = 6;
    private static final int TIME = 7;
    private static final int SUMMARY = 8;
    private static final int TEXT = 9;

    public AwsUser getUserFromEntry(String[] entry) {
        AwsUser awsUser = new AwsUser();
        awsUser.setUserId(entry[USER_ID]);
        awsUser.setProfileName(entry[PROFILE_NAME]);
        return awsUser;
    }

    public Product getProductFromEntry(String[] entry) {
        Product product = new Product();
        product.setProductId(entry[PRODUCT_ID]);
        return product;
    }

    public Review getReviewFromEntry(String[] entry, AwsUser awsUser, Product product) {
        Review review = new Review();
        review.setId(Long.parseLong(entry[REVIEW_ID]));
        review.setProduct(product);
        review.setAwsUser(awsUser);
        review.setHelpfulnessNumerator(Integer.parseInt(entry[HELPFULNESS_NUMERATOR]));
        review.setHelpfulnessDenominator(Integer.parseInt(entry[HELPFULNESS_DENOMINATOR]));
        review.setScore(Integer.parseInt(entry[SCORE]));
        review.setTime(LocalDateTime.ofInstant(
                Instant.ofEpochSecond(Long.parseLong(entry[TIME])),
                TimeZone.getDefault().toZoneId()));
        review.setSummary(entry[SUMMARY]);
        review.setText(entry[TEXT]);
        return review;
    }
}
