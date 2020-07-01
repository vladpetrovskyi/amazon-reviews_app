package com.vp.amazonreviewsapp.service;

import com.vp.amazonreviewsapp.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getRoleByName(String name);
}
