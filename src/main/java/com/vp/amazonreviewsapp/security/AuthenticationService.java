package com.vp.amazonreviewsapp.security;

import com.vp.amazonreviewsapp.exception.AuthenticationException;
import com.vp.amazonreviewsapp.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
