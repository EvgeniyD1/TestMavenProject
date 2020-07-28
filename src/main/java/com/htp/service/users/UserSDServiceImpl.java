package com.htp.service.users;

import com.htp.dao.UserSDRepository;
import com.htp.domain.HibernateUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"cache"})
public class UserSDServiceImpl implements UserSDService {

    private final UserSDRepository repository;

    public UserSDServiceImpl(UserSDRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public Page<HibernateUser> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Cacheable
    public Optional<HibernateUser> findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    @Cacheable
    public Optional<HibernateUser> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateUser save(HibernateUser user) {
        return repository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(HibernateUser user) {
        repository.delete(user);
    }
}
