package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.ActivitiesRequestUpdateRequest;
import com.htp.domain.ActivitiesRequest;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivityRequestUpdateRequestConverter
        extends ActivityRequestRequestConverter<ActivitiesRequestUpdateRequest,
        ActivitiesRequest> {

    @Override
    public ActivitiesRequest convert(ActivitiesRequestUpdateRequest request) {

        ActivitiesRequest activitiesRequest = Optional
                .ofNullable(entityManager.find(ActivitiesRequest.class,
                        request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(activitiesRequest, request);
    }
}
