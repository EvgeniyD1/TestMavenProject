package com.htp.service.activity;

import com.htp.domain.Activities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    Page<Activities> findAll(Pageable pageable);

    List<Activities> criteriaSpecification(Specification<Activities> spec);

    Optional<Activities> findById(Long activitiesId);

    List<Activities> findByType(String type);

    Activities save(Activities activities);

    void delete(Activities activities);
}
