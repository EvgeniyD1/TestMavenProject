package com.htp.service.role;

import com.htp.dao.RoleSDRepository;
import com.htp.domain.HibernateRole;
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
public class RoleSDServiceImpl implements RoleSDService{

    private final RoleSDRepository repository;

    public RoleSDServiceImpl(RoleSDRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public Page<HibernateRole> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<HibernateRole> findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    @Cacheable
    public List<HibernateRole> findAllRolesByUserId(Long userId) {
        return repository.findAllRolesByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateRole save(HibernateRole user) {
        return repository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(HibernateRole user) {
        repository.delete(user);
    }
}
