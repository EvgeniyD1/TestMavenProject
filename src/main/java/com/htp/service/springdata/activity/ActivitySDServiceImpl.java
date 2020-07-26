package com.htp.service.springdata.activity;

import com.htp.dao.springdata.ActivitiesSDRepository;
import com.htp.domain.hibernate.HibernateActivities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivitySDServiceImpl implements ActivitySDService{

    private final ActivitiesSDRepository repository;

    public ActivitySDServiceImpl(ActivitiesSDRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<HibernateActivities> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<HibernateActivities> findById(Long activitiesId) {
        return repository.findById(activitiesId);
    }

    @Override
    public List<HibernateActivities> findByType(String type) {
        return repository.findByType(type);
    }

    @Override
    public HibernateActivities save(HibernateActivities activities) {
        return repository.save(activities);
    }

    @Override
    public void delete(HibernateActivities activities) {
        repository.delete(activities);
    }
}
