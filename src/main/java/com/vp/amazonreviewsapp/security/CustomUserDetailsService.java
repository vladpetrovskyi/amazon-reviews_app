package com.vp.amazonreviewsapp.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService<User> userService;

    @Override
    public UserDetails loadUserByUsername(String profileName) throws UsernameNotFoundException {
        User user = userService.getByProfileName(profileName);
        UserBuilder builder;
        if (user != null) {
            builder = withUsername(profileName);
            builder.password(user.getPassword());
            builder.roles(user.getRoles().stream()
                    .map(role -> role.getRoleName().name())
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}
