package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.User;
import java.util.List;

public interface UserService {
    User getById(String id);

    void addAll(Iterable<User> users);

    List<User> getMostActiveUsers();
}
