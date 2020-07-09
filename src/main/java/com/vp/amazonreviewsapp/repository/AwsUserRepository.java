package com.vp.amazonreviewsapp.repository;

import com.vp.amazonreviewsapp.model.AwsUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwsUserRepository extends JpaRepository<AwsUser, String> {
    Optional<AwsUser> findByProfileName(String profileName);
}
