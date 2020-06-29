package com.vp.amazonreviewsapp.util.parser;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.vp.amazonreviewsapp.exception.ParsingException;
import com.vp.amazonreviewsapp.model.Product;
import com.vp.amazonreviewsapp.model.Review;
import com.vp.amazonreviewsapp.model.Role;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.service.RoleService;
import com.vp.amazonreviewsapp.util.parser.supplement.DbInjector;
import com.vp.amazonreviewsapp.util.parser.supplement.EntryToEntityMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CsvParseUtil implements Parser {

    private final Logger logger;
    private final DbInjector dbInjector;
    private final RoleService roleService;
    private final EntryToEntityMapper mapper;

    public List<List<?>> parseData(String path) {
        Set<User> users = new HashSet<>();
        Set<Product> products = new HashSet<>();
        List<Review> reviews = new ArrayList<>();
        List<Role> roles;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            CsvParserSettings settings = new CsvParserSettings();
            settings.setMaxCharsPerColumn(1000000);
            settings.setNumberOfRowsToSkip(1);

            roles = new ArrayList<>(dbInjector.injectRoles());

            Set<Role> role = Set.of(roleService.getRoleByName("USER"));

            logger.info("Starting parsing.");
            for (String[] entry : new CsvParser(settings).parseAll(br)) {
                User user = mapper.getUserFromEntry(entry, role);
                users.add(user);

                Product product = mapper.getProductFromEntry(entry);
                products.add(product);

                reviews.add(mapper.getReviewFromEntry(entry, user, product));
            }
            logger.info("Finished parsing.");
        } catch (IOException e) {
            throw new ParsingException("Failed to parse the input file.", e);
        }
        dbInjector.injectAdmin();

        logger.info("Starting inserting all entries into DB.");
        List<List<?>> returnList = dbInjector.addToDb(users, products, reviews);
        logger.info("Finished inserting all entries into DB.");

        returnList.add(roles);
        return returnList;
    }
}
