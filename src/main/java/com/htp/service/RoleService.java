package com.htp.service;

import com.htp.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();

    List<Role> search(String searchParam);

    Optional<Role> findById(Long roleId);

    Role findOne(Long roleId);

    List<Role> findAllRole(Long userId);

    Role save(Role role);

    Role update(Role role);

    int delete(Role role);

    List<Role> batchUpdate(List<Role> roles);
}
