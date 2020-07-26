package com.htp.service.springdata.activityrequest;

import com.htp.domain.hibernate.HibernateActivitiesRequest;

import java.util.List;
import java.util.Optional;

public interface ActivityRequestSDService {

    Optional<HibernateActivitiesRequest> findById(Long activitiesId);

    List<HibernateActivitiesRequest> findAllActivitiesRequestByActivitiesId(Long activityId);

    HibernateActivitiesRequest save(HibernateActivitiesRequest activitiesRequest);

    void delete(HibernateActivitiesRequest activitiesRequest);
}
