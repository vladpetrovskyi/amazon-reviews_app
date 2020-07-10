package com.vp.amazonreviewsapp.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService<U> {
    U add(U user);

    U getById(String id);

    U getByProfileName(String profileName);

    List<U> addAll(Iterable<U> users);

    Page<U> findAll(Pageable pageable);

    List<U> findAll();
}
