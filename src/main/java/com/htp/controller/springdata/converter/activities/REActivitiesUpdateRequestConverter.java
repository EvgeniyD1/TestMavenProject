package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.REActivitiesSDUpdateRequest;
import com.htp.domain.hibernate.HibernateRealEstateActivities;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class REActivitiesUpdateRequestConverter extends REActivitiesRequestConverter<REActivitiesSDUpdateRequest,
        HibernateRealEstateActivities>{

    @Override
    public HibernateRealEstateActivities convert(REActivitiesSDUpdateRequest request) {

        HibernateRealEstateActivities activities = Optional.ofNullable(
                entityManager.find(HibernateRealEstateActivities.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(activities, request);
    }
}
