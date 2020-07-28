package com.htp.service.activity;

import com.htp.domain.HibernateActivities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivitySDService {

    Page<HibernateActivities> findAll(Pageable pageable);

    Optional<HibernateActivities> findById(Long activitiesId);

    List<HibernateActivities> findByType(String type);

    HibernateActivities save(HibernateActivities activities);

    void delete(HibernateActivities activities);
}
