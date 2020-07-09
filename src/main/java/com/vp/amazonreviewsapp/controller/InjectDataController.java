package com.vp.amazonreviewsapp.controller;

import com.vp.amazonreviewsapp.model.Role;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.service.RoleService;
import com.vp.amazonreviewsapp.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class InjectDataController {

    private final RoleService roleService;
    private final UserService<User> userService;

    @PostConstruct
    public void inject() {
        roleService.add(Role.of("USER"));
        roleService.add(Role.of("ADMIN"));

        User admin = new User();
        admin.setProfileName("admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(admin);

        User user = new User();
        user.setProfileName("user");
        user.setPassword(new BCryptPasswordEncoder().encode("user"));
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.add(user);
    }
}
