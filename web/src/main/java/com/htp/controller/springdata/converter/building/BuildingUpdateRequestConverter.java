package com.htp.controller.springdata.converter.building;

import com.htp.controller.springdata.buildings.BuildingSDUpdateRequest;
import com.htp.domain.HibernateBuilding;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuildingUpdateRequestConverter extends BuildingRequestConverter<BuildingSDUpdateRequest, HibernateBuilding> {

    @Override
    public HibernateBuilding convert(BuildingSDUpdateRequest request) {

        HibernateBuilding building = Optional.ofNullable(entityManager.find(HibernateBuilding.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(building, request);
    }
}
