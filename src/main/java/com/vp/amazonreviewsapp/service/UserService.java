package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User add(User user);

    User getById(String id);

    List<User> addAll(Iterable<User> users);

    Page<User> findAll(Pageable pageable);

    List<User> findAll();
}
