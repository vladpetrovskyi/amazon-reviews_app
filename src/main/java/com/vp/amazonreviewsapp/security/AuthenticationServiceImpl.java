package com.vp.amazonreviewsapp.security;

import com.vp.amazonreviewsapp.exception.AuthenticationException;
import com.vp.amazonreviewsapp.exception.RegistrationException;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.service.RoleService;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService<User> userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String profileName, String pass) throws AuthenticationException {
        User userFromDb = userService.getByProfileName(profileName);
        if (userFromDb != null && passwordEncoder.matches(pass, userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String profileName, String password) {
        User user = new User();
        if (userService.getByProfileName(profileName) != null) {
            throw new RegistrationException("This profile name is already taken.");
        }
        user.setProfileName(profileName);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        return userService.add(user);
    }
}
