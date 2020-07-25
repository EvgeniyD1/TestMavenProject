package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.REActivitiesRequestSDUpdateRequest;
import com.htp.domain.hibernate.HibernateRealEstateActivitiesRequest;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class REActivityRequestUpdateRequestConverter
        extends REActivityRequestRequestConverter<REActivitiesRequestSDUpdateRequest,
        HibernateRealEstateActivitiesRequest>{

    @Override
    public HibernateRealEstateActivitiesRequest convert(REActivitiesRequestSDUpdateRequest request) {

        HibernateRealEstateActivitiesRequest activitiesRequest = Optional
                .ofNullable(entityManager.find(HibernateRealEstateActivitiesRequest.class,
                        request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setUserLink(activitiesRequest.getUserLink());

        return doConvert(activitiesRequest, request);
    }
}
