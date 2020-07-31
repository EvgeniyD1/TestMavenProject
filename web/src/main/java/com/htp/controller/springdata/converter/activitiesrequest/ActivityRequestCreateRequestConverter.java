package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.ActivitiesRequestSDSaveRequest;
import com.htp.domain.HibernateActivities;
import com.htp.domain.HibernateActivitiesRequest;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivityRequestCreateRequestConverter
        extends ActivityRequestRequestConverter<ActivitiesRequestSDSaveRequest,
                HibernateActivitiesRequest> {

    @Override
    public HibernateActivitiesRequest convert(ActivitiesRequestSDSaveRequest request) {

        HibernateActivitiesRequest activitiesRequest = new HibernateActivitiesRequest();

        HibernateActivities activities = Optional.ofNullable(
                entityManager.find(HibernateActivities.class, request.getRealEstateActivityId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        activitiesRequest.setActivities(activities);
        activitiesRequest.setUserLink(request.getUserLink());

        return doConvert(activitiesRequest, request);
    }
}
