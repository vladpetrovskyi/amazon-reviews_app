package com.vp.amazonreviewsapp.util;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.vp.amazonreviewsapp.exception.ParsingException;
import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.model.Role;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.service.ProductService;
import com.vp.amazonreviewsapp.service.ReviewService;
import com.vp.amazonreviewsapp.service.RoleService;
import com.vp.amazonreviewsapp.service.UserService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CSVParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVParser.class);

    private final UserService userService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final RoleService roleService;

    public void parseData(String path) {
        Set<User> users = new HashSet<>();
        Set<Product> products = new HashSet<>();
        List<Review> reviews = new ArrayList<>();

        injectRoles();

        User admin = new User();
        admin.setProfileName("admin");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            CsvParserSettings settings = new CsvParserSettings();
            settings.setMaxCharsPerColumn(1000000);
            settings.setNumberOfRowsToSkip(1);

            Set<Role> role = Set.of(roleService.getRoleByName("USER"));
            CsvParser parser = new CsvParser(settings);
            LOGGER.info("Starting parsing.");
            for (String[] entry : parser.parseAll(br)) {
                User user = getUserFromEntry(entry, role);
                users.add(user);

                Product product = getProductFromEntry(entry);
                products.add(product);

                reviews.add(getReviewFromEntry(entry, user, product));
            }
            LOGGER.info("Finished parsing.");
            addToDb(users, products, reviews);
            LOGGER.info("Finished inserting all entries into DB.");
        } catch (IOException e) {
            throw new ParsingException("Failed to parse the input file.", e);
        }
    }

    private void injectRoles() {
        roleService.add(Role.of("USER"));
        roleService.add(Role.of("ADMIN"));
    }

    private void addToDb(Set<User> users, Set<Product> products, List<Review> reviews) {
        userService.addAll(users);
        LOGGER.info("Finished inserting users into DB.");
        productService.addAll(products);
        LOGGER.info("Finished inserting products into DB.");
        reviewService.addAll(reviews);
        LOGGER.info("Finished inserting reviews into DB.");
    }

    private User getUserFromEntry(String[] entry, Set<Role> role) {
        User user = new User();
        user.setUserId(entry[2]);
        user.setProfileName(entry[3]);
        user.setRoles(role);
        return user;
    }

    private Product getProductFromEntry(String[] entry) {
        Product product = new Product();
        product.setProductId(entry[1]);
        return product;
    }

    private Review getReviewFromEntry(String[] entry, User user, Product product) {
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
