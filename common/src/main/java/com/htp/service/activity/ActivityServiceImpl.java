package com.htp.service.activity;

import com.htp.dao.ActivitiesRepository;
import com.htp.domain.Activities;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"cache"})
public class ActivityServiceImpl implements ActivityService {

    private final ActivitiesRepository repository;

    public ActivityServiceImpl(ActivitiesRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public Page<Activities> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Activities> criteriaSpecification(Specification<Activities> spec) {
        return repository.findAll(spec);
    }

    @Override
    public Optional<Activities> findById(Long activitiesId) {
        return repository.findById(activitiesId);
    }

    @Override
    @Cacheable
    public List<Activities> findByType(String type) {
        return repository.findByType(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Activities save(Activities activities) {
        return repository.save(activities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(Activities activities) {
        repository.delete(activities);
    }
}
