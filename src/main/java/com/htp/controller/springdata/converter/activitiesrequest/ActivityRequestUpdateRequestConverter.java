package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.ActivitiesRequestSDUpdateRequest;
import com.htp.domain.hibernate.HibernateActivitiesRequest;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivityRequestUpdateRequestConverter
        extends ActivityRequestRequestConverter<ActivitiesRequestSDUpdateRequest,
                HibernateActivitiesRequest> {

    @Override
    public HibernateActivitiesRequest convert(ActivitiesRequestSDUpdateRequest request) {

        HibernateActivitiesRequest activitiesRequest = Optional
                .ofNullable(entityManager.find(HibernateActivitiesRequest.class,
                        request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(activitiesRequest, request);
    }
}
