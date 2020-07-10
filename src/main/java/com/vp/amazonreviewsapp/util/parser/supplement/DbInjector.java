package com.vp.amazonreviewsapp.util.parser.supplement;

import com.vp.amazonreviewsapp.model.AwsUser;
import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.service.ProductService;
import com.vp.amazonreviewsapp.service.ReviewService;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DbInjector {

    private final Logger logger;
    private final UserService<AwsUser> awsUserService;
    private final ProductService productService;
    private final ReviewService reviewService;

    public List<List<?>> addToDb(Set<AwsUser> awsUsers,
                                 Set<Product> products,
                                 List<Review> reviews) {
        List<List<?>> returnList = new ArrayList<>();

        returnList.add(productService.addAll(products));
        logger.info("Finished inserting products into DB.");

        returnList.add(awsUserService.addAll(awsUsers));
        logger.info("Finished inserting users into DB.");

        returnList.add(reviewService.addAll(reviews));
        logger.info("Finished inserting reviews into DB.");

        return returnList;
    }
}
