package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.ActivitiesUpdateRequest;
import com.htp.domain.Activities;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivitiesUpdateRequestConverter extends ActivitiesRequestConverter<ActivitiesUpdateRequest,
        Activities> {

    @Override
    public Activities convert(ActivitiesUpdateRequest request) {

        Activities activities = Optional.ofNullable(
                entityManager.find(Activities.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(activities, request);
    }
}
