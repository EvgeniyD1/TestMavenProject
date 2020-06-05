package com.htp.dao;

import com.htp.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {

    List<Role> findAll();

    List<Role> search(String searchParam);

    Optional<Role> findById(Long roleId);

    Role findOne(Long roleId);

    Role save(Role role);

    Role update(Role role);

    int delete(Role role);

    List<Role> batch(Role role);
}
