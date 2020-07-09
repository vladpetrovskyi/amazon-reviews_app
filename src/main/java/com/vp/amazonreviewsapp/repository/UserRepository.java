package com.vp.amazonreviewsapp.repository;

import com.vp.amazonreviewsapp.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByProfileName(String profileName);
}
