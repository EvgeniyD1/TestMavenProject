package com.htp.controller.springdata.converter.building;

import com.htp.controller.springdata.buildings.BuildingUpdateRequest;
import com.htp.domain.Building;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuildingUpdateRequestConverter extends BuildingRequestConverter<BuildingUpdateRequest, Building> {

    @Override
    public Building convert(BuildingUpdateRequest request) {

        Building building = Optional.ofNullable(entityManager.find(Building.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(building, request);
    }
}
