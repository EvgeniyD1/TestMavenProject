package com.htp.service.hibernate;

import com.htp.domain.hibernate.HibernateRole;

import java.util.List;
import java.util.Optional;

public interface HibernateRoleService {

    List<HibernateRole> findAll();

    HibernateRole findOne(Long roleId);

    Optional<HibernateRole> findById(Long roleId);

    List<HibernateRole> findAllUserRoles(Long userId);

    HibernateRole save(HibernateRole role);

    HibernateRole update(HibernateRole role);

    void delete(HibernateRole role);

    List<HibernateRole> batchInsert(List<HibernateRole> roles);
}
