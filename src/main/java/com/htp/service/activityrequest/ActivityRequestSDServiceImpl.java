package com.htp.service.activityrequest;

import com.htp.dao.ActivitiesRequestSDRepository;
import com.htp.domain.HibernateActivitiesRequest;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"cache"})
public class ActivityRequestSDServiceImpl implements ActivityRequestSDService{

    private final ActivitiesRequestSDRepository repository;

    public ActivityRequestSDServiceImpl(ActivitiesRequestSDRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public Optional<HibernateActivitiesRequest> findById(Long activitiesId) {
        return repository.findById(activitiesId);
    }

    @Override
    @Cacheable
    public List<HibernateActivitiesRequest> findAllActivitiesRequestByActivitiesId(Long activityId) {
        return repository.findAllActivitiesRequestByActivitiesId(activityId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateActivitiesRequest save(HibernateActivitiesRequest activitiesRequest) {
        return repository.save(activitiesRequest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(HibernateActivitiesRequest activitiesRequest) {
        repository.delete(activitiesRequest);
    }
}
