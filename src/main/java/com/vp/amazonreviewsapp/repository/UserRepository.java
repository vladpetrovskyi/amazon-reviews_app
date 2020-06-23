package com.vp.amazonreviewsapp.repository;


import com.vp.amazonreviewsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
