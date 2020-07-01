package com.vp.amazonreviewsapp.controller;

import com.vp.amazonreviewsapp.model.Role;
import com.vp.amazonreviewsapp.model.User;
import com.vp.amazonreviewsapp.service.RoleService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class InjectDataController {

    private final RoleService roleService;

    @PostConstruct
    public void inject() {
        roleService.add(Role.of("USER"));
        roleService.add(Role.of("ADMIN"));

        User admin = new User();
        admin.setProfileName("admin");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        //INSERT ADMIN

    }
}
