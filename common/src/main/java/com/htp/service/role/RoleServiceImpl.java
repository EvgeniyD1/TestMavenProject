package com.htp.service.role;

import com.htp.dao.RoleRepository;
import com.htp.domain.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"cache"})
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public Page<Role> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Role> findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    @Cacheable
    public List<Role> findAllRolesByUserId(Long userId) {
        return repository.findAllRolesByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Role save(Role user) {
        return repository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(Role user) {
        repository.delete(user);
    }
}
