package com.htp.service.activityrequest;

import com.htp.domain.ActivitiesRequest;

import java.util.List;
import java.util.Optional;

public interface ActivityRequestService {

    Optional<ActivitiesRequest> findById(Long activitiesId);

    List<ActivitiesRequest> findAllActivitiesRequestByActivitiesId(Long activityId);

    ActivitiesRequest save(ActivitiesRequest activitiesRequest);

    void delete(ActivitiesRequest activitiesRequest);
}
