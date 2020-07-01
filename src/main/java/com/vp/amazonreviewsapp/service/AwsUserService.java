package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.AwsUser;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AwsUserService {
    AwsUser add(AwsUser awsUser);

    AwsUser getById(String id);

    List<AwsUser> addAll(Iterable<AwsUser> users);

    Page<AwsUser> findAll(Pageable pageable);

    List<AwsUser> findAll();
}
