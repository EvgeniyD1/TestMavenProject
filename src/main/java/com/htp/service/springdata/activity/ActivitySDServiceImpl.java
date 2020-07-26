package com.htp.service.springdata.activity;

import com.htp.dao.springdata.ActivitiesSDRepository;
import com.htp.domain.hibernate.HibernateActivities;
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
public class ActivitySDServiceImpl implements ActivitySDService{

    private final ActivitiesSDRepository repository;

    public ActivitySDServiceImpl(ActivitiesSDRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public Page<HibernateActivities> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Cacheable
    public Optional<HibernateActivities> findById(Long activitiesId) {
        return repository.findById(activitiesId);
    }

    @Override
    @Cacheable
    public List<HibernateActivities> findByType(String type) {
        return repository.findByType(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateActivities save(HibernateActivities activities) {
        return repository.save(activities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(HibernateActivities activities) {
        repository.delete(activities);
    }
}
