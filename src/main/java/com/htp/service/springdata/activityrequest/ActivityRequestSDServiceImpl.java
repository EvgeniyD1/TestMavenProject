package com.htp.service.springdata.activityrequest;

import com.htp.dao.springdata.ActivitiesRequestSDRepository;
import com.htp.domain.hibernate.HibernateActivitiesRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityRequestSDServiceImpl implements ActivityRequestSDService{

    private final ActivitiesRequestSDRepository repository;

    public ActivityRequestSDServiceImpl(ActivitiesRequestSDRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<HibernateActivitiesRequest> findById(Long activitiesId) {
        return repository.findById(activitiesId);
    }

    @Override
    public List<HibernateActivitiesRequest> findAllActivitiesRequestByActivitiesId(Long activityId) {
        return repository.findAllActivitiesRequestByActivitiesId(activityId);
    }

    @Override
    public HibernateActivitiesRequest save(HibernateActivitiesRequest activitiesRequest) {
        return repository.save(activitiesRequest);
    }

    @Override
    public void delete(HibernateActivitiesRequest activitiesRequest) {
        repository.delete(activitiesRequest);
    }
}
