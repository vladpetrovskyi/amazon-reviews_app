package com.vp.amazonreviewsapp.service.impl;

import com.vp.amazonreviewsapp.model.Role;
import com.vp.amazonreviewsapp.repository.RoleRepository;
import com.vp.amazonreviewsapp.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByRoleName(Role.RoleName.valueOf(name)).orElseThrow();
    }
}
