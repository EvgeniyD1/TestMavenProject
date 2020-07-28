package com.htp.service.activityrequest;

import com.htp.domain.HibernateActivitiesRequest;

import java.util.List;
import java.util.Optional;

public interface ActivityRequestSDService {

    Optional<HibernateActivitiesRequest> findById(Long activitiesId);

    List<HibernateActivitiesRequest> findAllActivitiesRequestByActivitiesId(Long activityId);

    HibernateActivitiesRequest save(HibernateActivitiesRequest activitiesRequest);

    void delete(HibernateActivitiesRequest activitiesRequest);
}
