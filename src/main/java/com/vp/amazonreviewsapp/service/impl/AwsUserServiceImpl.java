package com.vp.amazonreviewsapp.service.impl;

import com.vp.amazonreviewsapp.model.AwsUser;
import com.vp.amazonreviewsapp.repository.AwsUserRepository;
import com.vp.amazonreviewsapp.service.AwsUserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AwsUserServiceImpl implements AwsUserService {

    private final AwsUserRepository userRepository;

    @Override
    public AwsUser add(AwsUser awsUser) {
        return userRepository.save(awsUser);
    }

    @Override
    public AwsUser getById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<AwsUser> addAll(Iterable<AwsUser> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public Page<AwsUser> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<AwsUser> findAll() {
        return userRepository.findAll();
    }
}
