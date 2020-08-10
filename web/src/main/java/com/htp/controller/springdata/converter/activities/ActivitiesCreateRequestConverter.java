package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.ActivitySaveRequest;
import com.htp.domain.Activities;
import com.htp.domain.Building;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivitiesCreateRequestConverter extends ActivitiesRequestConverter<ActivitySaveRequest,
        Activities> {

    @Override
    public Activities convert(ActivitySaveRequest request) {

        Activities activities = new Activities();

        Building building = Optional.ofNullable(entityManager.find(Building.class,
                request.getBuildingId())).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        activities.setBuilding(building);
        activities.setUserLink(request.getUserLink());
        activities.setBuildingLink(request.getBuildingLink() +  building.getId().toString());

        return doConvert(activities, request);
    }
}
