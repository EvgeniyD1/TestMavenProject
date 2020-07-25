package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.REActivitiesSDSaveRequest;
import com.htp.domain.hibernate.HibernateBuilding;
import com.htp.domain.hibernate.HibernateRealEstateActivities;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class REActivitiesCreateRequestConverter extends REActivitiesRequestConverter<REActivitiesSDSaveRequest,
        HibernateRealEstateActivities>{

    @Override
    public HibernateRealEstateActivities convert(REActivitiesSDSaveRequest request) {

        HibernateRealEstateActivities activities = new HibernateRealEstateActivities();

        HibernateBuilding building = Optional.ofNullable(entityManager.find(HibernateBuilding.class,
                request.getBuildingId())).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        activities.setBuilding(building);
        activities.setUserLink(request.getUserLink());
        activities.setBuildingLink(request.getBuildingLink() +  building.getId().toString());

        return doConvert(activities, request);
    }
}
