package com.htp.service.activityrequest;

import com.htp.dao.ActivitiesRequestRepository;
import com.htp.domain.ActivitiesRequest;
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
public class ActivityRequestServiceImpl implements ActivityRequestService {

    private final ActivitiesRequestRepository repository;

    public ActivityRequestServiceImpl(ActivitiesRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ActivitiesRequest> findById(Long activitiesId) {
        return repository.findById(activitiesId);
    }

    @Override
    @Cacheable
    public List<ActivitiesRequest> findAllActivitiesRequestByActivitiesId(Long activityId) {
        return repository.findAllActivitiesRequestByActivitiesId(activityId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ActivitiesRequest save(ActivitiesRequest activitiesRequest) {
        return repository.save(activitiesRequest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(ActivitiesRequest activitiesRequest) {
        repository.delete(activitiesRequest);
    }
}
