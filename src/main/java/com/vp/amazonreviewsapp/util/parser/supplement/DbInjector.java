package com.vp.amazonreviewsapp.util.parser.supplement;

import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.model.Role;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.service.ProductService;
import com.vp.amazonreviewsapp.service.ReviewService;
import com.vp.amazonreviewsapp.service.RoleService;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DbInjector {

    private final Logger logger;
    private final RoleService roleService;
    private final UserService userService;
    private final ProductService productService;
    private final ReviewService reviewService;

    public List<Role> injectRoles() {
        List<Role> roleSet = new ArrayList<>();
        roleSet.add(roleService.add(Role.of("USER")));
        roleSet.add(roleService.add(Role.of("ADMIN")));
        return roleSet;
    }

    public List<List<?>> addToDb(Set<User> users, Set<Product> products, List<Review> reviews) {
        List<List<?>> returnList = new ArrayList<>();

        returnList.add(userService.addAll(users));
        logger.info("Finished inserting users into DB.");

        returnList.add(productService.addAll(products));
        logger.info("Finished inserting products into DB.");

        returnList.add(reviewService.addAll(reviews));
        logger.info("Finished inserting reviews into DB.");

        return returnList;
    }

    public User injectAdmin() {
        User admin = new User();
        admin.setProfileName("admin");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        admin.setUserId("ZERO_ID");
        return userService.add(admin);
    }
}
