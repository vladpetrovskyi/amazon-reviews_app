package com.vp.amazonreviewsapp.service.impl;

import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.repository.UserRepository;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void addAll(Iterable<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public List<String> getMostActiveUsers(Long limit) {
        return userRepository.findAll()
                .stream()
                .sorted((u1, u2) -> u2.getReviews().size() - u1.getReviews().size())
                .map(User::getUserId)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
