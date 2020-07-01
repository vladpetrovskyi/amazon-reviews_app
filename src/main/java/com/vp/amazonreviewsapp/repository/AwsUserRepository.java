package com.vp.amazonreviewsapp.repository;

import com.vp.amazonreviewsapp.model.AwsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwsUserRepository extends JpaRepository<AwsUser, String> {
}
