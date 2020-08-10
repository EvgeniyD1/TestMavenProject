package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.ActivitiesRequestSaveRequest;
import com.htp.domain.Activities;
import com.htp.domain.ActivitiesRequest;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivityRequestCreateRequestConverter
        extends ActivityRequestRequestConverter<ActivitiesRequestSaveRequest,
        ActivitiesRequest> {

    @Override
    public ActivitiesRequest convert(ActivitiesRequestSaveRequest request) {

        ActivitiesRequest activitiesRequest = new ActivitiesRequest();

        Activities activities = Optional.ofNullable(
                entityManager.find(Activities.class, request.getRealEstateActivityId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        activitiesRequest.setActivities(activities);
        activitiesRequest.setUserLink(request.getUserLink());

        return doConvert(activitiesRequest, request);
    }
}
