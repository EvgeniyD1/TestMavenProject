package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.ActivitiesSDUpdateRequest;
import com.htp.domain.HibernateActivities;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivitiesUpdateRequestConverter extends ActivitiesRequestConverter<ActivitiesSDUpdateRequest,
        HibernateActivities> {

    @Override
    public HibernateActivities convert(ActivitiesSDUpdateRequest request) {

        HibernateActivities activities = Optional.ofNullable(
                entityManager.find(HibernateActivities.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(activities, request);
    }
}
