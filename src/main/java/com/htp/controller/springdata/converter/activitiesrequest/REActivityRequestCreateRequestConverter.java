package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.REActivitiesRequestSDSaveRequest;
import com.htp.domain.hibernate.HibernateRealEstateActivities;
import com.htp.domain.hibernate.HibernateRealEstateActivitiesRequest;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class REActivityRequestCreateRequestConverter
        extends REActivityRequestRequestConverter<REActivitiesRequestSDSaveRequest,
        HibernateRealEstateActivitiesRequest> {

    @Override
    public HibernateRealEstateActivitiesRequest convert(REActivitiesRequestSDSaveRequest request) {

        HibernateRealEstateActivitiesRequest activitiesRequest = new HibernateRealEstateActivitiesRequest();

        HibernateRealEstateActivities activities = Optional.ofNullable(
                entityManager.find(HibernateRealEstateActivities.class, request.getRealEstateActivityId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        activitiesRequest.setActivities(activities);
        activitiesRequest.setUserLink(request.getUserLink());

        return doConvert(activitiesRequest, request);
    }
}
