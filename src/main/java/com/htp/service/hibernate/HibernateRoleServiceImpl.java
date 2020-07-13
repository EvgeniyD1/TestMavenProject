package com.htp.service.hibernate;

import com.htp.dao.hibernate.HibernateRoleDao;
import com.htp.domain.hibernate.HibernateRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateRoleServiceImpl implements HibernateRoleService {

    private final HibernateRoleDao hibernateRoleDao;

    public HibernateRoleServiceImpl(HibernateRoleDao hibernateRoleDao) {
        this.hibernateRoleDao = hibernateRoleDao;
    }

    @Override
    public List<HibernateRole> findAll() {
        return hibernateRoleDao.findAll();
    }

    @Override
    public HibernateRole findOne(Long roleId) {
        return hibernateRoleDao.findOne(roleId);
    }

    @Override
    public Optional<HibernateRole> findById(Long roleId) {
        return hibernateRoleDao.findById(roleId);
    }

    @Override
    public List<HibernateRole> findAllUserRoles(Long userId) {
        return hibernateRoleDao.findAllUserRoles(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateRole save(HibernateRole role) {
        return hibernateRoleDao.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateRole update(HibernateRole role) {
        return hibernateRoleDao.update(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(HibernateRole role) {
        hibernateRoleDao.delete(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public List<HibernateRole> batchInsert(List<HibernateRole> roles) {
        return hibernateRoleDao.batchInsert(roles);
    }
}
