package com.htp.service.role;

import com.htp.domain.HibernateRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleSDService {

    Page<HibernateRole> findAll(Pageable pageable);

    Optional<HibernateRole> findById(Long userId);

    List<HibernateRole> findAllRolesByUserId(Long userId);

    HibernateRole save(HibernateRole user);

    void delete(HibernateRole user);
}
