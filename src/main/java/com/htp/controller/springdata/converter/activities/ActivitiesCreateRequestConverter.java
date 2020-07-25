package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.ActivitySDSaveRequest;
import com.htp.domain.hibernate.HibernateActivities;
import com.htp.domain.hibernate.HibernateBuilding;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivitiesCreateRequestConverter extends ActivitiesRequestConverter<ActivitySDSaveRequest,
        HibernateActivities> {

    @Override
    public HibernateActivities convert(ActivitySDSaveRequest request) {

        HibernateActivities activities = new HibernateActivities();

        HibernateBuilding building = Optional.ofNullable(entityManager.find(HibernateBuilding.class,
                request.getBuildingId())).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        activities.setBuilding(building);
        activities.setUserLink(request.getUserLink());
        activities.setBuildingLink(request.getBuildingLink() +  building.getId().toString());

        return doConvert(activities, request);
    }
}
