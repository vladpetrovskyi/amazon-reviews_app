package com.vp.amazonreviewsapp.service.impl;

import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.repository.UserRepository;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> addAll(Iterable<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
