package com.htp.service.role;

import com.htp.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Page<Role> findAll(Pageable pageable);

    Optional<Role> findById(Long userId);

    List<Role> findAllRolesByUserId(Long userId);

    Role save(Role user);

    void delete(Role user);
}
