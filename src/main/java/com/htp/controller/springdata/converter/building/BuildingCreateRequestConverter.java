package com.htp.controller.springdata.converter.building;

import com.htp.controller.springdata.buildings.BuildingSDSaveRequest;
import com.htp.domain.hibernate.HibernateBuilding;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuildingCreateRequestConverter extends BuildingRequestConverter<BuildingSDSaveRequest, HibernateBuilding>{

    @Override
    public HibernateBuilding convert(BuildingSDSaveRequest request) {

        HibernateBuilding building = new HibernateBuilding();
        HibernateUser user = Optional.ofNullable(entityManager.find(HibernateUser.class, request.getUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        building.setUser(user);

        return doConvert(building, request);
    }
}
