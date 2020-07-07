package com.htp.service;

import com.htp.dao.RoleDao;
import com.htp.domain.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(@Qualifier("roleRepositoryJdbcTemplate") RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> search(String searchParam) {
        return roleDao.search(searchParam);
    }

    @Override
    public Optional<Role> findById(Long roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    @Override
    public List<Role> findAllRole(Long userId) {
        return roleDao.findAllRole(userId);
    }

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public int delete(Role role) {
        return roleDao.delete(role);
    }

    @Override
    public List<Role> batchUpdate(List<Role> roles) {
        return roleDao.batchUpdate(roles);
    }
}
